package com.apparelshop.dataparsingadidasservice.service;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class RequestPerformer implements IRequestPerformer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private HttpURLConnection prepareRequest(String link) throws IOException {

        URL url = new URL(link);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(5000);
        urlConnection.setConnectTimeout(5000);

        logger.info("Request to [" + urlConnection.getURL().toString() + "] was created.");
        return urlConnection;
    }


    @Override
    public String getResponse(String link) {

        String responseStr = Strings.EMPTY;

        try {
            HttpURLConnection urlConnection = prepareRequest(link);
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
            headers.put("accept-encoding", "text/html; charset=UTF-8");
            headers.put("accept-language", "en-US;q=0.9,en;q=0.8");
            headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");

            for (Map.Entry<String, String> header : headers.entrySet()) {
                urlConnection.setRequestProperty(header.getKey(), header.getValue());
            }

            try {
                int responseCode = urlConnection.getResponseCode();

                if (responseCode >= HttpURLConnection.HTTP_OK && responseCode < HttpURLConnection.HTTP_BAD_REQUEST) {
                    logger.info("Request is successful - [" + responseCode + "] http code.");

                    BufferedReader input = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String nextString;
                    while ((nextString = input.readLine()) != null) {
                        response.append(nextString);
                    }
                    input.close();
                    responseStr = response.toString();
                } else {

                    logger.warn("Request is not successful - [" + responseCode + "] http code.");
                }

            } catch (
                    IOException e) {
                logger.warn("Exception during response processing occurred - [" + e.getMessage() + "].");
            }


        } catch (IOException e) {
            logger.warn("Exception during response crafting occurred - [" + e.getMessage() + "].");
        }

        return responseStr;
    }
}
