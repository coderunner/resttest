package org.simple.rest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.JerseyTest;

public class TestHelloResource extends JerseyTest{
	
	public TestHelloResource()throws Exception {
        super("org.simple.rest");
    }

	@Test
	public void testHelloResourceWithName()
	{
		WebResource r = resource().path("/hello").queryParam("name", "bob");
		String response = r.get(String.class);
        assertEquals("Hi there bob", response);
	}
	
	@Test
	public void testHelloResource()
	{
		WebResource r = resource().path("/hello");
		String response = r.get(String.class);
        assertEquals("Hi there !", response);
	}
}
