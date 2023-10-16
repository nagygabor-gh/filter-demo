package com.demo.filterdemo.filters;

import java.io.IOException;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.PreMatching;

/**
 * Simple filter changing all POST methods to GET. It might be useful when you want to handle PUT and POST
 * methods with the same Java code.
 *
 * If you try to change the HTTP method in a post-matching filter, it would cause an IllegalArgumentException.
 */
@PreMatching
public class PreMatchingFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if (requestContext.getMethod().equals(("PUT"))) {
			requestContext.setMethod("POST");
		}
	}

}
