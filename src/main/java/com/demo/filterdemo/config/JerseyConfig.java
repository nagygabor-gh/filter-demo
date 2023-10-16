package com.demo.filterdemo.config;

import com.demo.filterdemo.filters.ClientRequestFilter;
import com.demo.filterdemo.filters.ClientResponseFilter;
import com.demo.filterdemo.filters.ContainerRequestFilter;
import com.demo.filterdemo.filters.ContainerResponseFilter;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import jakarta.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/jersey")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(ContainerRequestFilter.class);
		register(ContainerResponseFilter.class);
		packages("com.demo.filterdemo.jersey.server");
	}

}
