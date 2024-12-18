package com.github.clovisgargione.service;

import java.util.List;

import com.github.clovisgargione.dto.OpportunityDTO;
import com.github.clovisgargione.dto.ProposalDTO;
import com.github.clovisgargione.dto.QuotationDTO;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface OpportunityService {

	void buildOpportunity(ProposalDTO proposal);

    void saveQuotation(QuotationDTO quotation);

    List<OpportunityDTO> generateOpportunityData();

}
