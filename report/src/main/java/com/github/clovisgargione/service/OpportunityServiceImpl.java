package com.github.clovisgargione.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.github.clovisgargione.dto.OpportunityDTO;
import com.github.clovisgargione.dto.ProposalDTO;
import com.github.clovisgargione.dto.QuotationDTO;
import com.github.clovisgargione.entity.OpportunityEntity;
import com.github.clovisgargione.entity.QuotationEntity;
import com.github.clovisgargione.repository.OpportunityRepository;
import com.github.clovisgargione.repository.QuotationRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class OpportunityServiceImpl implements OpportunityService {

	@Inject
    private QuotationRepository quotationRepository;

    @Inject
    private OpportunityRepository opportunityRepository;

    @Override
    public void buildOpportunity(ProposalDTO proposal) {

        List<QuotationEntity> quotationEntities = quotationRepository.findAll().list();
        Collections.reverse(quotationEntities);

        OpportunityEntity opportunity = new OpportunityEntity();
        opportunity.setDate(new Date());
        opportunity.setProposalId(proposal.getProposalId());
        opportunity.setCustomer(proposal.getCustomer());
        opportunity.setPriceTonne(proposal.getPriceTonne());
        opportunity.setLastDollarQuotation(quotationEntities.get(0).getCurrencyPrice());

        opportunityRepository.persist(opportunity);

    }

    @Override
    @Transactional
    public void saveQuotation(QuotationDTO quotation) {

        QuotationEntity createQuotation = new QuotationEntity();
        createQuotation.setDate(new Date());
        createQuotation.setCurrencyPrice(quotation.getCurrencyPrice());

        quotationRepository.persist(createQuotation);

    }

    @Override
    public List<OpportunityDTO> generateOpportunityData() {

        List<OpportunityDTO> opportunities = new ArrayList<>();

        opportunityRepository
                .findAll()
                .stream()
                .forEach(item->{
                    opportunities.add(OpportunityDTO.builder()
                            .proposalId(item.getProposalId())
                            .customer(item.getCustomer())
                            .priceTonne(item.getPriceTonne())
                            .lastDollarQuotation(item.getLastDollarQuotation())
                            .build());
                });

        return opportunities;

    }

}
