package com.tenzin.app.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.tenzin.app.CustomerResource;
import com.tenzin.app.HelloWorldRest;
import com.tenzin.app.exhandler.GenericErrProvider;
import com.tenzin.app.exhandler.InvalidDataProvider;
import com.tenzin.app.exhandler.NoRecordFoundProvider;

@Configuration
public class SampleRestConfig extends ResourceConfig{
	
	public SampleRestConfig() {
		register(CustomerResource.class);
		register(HelloWorldRest.class);
		register(GenericErrProvider.class);
		register(InvalidDataProvider.class);
		register(NoRecordFoundProvider.class);
		//register(CustomerSearchResults.class);
	}
	
}