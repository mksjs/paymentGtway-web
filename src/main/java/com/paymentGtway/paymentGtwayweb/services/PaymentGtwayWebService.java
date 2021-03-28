package com.paymentGtway.paymentGtwayweb.services;

import com.paymentGtway.paymentGtwayweb.model.Payment;
import com.paymentGtway.paymentGtwayweb.model.PaymentGtwayWebResponse;
import com.paymentGtway.paymentGtwayweb.model.TokenInfo;
import com.paymentGtway.paymentGtwayweb.model.TokenResponse;

public interface PaymentGtwayWebService {

	PaymentGtwayWebResponse makePayment(Payment payment);

	TokenResponse getSingleUseCustomerToken(TokenInfo tokenInfo);

}
