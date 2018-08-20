package com.personenservice.rest;

import javax.validation.Valid;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;

import com.personenservice.model.Person;
import com.personenservice.model.PersonDAO;

public class GetSinglePersonRessource {

	Person person = new Person();
	PersonDAO personDAO = new PersonDAO();
	
	@GET
	public String setSinglePersonWithValues(
			@Valid @FormParam("id") String id)
	{
		
//		personDAO.getSinglePersonById(id);		
		
		return "";
	}
}
