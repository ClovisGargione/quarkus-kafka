package com.github.clovisgargione.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.clovisgargione.service.QuotationService;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class QuotationScheduler {

	private final Logger LOG = LoggerFactory.getLogger(QuotationScheduler.class);

    @Inject
    private QuotationService quotationService;

    @Transactional
    @Scheduled(every = "35s", identity="task-job")
    void schedule(){
        LOG.info("-- Executando scheduler --");
        quotationService.getCurrencyPrice();
    }

}
