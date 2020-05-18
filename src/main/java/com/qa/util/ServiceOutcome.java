package com.qa.util;

import org.apache.http.HttpResponse;

/**
 * Getting Response time from HTTPs Response
 * 
 * @developer Janmejay.kumar
 */
public class ServiceOutcome {
	public int responseCode;
	public String responseResut;
	public HttpResponse response;

	public HttpResponse getResponse() {
		return response;
	}

	public void setResponse(HttpResponse response) {
		this.response = response;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseResut() {
		return responseResut;
	}

	public void setResponseResut(String responseResut) {
		this.responseResut = responseResut;
	}

}
