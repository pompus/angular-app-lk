package com.leukemia.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * http://vr-abt.blogspot.com/2015/02/send-sms-through-javajsp-servlets.html
 *
 */
public class SendSms {

	public boolean sendSms(String message, String number) {
		boolean response = true;
		String request = null;
		try {
			String user = "user=" + "subharajsaha91@gmail.com:12345";
			String recipient = "&receipientno=" + number;
			String msgType = "&msgtype=0";
			String mesageText = "&msgtxt=" + URLEncoder.encode(message, "UTF-8");
			String sender = "&senderID=" + URLEncoder.encode("1234567", "UTF-8");

			request = "http://api.mvaayoo.com/mvaayooapi/MessageCompose?" + user + recipient + msgType + mesageText
					+ sender;

			URL url = new URL(request);

			// System.setProperty("http.proxyHost", "");
			// System.setProperty("http.proxyPort", "8080");

			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			uc.setRequestMethod("GET");
			uc.setReadTimeout(60 * 1000);
			uc.addRequestProperty("User-Agent", "Mozilla/4.0");
			int respcode = uc.getResponseCode();
			System.out.println("respcode received" + Integer.toString(respcode));
			BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
			}
			in.close();

		} catch (Exception e) {
			System.out.println("Request failed for sms: " + request);
			System.out.println("Exception in sending sms: " + e);
			response = false;
		}
		return response;
	}
}
