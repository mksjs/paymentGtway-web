package com.paymentGtway.paymentGtwayweb.model;

import java.util.Arrays;
import java.util.List;

/**
 * @author manish
 *
 */
public class TokenInfo {
	private String merchantRefNum;
	private String customerId;
    List<String> paymentTypes = Arrays.asList("CARD");
    
    public String getMerchantRefNum() {
		return merchantRefNum;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setMerchantRefNum(String merchantRefNum) {
		this.merchantRefNum = merchantRefNum;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
