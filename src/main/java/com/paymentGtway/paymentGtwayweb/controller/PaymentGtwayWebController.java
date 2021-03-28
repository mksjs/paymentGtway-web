package com.paymentGtway.paymentGtwayweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymentGtway.paymentGtwayweb.model.Payment;
import com.paymentGtway.paymentGtwayweb.model.PaymentGtwayWebResponse;
import com.paymentGtway.paymentGtwayweb.model.TokenInfo;
import com.paymentGtway.paymentGtwayweb.model.TokenResponse;
import com.paymentGtway.paymentGtwayweb.services.PaymentGtwayWebService;


/**
 * @author manish
 *
 */
@RestController
@RequestMapping("/pay")
public class PaymentGtwayWebController {
	private PaymentGtwayWebService paymentGtwayWebService;
	
	@Autowired
	public PaymentGtwayWebController(PaymentGtwayWebService paymentGtwayWebService) {
		this.paymentGtwayWebService = paymentGtwayWebService;
	}
	
	@CrossOrigin
    @PostMapping
    public PaymentGtwayWebResponse makePayment(@RequestBody Payment payment) {
        return paymentGtwayWebService.makePayment(payment);
    }
	
	@CrossOrigin
    @PostMapping("/tokens")
    public TokenResponse getSingleUseCustomerToken(@RequestBody TokenInfo tokenInfo) {
        return paymentGtwayWebService.getSingleUseCustomerToken(tokenInfo);
    }
	
}
