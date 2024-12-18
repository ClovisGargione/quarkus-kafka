package com.github.clovisgargione.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class USDBRL {

	public String code;
	public String codein;
	public String name;
	public String high;
	public String low;
	public String varBid;
	public String pctChange;
	public String bid;
	public String ask;
	public String timestamp;
	public String create_date;
	
}
