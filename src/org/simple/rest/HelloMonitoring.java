package org.simple.rest;

import java.util.concurrent.atomic.AtomicInteger;

import org.glassfish.gmbal.Description;
import org.glassfish.gmbal.ManagedAttribute;
import org.glassfish.gmbal.ManagedObject;

import com.sun.jersey.spi.resource.Singleton;

@Singleton 
@ManagedObject
public class HelloMonitoring {
	private static final AtomicInteger mHelloCount = new AtomicInteger();

	/**
	 * @return the number of times idle keep-alive connections were closed by
	 *         timeout.
	 */
	@ManagedAttribute(id = "hello-count")
	@Description("The number of hello is called.")
	public int getHelloCount() {
		return mHelloCount.get();
	}

	public void inc() {
		mHelloCount.incrementAndGet();
	}
}
