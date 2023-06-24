package com.openpay.marvelservice.util;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class MarvelUtil {

    @Value("${marvel.public.key}")
    private String publicKey;

    @Value("${marvel.private.key}")
    private String privateKey;

    /**
     * Generate ts + privateKey + publicKey
     * @param ts - Timestamp
     * @return Marvel required hash param
     * @throws NoSuchAlgorithmException
     */
    public String getMarvelHash(long ts) {
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

    public String getUrlWithParams(String apiUrl, long ts, String hash) {
        return apiUrl + "?apikey=" + publicKey + "&ts=" + ts + "&hash=" + hash;
    }

    public String getUrlWithParams(String apiUrl, long ts, String hash, int offset, int limit) {
        return getUrlWithParams(apiUrl, ts, hash) + "&offset=" + offset + "&limit=" + limit;
    }
}
