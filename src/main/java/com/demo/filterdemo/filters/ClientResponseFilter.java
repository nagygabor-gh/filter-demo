package com.demo.filterdemo.filters;

import java.io.IOException;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientResponseContext;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Provider
public class ClientResponseFilter implements jakarta.ws.rs.client.ClientResponseFilter {

	@Override
	public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
		log.info("################# ClientResponseFilter#filter #################");
		responseContext.getHeaders()
				.add("X-Test-Client", "Test response client filter");
	}

}
