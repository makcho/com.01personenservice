package com.personenservice.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.personenservice.model.Person;
import com.personenservice.model.PersonDAO;

@Path("delete")
public class DeleteRessource {

	Person person = new Person();
	PersonDAO personDAO = new PersonDAO();
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String setSinglePersonWithValues(
			@FormParam("id") String id) throws Exception 
	{ 		

		personDAO.deletePersonById(id);

		return "Person was deleted by ID: " + id;
	}
}