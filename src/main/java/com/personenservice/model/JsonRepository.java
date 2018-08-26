package com.personenservice.model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonRepository {
	private static final JSONArray JSONArray = null;
	String id;
	String name;
	JSONObject jsonObject = new JSONObject();
	
	public void checkJsonFileIsExisting() throws IOException {
		File file = new File("D:\\dev\\eclipse\\jee-workspace\\com.01personenservice\\person.json");
		
		if (file.exists()) {
			System.out.println("JSON file is already existing");	
		}else {
			createJsonFile();
		}
	}
	
	public void createJsonFile() {
		try{
			FileWriter fileWriter = new FileWriter
					("D:\\dev\\\\eclipse\\jee-workspace\\com.01personenservice\\person.json");
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
					("D:\\dev\\eclipse\\jee-workspace\\com.01personenservice\\person.json", true);
			fileWriter.write(jsonObject.toJSONString());
			fileWriter.flush();
			fileWriter.close();
			System.out.println("new person has been createt via JSON");
		} catch (Exception e) {
			System.out.println("new person could not be createt via JSON");
		}
	}
	
	public void deleteJsonFile(){
        try
        {
            Files.deleteIfExists
            	(Paths.get("D:\\dev\\eclipse\\jee-workspace\\com.01personenservice\\person.json"));
        }
        catch(IOException io)
        {
            System.out.println("file couldn't be deleted");
        }
         
        System.out.println("Deletion successful.");
    }
	
	public static void main(String[] args) {
        JSONParser parser = new JSONParser();
 
            
    }
}
