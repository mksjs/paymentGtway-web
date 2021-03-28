package com.paymentGtway.paymentGtwayweb.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentGtwayWebInfo {
	private String merchantRefNum;
    private int amount;
    private String paymentHandleToken;
    private String merchantCustomerId;
    private boolean dupCheck = true;
    private boolean settleWithAuth = false;
    private String currencyCode = "USD";
    private String customerIp = "168.0.0.1";
    private String description = "Bought book clean code";


	public PaymentGtwayWebInfo(String merchantRefNum, int amount, String paymentHandleToken) {
		// TODO Auto-generated constructor stub
		this.merchantRefNum = merchantRefNum;
		this.amount = amount;
		this.paymentHandleToken = paymentHandleToken;
	}


	public String getMerchantRefNum() {
		return merchantRefNum;
	}


	public int getAmount() {
		return amount;
	}


	public String getPaymentHandleToken() {
		return paymentHandleToken;
	}


	public String getMerchantCustomerId() {
		return merchantCustomerId;
	}


	public void setMerchantRefNum(String merchantRefNum) {
		this.merchantRefNum = merchantRefNum;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public void setPaymentHandleToken(String paymentHandleToken) {
		this.paymentHandleToken = paymentHandleToken;
	}


	public void setMerchantCustomerId(String merchantCustomerId) {
		this.merchantCustomerId = merchantCustomerId;
	}


	public boolean isDupCheck() {
		return dupCheck;
	}


	public boolean isSettleWithAuth() {
		return settleWithAuth;
	}


	public String getCurrencyCode() {
		return currencyCode;
	}


	public String getCustomerIp() {
		return customerIp;
	}


	public String getDescription() {
		return description;
	}


	public void setDupCheck(boolean dupCheck) {
		this.dupCheck = dupCheck;
	}


	public void setSettleWithAuth(boolean settleWithAuth) {
		this.settleWithAuth = settleWithAuth;
	}


	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}


	public void setCustomerIp(String customerIp) {
		this.customerIp = customerIp;
	}


	public void setDescription(String description) {
		this.description = description;
	}

}
