package org.jboss.tools.example.html5.com.personenservice;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("Personen")
public class PostRessource {
	
	
	@POST
	public Response getIt(
			@FormParam("id") String id,
			@NotNull @Size(min = 3, max = 20) @FormParam("name") @Valid String name) { //@NotNull & @Size is not working at the moment
				return Response.status(200)
						.entity("ID : " + id + ", Name : " + name)
						.build();
	}
}