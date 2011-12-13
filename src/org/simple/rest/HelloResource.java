package org.simple.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.sun.jersey.api.core.InjectParam;

@Path("/hello")
public class HelloResource
{
	private final HelloMonitoring monitoring;
	
	public HelloResource(@InjectParam HelloMonitoring monitoring)
	{
		this.monitoring = monitoring;
	}
	
	@GET
	@Produces("text/plain")
	public String getGreeting(@QueryParam("name") String name)
	{
		monitoring.inc();
		return "Hi there " + (name != null ? name : "!");
	}
}
