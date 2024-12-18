package com.github.clovisgargione.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class QuotationDTO {

	private Date date;

    private BigDecimal currencyPrice;

}
