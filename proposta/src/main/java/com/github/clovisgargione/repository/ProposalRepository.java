package com.github.clovisgargione.repository;

import java.util.Optional;

import com.github.clovisgargione.entity.ProposalEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProposalRepository implements PanacheRepository<ProposalEntity> {

	public Optional<ProposalEntity> findByCustomer(String customer) {
		return Optional.of(find("customer", customer).firstResult());
	}

}
