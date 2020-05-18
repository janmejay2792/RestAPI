package com.qa.util;

import java.io.IOException;

import javax.xml.bind.DatatypeConverter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * A simple Java REST GET example using the Apache HTTP library. This executes a
 * call against the Yahoo Weather API service, which is actually an RSS service
 * (http://developer.yahoo.com/weather/).
 * 
 * Try this Twitter API URL for another example (it returns JSON results):
 * http://search.twitter.com/search.json?q=%40apple (see this url for more
 * twitter info: https://dev.twitter.com/docs/using-search)
 * 
 * Apache HttpClient: http://hc.apache.org/httpclient-3.x/
 
 *@developer janmejay.kumar
 */
public class ServiceTest {

	public ServiceOutcome apacheHttpClientGet(String url) {
		/* public static void main(String[] args) { */

		String content = null;
		ServiceOutcome outCome = new ServiceOutcome();

		try {
			//System.out.println("Started");
			//DefaultHttpClient Client = new DefaultHttpClient();

			HttpGet httpGet = new HttpGet(url); 
			String authUserName=System.getProperty("AuthUser");
			String authPassword=System.getProperty("AuthPass");
//			String authURL=System.getProperty("Auth_URL");
			
			String encoding = DatatypeConverter.printBase64Binary((authUserName+":"+authPassword).getBytes("UTF-8"));
			httpGet.setHeader("Authorization", "Basic " + encoding);
		
	          
			HttpResponse response = HttpClientBuilder.create().build().execute(httpGet);
			outCome.setResponse(response);
			
			 
			int statusCode = response.getStatusLine().getStatusCode();
			outCome.responseCode = statusCode;
//			System.out.println(statusCode);
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				content = EntityUtils.toString(entity);
			
				outCome.responseResut = content;
				

			} else if (response.getStatusLine().getStatusCode() != 200) {

				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}

			//System.out.println("repsonseStr = " + content);
			//System.out.println("ENd");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return outCome;
	}

}