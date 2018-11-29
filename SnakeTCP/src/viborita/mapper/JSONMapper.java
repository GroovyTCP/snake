package viborita.mapper;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONMapper {

	public static String fromObjectToJSON(Object obj) {
		ObjectMapper om = new ObjectMapper();
		try {
			return om.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			System.out.println("ERROR AL PARSEAR OBJECT A JSON");
			e.printStackTrace();
		} 
		return "";
	}
	
	public static Object fromJSONToObject(String json) {
		ObjectMapper om = new ObjectMapper();
		try {
			return om.readValue(json, Object.class);
		} catch (IOException e) {
			System.out.println("ERROR AL PARSEAR DESDE JSON A OBJECT");
			e.printStackTrace();
		}
		return Object.class;
	}
	
}
