package com.github.clovisgargione.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.github.clovisgargione.dto.CurrencyPriceDTO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("last")
@RegisterRestClient(baseUri = "https://economia.awesomeapi.com.br")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface CurrencyPriceClient {

	@GET
	@Path("{pair}")
	CurrencyPriceDTO getPriceByPair(@PathParam("pair") String pair);
}
