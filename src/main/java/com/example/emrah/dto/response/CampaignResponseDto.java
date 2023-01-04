package com.example.emrah.dto.response;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class CampaignResponseDto {
    private String title;
    private String description;
}
