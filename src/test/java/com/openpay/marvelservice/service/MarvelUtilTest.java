package com.openpay.marvelservice.service;

import com.openpay.marvelservice.AbstractBaseTest;
import com.openpay.marvelservice.util.MarvelUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MarvelUtilTest extends AbstractBaseTest {

    private MarvelUtil marvelUtil;

    private final String hashExample = "af88ddc2a05b4210fb931f7b15929d52";

    @BeforeAll
    public void init() {
        marvelUtil = new MarvelUtil("test", "test");
    }

    @Test
    public void getMarvelHash_shouldReturnHashedString() {
        String marvelHash = marvelUtil.getMarvelHash(1l);

        assertEquals(marvelHash, hashExample);
    }

    @Test
    public void getUrlWithParams_shouldReturnBuiltUrl() {
        String apiUrl = marvelUtil.getUrlWithParams("apiUrl", 1, hashExample);

        assertEquals(apiUrl, "apiUrl?apikey=test&ts=1&hash=af88ddc2a05b4210fb931f7b15929d52");
    }
}
