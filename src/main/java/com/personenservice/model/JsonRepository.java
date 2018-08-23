package com.personenservice.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;

public class JsonRepository {

	String id;
	String name;
	JSONObject jsonObject = new JSONObject();
	
	public void checkJsonFileIsExistingAndWriteIntoFile() throws IOException {
		File file = new File("D:\\dev\\eclipse\\jee-workspace\\com.01personenservice\\person.json");
		
		if (file.exists()) {
			System.out.println("JSON file is already existing");
			putValuesIntoJsonObject(id, name);
			
			try {
				System.out.println("new person has been createt via JSON");
				
			} catch (Exception e) {
				System.out.println("there is an error with the filewriter");
			}
		}else {
			createJsonFile();
			putValuesIntoJsonObject(id, name);
		}
	}
	
	public void createJsonFile() {
				
		try{
			FileWriter fileWriter = new FileWriter("D:\\dev\\\\eclipse\\jee-workspace\\com.01personenservice\\person.json");
			System.out.println("a new JSON file could be created");
		} catch (Exception e) {
			System.out.println("JSON file could not be created");
		}
	}
	
	public void putValuesIntoJsonObject(String id, String name) {
		
		jsonObject.put("id", id);
		jsonObject.put("name", name);
		
		try {
			FileWriter fileWriter = new FileWriter
					("D:\\dev\\eclipse\\jee-workspace\\com.01personenservice\\person.json");
			fileWriter.write(jsonObject.toJSONString());
			fileWriter.flush();
			fileWriter.close();
			System.out.println("new person has been createt via JSON");
		} catch (Exception e) {
			System.out.println("new person could not be createt via JSON");
		}
	}
}
