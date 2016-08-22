package org.jsonConvert;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class JSONValue {

	public static OutputStream toJSONFile(Object obj, String path){
		String json = toJSONString(obj);

		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(path);
			Writer outputStreamWriter = new OutputStreamWriter(outputStream);

			outputStreamWriter.write(json);

			outputStreamWriter.close();
		} catch (IOException a) {
			Logger.getLogger(a.toString());
		}
		
		return outputStream;
	}
	
	public static String toJSONString(Object obj){
		Class theClass = obj.getClass();
		Field fields[] = theClass.getDeclaredFields();	    

		if(obj == null)
			return "null";

		if(obj instanceof String)
			return "\""+escape((String)obj)+"\"";

		if(obj instanceof Double){
			if(((Double)obj).isInfinite() || ((Double)obj).isNaN())
				return "null";
			else
				return obj.toString();
		}

		if(obj instanceof Float){
			if(((Float)obj).isInfinite() || ((Float)obj).isNaN())
				return "null";
			else
				return obj.toString();
		}		

		if(obj instanceof Number)
			return obj.toString();

		if(obj instanceof Boolean)
			return obj.toString();

		if(obj instanceof Map)
			return JSONObject.toJSONString((Map)obj);

		if(obj instanceof List)
			return JSONArray.toJSONString((List)obj);

		if(obj instanceof Object)
			return JSONObject.toJSONString(obj);

		return obj.toString();
	}

	public static String escape(String s){
		if(s==null)
			return null;
		StringBuffer sb = new StringBuffer();
		escape(s, sb);
		return sb.toString();
	}

	static void escape(final String s, final StringBuffer sb) 
	{
		int stringLength = s.length(); 
		for(int i=0;i<stringLength;i++){
			char ch=s.charAt(i);
			switch(ch){
			case '"':
				sb.append("\\\"");
				break;
			case '\'':
				sb.append("\\\'");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			case '/':
				sb.append("\\/");
				break;
			default:
				if((ch>='\u0000' && ch<='\u001F') || (ch>='\u007F' && ch<='\u009F') || (ch>='\u2000' && ch<='\u20FF')){
					String ss=Integer.toHexString(ch);
					sb.append("\\u");
					for(int k=0;k<4-ss.length();k++){
						sb.append('0');
					}
					sb.append(ss.toUpperCase());
				}
				else{
					sb.append(ch);
				}
			}
		}
	}

}
