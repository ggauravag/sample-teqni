package org.pkb.springlogin.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GoogleRecaptchaUtils {

	private String postUrl;

	private String secret;

	/**
	 * @param postUrl
	 * @param secret
	 */
	public GoogleRecaptchaUtils(String postUrl, String secret) {
		super();
		this.postUrl = postUrl;
		this.secret = secret;
	}

	public boolean postClientResponse(String response) throws IOException {
		URL url = new URL(postUrl);
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

		connection.setRequestMethod("POST");
		StringBuilder paramBuilder = new StringBuilder();
		paramBuilder.append(
				String.format("%s=%s&", "secret", URLEncoder.encode(secret, StandardCharsets.UTF_8.displayName())));
		paramBuilder.append(
				String.format("%s=%s", "response", URLEncoder.encode(response, StandardCharsets.UTF_8.displayName())));

		String params = paramBuilder.toString();

		connection.setDoOutput(true);

		OutputStream outputStream = connection.getOutputStream();
		outputStream.write(params.getBytes(), 0, params.getBytes().length);
		outputStream.flush();
		outputStream.close();

		int responseCode = connection.getResponseCode();
		InputStream inputStream = null;
		if (responseCode != 200) {
			System.err.println("Error while connecting to Google Recaptcha");
		}

		try {
			inputStream = connection.getInputStream();
		} catch (Exception e) {
			inputStream = connection.getErrorStream();
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		StringBuilder responseBuilder = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			responseBuilder.append(line);
		}

		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> resultMap = mapper.readValue(responseBuilder.toString(),
				new TypeReference<HashMap<String, Object>>() {
				});

		if (Boolean.TRUE.equals(resultMap.get("success"))) {
			return true;
		} else {
			System.err.println("Could not verify reCaptcha response : " + responseBuilder.toString());
			return false;
		}

	}

}
