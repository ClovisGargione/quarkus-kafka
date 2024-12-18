package com.github.clovisgargione.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.github.clovisgargione.client.CurrencyPriceClient;
import com.github.clovisgargione.dto.CurrencyPriceDTO;
import com.github.clovisgargione.dto.QuotationDTO;
import com.github.clovisgargione.entity.QuotationEntity;
import com.github.clovisgargione.message.KafkaEvents;
import com.github.clovisgargione.repository.QuotationRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class QuotationService {

	@Inject
	@RestClient
	private CurrencyPriceClient currencyPriceClient;
	
	@Inject
	private QuotationRepository quotationRepository;
	
	@Inject
	private KafkaEvents kafkaEvents;
	
	public void getCurrencyPrice() {

	        CurrencyPriceDTO currencyPriceInfo = currencyPriceClient.getPriceByPair("USD-BRL");

	        if(updateCurrentInfoPrice(currencyPriceInfo)){
	            kafkaEvents.sendNewKafkaEvent(QuotationDTO
	                    .builder()
	                    .currencyPrice(new BigDecimal(currencyPriceInfo.getUSDBRL().getBid()))
	                    .date(new Date())
	                    .build());
	        }

	    }

	    private boolean updateCurrentInfoPrice(CurrencyPriceDTO currencyPriceInfo) {

	        BigDecimal currentPrice = new BigDecimal(currencyPriceInfo.getUSDBRL().getBid());
	        boolean updatePrice = false;

	        List<QuotationEntity> quotationList = quotationRepository.findAll().list();

	        if(quotationList.isEmpty()){

	            saveQuotation(currencyPriceInfo);
	            updatePrice = true;

	        } else {

	            QuotationEntity lastDollarPrice = quotationList
	                    .get(quotationList.size() -1);


	            if(currentPrice.floatValue() > lastDollarPrice.getCurrencyPrice().floatValue()){
	                updatePrice = true;
	                saveQuotation(currencyPriceInfo);
	            }
	        }

	        return updatePrice;

	    }

	    private void saveQuotation(CurrencyPriceDTO currencyInfo){

	        QuotationEntity quotation = new QuotationEntity();

	        quotation.setDate(new Date());
	        quotation.setCurrencyPrice(new BigDecimal(currencyInfo.getUSDBRL().getBid()));
	        quotation.setPctChange(currencyInfo.getUSDBRL().getPctChange());
	        quotation.setPair("USD-BRL");

	        quotationRepository.persist(quotation);
	    }

	
}
