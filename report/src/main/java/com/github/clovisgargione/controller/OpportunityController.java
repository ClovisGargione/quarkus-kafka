package com.github.clovisgargione.controller;

import java.util.List;

import com.github.clovisgargione.dto.OpportunityDTO;
import com.github.clovisgargione.service.OpportunityService;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/api/opportunity")
public class OpportunityController {

	@Inject
    private OpportunityService opportunityService;

    @GET
    @Path("/data")
    public List<OpportunityDTO> generateReport(){

        return opportunityService.generateOpportunityData();

    }

}
