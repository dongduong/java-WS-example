package com.dongduong.restclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

/*
 * http://crunchify.com/how-to-create-restful-java-client-with-java-net-url-example/
 */

public class RestJerseyNetURLClient {

	public static void main(String[] args) {
		System.out.println("\n============Output:============ \n" + callURL("http://localhost:8080/java-REST-jersey-example/restexample/ctofservice/"));
	}
 
	public static String callURL(String myURL) {
		System.out.println("Requested URL: " + myURL);
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		try {
			URL url = new URL(myURL);
			urlConn = url.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 1000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(), Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
			}
			in.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:" + myURL, e);
		}
 
		return sb.toString();
	}

}