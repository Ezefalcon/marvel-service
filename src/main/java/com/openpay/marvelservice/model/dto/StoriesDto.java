package com.openpay.marvelservice.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class StoriesDto {
    private int available;
    private String collectionURI;
    private List<StoryItemDto> items;
    private int returned;
}