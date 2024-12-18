package com.github.clovisgargione.message;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.clovisgargione.dto.ProposalDTO;
import com.github.clovisgargione.dto.QuotationDTO;
import com.github.clovisgargione.service.OpportunityService;

import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class KafkaEvent {

	private final Logger LOG = LoggerFactory.getLogger(KafkaEvent.class);

    @Inject
    private OpportunityService opportunityService;

    @Incoming("proposal-in")
    @Transactional
    public void receiveProposal(ProposalDTO proposal){
        LOG.info("-- Recebendo Nova Proposta do Tópico Kafka --");
        opportunityService.buildOpportunity(proposal);
    }

    @Incoming("quotation-in")
    @Blocking
    public void receiveQuotation(QuotationDTO quotation){
        LOG.info("-- Recebendo Nova Cotação de Moeda do Tópico Kafka --");
        opportunityService.saveQuotation(quotation);
    }

}
