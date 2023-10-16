package com.demo.filterdemo.filters;

import java.io.IOException;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.SecurityContext;

public class AuthorizationRequestFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		final SecurityContext securityContext = requestContext.getSecurityContext();
		if (securityContext == null || !securityContext.isUserInRole("privileged")) {
			requestContext.abortWith(Response
					.status(Status.UNAUTHORIZED)
							.entity("User cannot access the resource.")
					.build());
		}
	}

}
