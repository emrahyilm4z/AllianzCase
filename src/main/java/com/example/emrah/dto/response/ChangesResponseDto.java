package com.example.emrah.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChangesResponseDto {
    private String description;
    private LocalDateTime dateTime;
}
