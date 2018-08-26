package com.personenservice.rest;

import java.sql.SQLException;
import java.util.List;

import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.personenservice.controller.PersonController;
import com.personenservice.model.JsonRepository;
import com.personenservice.model.Person;
import com.personenservice.model.PersonDAO;

@Path("PersonenService")
public class MyResource {
	
	PersonDAO personDAO = new PersonDAO();
	JsonRepository JsonRepository = new JsonRepository();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getIt() throws InstantiationException, IllegalAccessException, SQLException {
		
		JsonRepository.deleteJsonFile();
		personDAO.listAllThePersons();
		return "";
	}
	
}
