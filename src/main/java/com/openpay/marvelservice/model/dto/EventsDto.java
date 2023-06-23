package com.openpay.marvelservice.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class EventsDto {
    private int available;
    private String collectionURI;
    private List<EventItemDto> items;
    private int returned;
}