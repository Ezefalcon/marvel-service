package com.openpay.marvelservice.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class ComicsDto {
    private int available;
    private String collectionURI;
    private List<ComicItemDto> items;
    private int returned;
}
