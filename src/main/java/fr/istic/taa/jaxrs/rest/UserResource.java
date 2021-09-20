package fr.istic.taa.jaxrs.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import fr.istic.taa.jaxrs.domain.User;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.models.parameters.RequestBody;

@Path("/user")
@Produces({"application/json", "application/xml"})

public class UserResource {

	@GET
	  @Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	  public User getUserById(@PathParam("userId") Long userId)  {
		User u = new User();
	      return u;
	  }

	  @POST
	  @Consumes("application/json")
	  @Produces(MediaType.TEXT_PLAIN)
	  public String addUser(@Parameter(required = true) User user) {
		 System.out.println(user.getEmail());
		 
	    // add pet
	    return "toto";
	  }

}
