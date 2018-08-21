package com.personenservice.rest;

import java.sql.SQLException;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;

import com.personenservice.model.Person;
import com.personenservice.model.PersonDAO;

public class GetSinglePersonRessource {

	Person person = new Person();
	PersonDAO personDAO = new PersonDAO();
	
	@GET
	public String getSinglePersonByIds(
			@FormParam("id") String id) throws InstantiationException, IllegalAccessException, SQLException
	{
		
		personDAO.getSinglePersonById(id);		
		
		return "";
	}
}
