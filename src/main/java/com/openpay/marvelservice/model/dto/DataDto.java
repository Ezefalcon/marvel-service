package com.openpay.marvelservice.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class DataDto {
    private int offset;
    private int limit;
    private int total;
    private int count;
    private List<CharacterDto> results;
}
