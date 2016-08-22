package org.jsonConvert.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.jsonConvert.JSONValue;
	
public class Test extends TestCase{

	public void testEncode() throws Exception{
		System.out.println("=======start test=======");
		
		System.out.println("=======One Obj=======");
		Person person = new Person(1, "rodrigo" ,"fortes");
		Address address = new Address("Rua Santos Saraiva", "Florianópolis");
		person.setAddress(address);
		List<String> list = new ArrayList<>();
		list.add("test01");
		list.add("test02");
		list.add("test03");
		person.setTest(list);

		String jsonString = JSONValue.toJSONString(person);
		System.out.println(jsonString);
        assertEquals("{\"id\":1,\"name\":\"rodrigo\",\"lastName\":\"fortes\",\"address\":{\"address\":\"Rua Santos Saraiva\",\"city\":\"Florianópolis\"},\"test\":[\"test01\",\"test02\",\"test03\"]}",jsonString);
        
        System.out.println("=======List Obj=======");
        List<Person> persons = new ArrayList<Person>();
        person = new Person(1, "Rodrigo" ,"Fortes");
		Person person1 = new Person(2, "Joara" ,"Araújo");
		Person person2 = new Person(3, "Lola" ,"Crazy");
		address = new Address("Rua Santos Saraiva", "Florianópolis");
		person.setAddress(address);
		person1.setAddress(address);
		person2.setAddress(address);
		list = new ArrayList<>();
		list.add("test01");
		list.add("test02");
		list.add("test03");
		person.setTest(list);
		
		persons.add(person);
		persons.add(person1);
		persons.add(person2);
		
		jsonString = JSONValue.toJSONString(persons);
		
		System.out.println(jsonString);
        assertEquals("[{\"id\":1,\"name\":\"Rodrigo\",\"lastName\":\"Fortes\",\"address\":{\"address\":\"Rua Santos Saraiva\",\"city\":\"Florianópolis\"},\"test\":[\"test01\",\"test02\",\"test03\"]},{\"id\":2,\"name\":\"Joara\",\"lastName\":\"Araújo\",\"address\":{\"address\":\"Rua Santos Saraiva\",\"city\":\"Florianópolis\"},\"test\":[]},{\"id\":3,\"name\":\"Lola\",\"lastName\":\"Crazy\",\"address\":{\"address\":\"Rua Santos Saraiva\",\"city\":\"Florianópolis\"},\"test\":[]}]",jsonString);
        
    }
}
