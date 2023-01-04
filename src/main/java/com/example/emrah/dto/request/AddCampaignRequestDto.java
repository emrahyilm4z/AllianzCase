package com.example.emrah.dto.request;

import com.example.emrah.entities.Category;
import lombok.Data;

@Data
public class AddCampaignRequestDto {
    private String title;
    private String description;
    private Category category;
}
