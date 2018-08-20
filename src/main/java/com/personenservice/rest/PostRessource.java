package com.personenservice.rest;

import javax.validation.Valid;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.personenservice.model.Person;
import com.personenservice.model.PersonDAO;



@Path("Personen")
public class PostRessource {
	
	Person person = new Person();
	PersonDAO personDAO = new PersonDAO();
	
	@POST
	public Response setSinglePersonWithValues(
			@Valid @FormParam("id") String id,
			@Valid @FormParam("name") String name) throws Exception 
	{ //@NotNull & @Size is not working at the moment
		
		person.setId(id);
		person.setName(name);
		personDAO.insertPerson(id, name);		
		
		return Response.status(200)		
						.entity("ID : " + person.getId() + ", Name : " + person.getName())
						.build();
	}
}