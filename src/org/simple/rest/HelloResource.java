package org.simple.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("hello")
public class HelloResource
{
	@GET
	@Produces("text/plain")
	public String getGreeting(@QueryParam("name") String name)
	{
		return "Hi there " + name;
	}
}
