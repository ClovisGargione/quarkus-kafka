package com.github.clovisgargione.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ProposalDTO {

	private Long proposalId;

	private String customer;

	private BigDecimal priceTonne;

}
