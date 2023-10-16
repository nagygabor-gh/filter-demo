package com.demo.filterdemo.jersey.server;

import com.demo.filterdemo.filters.ClientRequestFilter;
import com.demo.filterdemo.filters.ClientResponseFilter;

import org.glassfish.jersey.client.ClientConfig;
import org.springframework.stereotype.Service;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

@Service
@Path("/hello")
public class HelloService {

	private static final String BUDAPEST_WEATHER_ENDPOINT = "https://wttr.in/Budapest?format=4";

	@GET
	@Produces("text/plain;charset=utf-8")
	public String hello() {
		return "Hello from Spring Jersey";
	}

	@GET
	@Path("/client")
	@Produces("text/plain;charset=utf-8")
	public String getBudapestWeatherInfo() {
		ClientConfig weatherClientConfig = getWeatherClientConfig();
		try (Client weatherClient = ClientBuilder.newClient(weatherClientConfig)) {
			WebTarget target = weatherClient.target(BUDAPEST_WEATHER_ENDPOINT);
			return target.request().get(String.class);
		} catch (Exception e) {
			return "Oops, encountered an error, please visit back later.";
		}
	}

	private static ClientConfig getWeatherClientConfig() {
		ClientConfig weatherClientConfig = new ClientConfig();
		weatherClientConfig.register(ClientRequestFilter.class);
		weatherClientConfig.register(ClientResponseFilter.class);
		return weatherClientConfig;
	}

}
