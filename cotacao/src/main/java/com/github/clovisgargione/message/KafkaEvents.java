package com.github.clovisgargione.message;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.clovisgargione.dto.QuotationDTO;

import io.smallrye.reactive.messaging.kafka.Record;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class KafkaEvents {

	private final Logger LOG = LoggerFactory.getLogger(KafkaEvents.class);
	
	@Channel("quotation-out")
	Emitter<Record<Integer, QuotationDTO>> quotationRequestEmitter;
	
	public void sendNewKafkaEvent(QuotationDTO quotation) {
		
		LOG.info("-- Enviando Cotação para Tópico Kafka --");
		quotationRequestEmitter.send(Record.of((int) (Math.random() * (4 - 1)) + 1, quotation)).toCompletableFuture().join();
	}
}
