package com.personenservice.rest;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.personenservice.model.Person;
import com.personenservice.model.PersonDAO;

@Path("update")
public class UpdateSinglePersonRessource {

	Person person = new Person();
	PersonDAO personDAO = new PersonDAO();

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String updateSinglePersonById(@FormParam("id") String id, @FormParam("name") String name)
			throws Exception {

		personDAO.updatePersonById(id, name);

		return "Person with ID " + id + " has a new name"; 
	}
}
