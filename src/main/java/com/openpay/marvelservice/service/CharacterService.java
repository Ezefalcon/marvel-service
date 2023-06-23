package com.openpay.marvelservice.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
public class CharacterService {

    @Value("${marvel.public.key}")
    private String publicKey;

    @Value("${marvel.private.key}")
    private String privateKey;

    @Value("${marvel.base.url}")
    private String baseUrl;

    private RestTemplate restTemplate;

    public CharacterService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Generate ts + privateKey + publicKey
     * @param ts - Timestamp
     * @return Marvel required hash param
     * @throws NoSuchAlgorithmException
     */
    private String getMarvelHash(long ts) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");

            StringBuilder hexString = new StringBuilder();
            md5.update((ts + privateKey + publicKey).getBytes());
            for (byte b : md5.digest()) {
                String h = Integer.toHexString(0xFF & b);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error trying to get MD5 instance");
        }
    }

    public String getCharacters() {
        String apiUrl = baseUrl + "/characters";

        long ts = new Timestamp(System.currentTimeMillis()).getTime();
        String hash = getMarvelHash(ts);

        String fullUrl = apiUrl + "?apikey=" + this.publicKey + "&ts=" + ts + "&hash=" + hash;

        ResponseEntity<String> response = restTemplate.exchange(fullUrl, HttpMethod.GET, null, String.class);

        // Get the response body
        String responseBody = response.getBody();

        // Print the response body
        System.out.println(responseBody);

        return responseBody;
    }
}
