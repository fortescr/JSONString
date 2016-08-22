package org.jsonConvert;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

public class JSONObject extends HashMap implements Map{

	private static final long serialVersionUID = -503443796854799292L;


	public JSONObject() {
		super();
	}

	public JSONObject(Map map) {
		super(map);
	}

	public static String toJSONString(Map map){
		if(map == null)
			return "null";

		StringBuffer sb = new StringBuffer();
		boolean first = true;
		Iterator iter=map.entrySet().iterator();

		sb.append('{');
		while(iter.hasNext()){
			if(first)
				first = false;
			else
				sb.append(',');

			Map.Entry entry=(Map.Entry)iter.next();
			toJSONString(String.valueOf(entry.getKey()),entry.getValue(), sb);
		}
		sb.append('}');
		return sb.toString();
	}

	public static String toJSONString(Object obj){
		if(obj == null)
			return "null";

		StringBuffer sb = new StringBuffer();
		boolean first = true;
		sb.append('{');

		try{
			Class theClass = obj.getClass();
			Field fields[] = theClass.getDeclaredFields();	    
			for (int j = 0, m = fields.length; j < m; j++) 
			{		
				fields[j].setAccessible(true);

				if(first)
					first = false;
				else
					sb.append(',');

				toJSONString(String.valueOf(fields[j].getName()),fields[j].get(obj), sb);
			}

			sb.append('}');

		} catch (IllegalArgumentException e) {
			Logger.getLogger(e.toString());
		} catch (IllegalAccessException e) {
			Logger.getLogger(e.toString());
		}

		return sb.toString();
	}

	public String toJSONString(){
		return toJSONString(this);
	}

	private static String toJSONString(String key,Object value, StringBuffer sb){
		sb.append('\"');
		if(key == null)
			sb.append("null");
		else
			JSONValue.escape(key, sb);
		sb.append('\"').append(':');

		sb.append(JSONValue.toJSONString(value));

		return sb.toString();
	}

	public String toString(){
		return toJSONString();
	}

	public static String toString(String key,Object value){
		StringBuffer sb = new StringBuffer();
		toJSONString(key, value, sb);
		return sb.toString();
	}

	public static String escape(String s){
		return JSONValue.escape(s);
	}
}
