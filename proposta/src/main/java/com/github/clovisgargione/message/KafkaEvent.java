package com.github.clovisgargione.message;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.clovisgargione.dto.ProposalDTO;

import io.smallrye.reactive.messaging.kafka.Record;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class KafkaEvent {

	private final Logger LOG = LoggerFactory.getLogger(KafkaEvent.class);

    @Channel("proposal-out")
    Emitter<Record<Integer, ProposalDTO>> proposalRequestEmitter;

    public void sendNewKafkaEvent(ProposalDTO proposalDTO){
        LOG.info("-- Enviando Nova Proposta para Tópico Kafka --");
        proposalRequestEmitter.send(Record.of((int) (Math.random() * (4 - 1)) + 1, proposalDTO)).toCompletableFuture().join();;
    }

}
