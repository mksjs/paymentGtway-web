package com.paymentGtway.paymentGtwayweb.services;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymentGtway.paymentGtwayweb.model.Payment;
import com.paymentGtway.paymentGtwayweb.model.PaymentGtwayWebInfo;
import com.paymentGtway.paymentGtwayweb.model.PaymentGtwayWebResponse;
import com.paymentGtway.paymentGtwayweb.model.TokenInfo;
import com.paymentGtway.paymentGtwayweb.model.TokenResponse;

@Service
public class PaymentGtwayWebServiceImpl implements PaymentGtwayWebService {

	@Override
	public PaymentGtwayWebResponse makePayment(Payment payment) {
		// TODO Auto-generated method stub
		PaymentGtwayWebInfo paymentGtwayWebInfo = new PaymentGtwayWebInfo(payment.getMerchantRefNum(),
				payment.getAmount(), payment.getPaymentHandleToken());
		paymentGtwayWebInfo.setMerchantCustomerId(payment.getCustomerId());
		final String base64 = "Basic cHJpdmF0ZS03NzUxOkItcWEyLTAtNWYwMzFjZGQtMC0zMDJkMDIxNDQ5NmJlODQ3MzJhMDFmNjkwMjY4ZDNiOGViNzJlNWI4Y2NmOTRlMjIwMjE1MDA4NTkxMzExN2YyZTFhODUzMTUwNWVlOGNjZmM4ZTk4ZGYzY2YxNzQ4";
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(paymentGtwayWebInfo);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		final JacksonJsonProvider jacksonJsonProvider = new JacksonJaxbJsonProvider()
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Client client = ClientBuilder.newClient(new ClientConfig(jacksonJsonProvider));
		Entity<String> payload = Entity.json(jsonString);
		Response response = client.target("https://api.test.paysafe.com/paymenthub/v1/payments")
				.request(String.valueOf(MediaType.APPLICATION_JSON)).header("Authorization", base64)
				.header("Simulator", "\"EXTERNAL\"").header("Access-Control-Allow-Origin", "*").post(payload);

		System.out.println("status: " + response.getStatus());
		System.out.println("headers: " + response.getHeaders());
		response.getHeaders().putSingle(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
		PaymentGtwayWebResponse paymentResponse = response.readEntity(PaymentGtwayWebResponse.class);
		return paymentResponse;
	}

	@Override
	public TokenResponse getSingleUseCustomerToken(TokenInfo tokenInfo) {
		// TODO Auto-generated method stub
		final String base64 = "Basic cHJpdmF0ZS03NzUxOkItcWEyLTAtNWYwMzFjZGQtMC0zMDJkMDIxNDQ5NmJlODQ3MzJhMDFmNjkwMjY4ZDNiOGViNzJlNWI4Y2NmOTRlMjIwMjE1MDA4NTkxMzExN2YyZTFhODUzMTUwNWVlOGNjZmM4ZTk4ZGYzY2YxNzQ4";
		ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(tokenInfo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        final JacksonJsonProvider jacksonJsonProvider = new JacksonJaxbJsonProvider().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Client client = ClientBuilder.newClient(new ClientConfig(jacksonJsonProvider));
        Entity<String> payload = Entity.json(jsonString);
        Response response = client.target("https://api.test.paysafe.com/paymenthub/v1/customers/" + tokenInfo.getCustomerId() + "/singleusecustomertokens")
                .request(String.valueOf(MediaType.APPLICATION_JSON))
                .header("Authorization", base64)
                .header("Simulator", "\"EXTERNAL\"")
                .post(payload);

        System.out.println("status: " + response.getStatus());
        System.out.println("headers: " + response.getHeaders());
        response.getHeaders().putSingle(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        return response.readEntity(TokenResponse.class);
	}

}
