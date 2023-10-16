package com.demo.filterdemo.filters;

import java.io.IOException;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Provider
public class ContainerResponseFilter implements jakarta.ws.rs.container.ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		log.info("################# ContainerResponseFilter#filter #################");
//		responseContext.getHeaders().add("X-Enhanced-By", "Jersey");
	}

}
