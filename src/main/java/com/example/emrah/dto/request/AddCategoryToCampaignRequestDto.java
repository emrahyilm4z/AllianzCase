package com.example.emrah.dto.request;

import lombok.Data;

@Data
public class AddCategoryToCampaignRequestDto {
    private Long campaignId;
    private Long categoryId;
}
