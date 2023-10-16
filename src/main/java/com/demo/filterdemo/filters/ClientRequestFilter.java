package com.demo.filterdemo.filters;

import java.io.IOException;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Provider
public class ClientRequestFilter implements jakarta.ws.rs.client.ClientRequestFilter {

	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {
		log.info("################# ClientRequestFilter#filter #################");
	}

}
