package com.personenservice.rest;



import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.personenservice.model.Person;



@Path("Personen")
public class PostRessource {
	
	Person person = new Person();
	
//	@NotNull(message = "Name cannot be null")
//	@Size(min = 3, max = 20, message = "bitte 3-20 Zeichen eintragen")
	
	@POST
	public Response setSinglePersonWithValues(
			@NotNull(message = "Name cannot be null") @NotNull @FormParam("id") String id,
			@Size(min = 3, max = 20, message = "bitte 3-20 Zeichen eintragen") @Valid @FormParam("name") String name) { //@NotNull & @Size is not working at the moment
		
		person.setId(id);
		person.setName(name);
		
		return Response.status(200)		
						.entity("ID : " + person.getId() + ", Name : " + person.getName())
						.build();
	}
}