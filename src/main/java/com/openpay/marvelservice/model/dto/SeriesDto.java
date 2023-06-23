package com.openpay.marvelservice.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class SeriesDto {
    private int available;
    private String collectionURI;
    private List<SeriesItemDto> items;
    private int returned;
}