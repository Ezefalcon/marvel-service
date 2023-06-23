package com.openpay.marvelservice.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDto {
    private int code;
    private String status;
    private String copyright;
    private String attributionText;
    private String attributionHTML;
    private String etag;
    private DataDto data;
}
