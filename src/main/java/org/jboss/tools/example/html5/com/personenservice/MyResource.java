package org.jboss.tools.example.html5.com.personenservice;

import java.util.List;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.personenservice.controller.PersonController;
import com.personenservice.model.Person;



@Path("PersonenService")
public class MyResource {
	
	PersonController personenController = new PersonController();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> getIt() {
		return personenController.getAllPersonen();
	}
	
}
