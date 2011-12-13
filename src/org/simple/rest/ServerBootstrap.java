package org.simple.rest;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.gmbal.ManagedObjectManager;
import org.glassfish.gmbal.ManagedObjectManagerFactory;
import org.glassfish.grizzly.http.server.HttpServer;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;

public class ServerBootstrap {

    private static int getPort(int defaultPort) {
        String port = System.getProperty("jersey.test.port");
        if (null != port) {
            try {
                return Integer.parseInt(port);
            } catch (NumberFormatException e) {
            }
        }
        return defaultPort;        
    } 
    
    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost/").port(getPort(9998)).build();
    }

    public static final URI BASE_URI = getBaseURI();
    
    public static void main(String[] args) throws IOException {
    	ManagedObjectManager manager = 
    			ManagedObjectManagerFactory.createStandalone("com.restapi");
    	manager.createRoot();
    	manager.registerAtRoot(new HelloMonitoring(), "hello-monitoring");
    	
		System.out.println("Starting grizzly...");
		ResourceConfig rc = new PackagesResourceConfig("org.simple.rest");
    	     
    	HttpServer httpServer = GrizzlyServerFactory.createHttpServer(BASE_URI, rc);
        httpServer.getServerConfiguration().setJmxEnabled(true);
 
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nTry out %shelloworld\nHit enter to stop it...",
                BASE_URI, BASE_URI));
        System.in.read();
        httpServer.stop();
    } 
}
