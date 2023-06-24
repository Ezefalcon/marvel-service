package com.openpay.marvelservice.service;


import com.openpay.marvelservice.AbstractBaseTest;
import com.openpay.marvelservice.model.dto.ResponseDto;
import com.openpay.marvelservice.util.MarvelUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


public class CharacterConnectorServiceTest extends AbstractBaseTest {

    @Mock
    private MarvelUtil marvelUtil;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CharacterConnectorService characterService;


    @Test
    public void getCharacters_shouldBeSuccessful() {

        // Arrange
        when(marvelUtil.getMarvelHash(anyLong())).thenReturn("hash");
        when(marvelUtil.getUrlWithParams(anyString(), anyLong(), anyString(), anyInt(), anyInt())).thenReturn("url");

        ResponseEntity responseEntity = mock(ResponseEntity.class);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), eq(null), eq(ResponseDto.class))).thenReturn(responseEntity);

        // Act
        characterService.getCharacters(0, 20);

        // Assert
        verify(marvelUtil).getMarvelHash(anyLong());
        verify(marvelUtil).getUrlWithParams(anyString(), anyLong(), anyString(), anyInt(), anyInt());
        verify(restTemplate).exchange(anyString(), eq(HttpMethod.GET), eq(null), eq(ResponseDto.class)); // Verify if restTemplate.exchange() is called with the expected arguments
    }

    @Test
    public void getCharacterById_shouldBeSuccessful() {

        // Arrange
        when(marvelUtil.getMarvelHash(anyLong())).thenReturn("hash");
        when(marvelUtil.getUrlWithParams(anyString(), anyLong(), anyString())).thenReturn("url");

        ResponseEntity responseEntity = mock(ResponseEntity.class);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), eq(null), eq(ResponseDto.class))).thenReturn(responseEntity);

        // Act
        characterService.getCharacter(123);

        // Assert
        verify(marvelUtil).getMarvelHash(anyLong());
        verify(marvelUtil).getUrlWithParams(anyString(), anyLong(), anyString());
        verify(restTemplate).exchange(anyString(), eq(HttpMethod.GET), eq(null), eq(ResponseDto.class)); // Verify if restTemplate.exchange() is called with the expected arguments
    }
}
