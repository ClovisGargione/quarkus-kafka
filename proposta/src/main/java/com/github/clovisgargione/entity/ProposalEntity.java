package com.github.clovisgargione.entity;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proposta")
@Data
@NoArgsConstructor
public class ProposalEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customer;

    private BigDecimal priceTonne;

    private Integer tonnes;

    private String country;

    private Integer proposalValidityDays;

    private Date created;

}
