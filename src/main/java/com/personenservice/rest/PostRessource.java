package com.personenservice.rest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.personenservice.model.JsonRepository;
import com.personenservice.model.Person;
import com.personenservice.model.PersonDAO;

@Path("Personen")
public class PostRessource {

	Person person = new Person();
	PersonDAO personDAO = new PersonDAO();
	JsonRepository jsonRepository = new JsonRepository();

	@POST
	public Response setSinglePersonWithValues(@NotNull @Valid @FormParam("id") String id,
			@NotNull @Size(min = 3, max = 20, message = "bitte 3-20 Zeichen eintragen") @Valid @FormParam("name") String name)
			throws Exception { // @NotNull & @Size is not working at the moment
		
		person.setId(id);
		person.setName(name);
		personDAO.validatePerson(id, name);
		jsonRepository.checkJsonFileIsExisting();
		jsonRepository.putValuesIntoJsonObject(id, name);

		try {
			return Response.status(200).entity("ID : " + person.getId() + ", Name : " + person.getName()).build();
		} catch (Exception e) {
			return Response.status(400).entity("Die ID " + person.getId() + " ist bereits vergeben " + person.getName())
					.build();

		}
	}
}