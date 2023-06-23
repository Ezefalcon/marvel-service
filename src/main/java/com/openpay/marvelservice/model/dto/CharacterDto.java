package com.openpay.marvelservice.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class CharacterDto {
    private int id;
    private String name;
    private String description;
    private String modified;
    private ThumbnailDto thumbnail;
    private String resourceURI;
    private ComicsDto comics;
    private SeriesDto series;
    private StoriesDto stories;
    private EventsDto events;
    private List<UrlDto> urls;
}