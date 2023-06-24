package com.openpay.marvelservice.service;

import com.openpay.marvelservice.model.dto.ResponseDto;
import com.openpay.marvelservice.util.MarvelUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;

@Service
public class CharacterConnectorService {

    @Value("${marvel.base.url}")
    private String baseUrl;

    private RestTemplate restTemplate;

    private MarvelUtil marvelUtil;

    public CharacterConnectorService(RestTemplate restTemplate, MarvelUtil marvelUtil) {
        this.restTemplate = restTemplate;
        this.marvelUtil = marvelUtil;
    }

    public ResponseEntity<ResponseDto> getCharacters(int offset, int limit) {
        String apiUrl = baseUrl + "/characters";

        long ts = new Timestamp(System.currentTimeMillis()).getTime();
        String hash = marvelUtil.getMarvelHash(ts);

        String url = marvelUtil.getUrlWithParams(apiUrl, ts, hash, offset, limit);

        return restTemplate.exchange(url, HttpMethod.GET, null, ResponseDto.class);
    }

    public ResponseEntity<ResponseDto> getCharacter(int characterId) {
        String apiUrl = baseUrl + "/characters/" + characterId;

        long ts = new Timestamp(System.currentTimeMillis()).getTime();
        String hash = marvelUtil.getMarvelHash(ts);

        String url = marvelUtil.getUrlWithParams(apiUrl, ts, hash);

        ResponseEntity<ResponseDto> exchange = restTemplate.exchange(url, HttpMethod.GET, null, ResponseDto.class);
        return exchange;
    }
}
