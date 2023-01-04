package com.example.emrah.controller;


import com.example.emrah.dto.request.AddCampaignRequestDto;
import com.example.emrah.dto.request.CampaignRequestDto;
import com.example.emrah.dto.response.CampaignResponseDto;
import com.example.emrah.dto.response.StatisticsResponseDto;
import com.example.emrah.service.CampaignService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("campaign")
public class CampaignController {
    private CampaignService campaignService;

    @PostMapping("add")
    public ResponseEntity<CampaignResponseDto> add(@RequestBody AddCampaignRequestDto addCampaignRequestDto) {
        return new ResponseEntity<>(campaignService.add(addCampaignRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("category/activate")
    public ResponseEntity<?> activateCategory(@RequestBody CampaignRequestDto campaignRequestDto) {
        return new ResponseEntity<>(campaignService.categoryActivate(campaignRequestDto), HttpStatus.OK);
    }

    @PutMapping("activateAndDeactivate")
    public ResponseEntity<?> activateAndDeactivate(@RequestBody CampaignRequestDto campaignRequestDto) {
        return new ResponseEntity<>(campaignService.activateAndDeactivate(campaignRequestDto), HttpStatus.OK);
    }

    @GetMapping("statistics")
    public ResponseEntity<StatisticsResponseDto> getStatistics() {
        return new ResponseEntity<>(campaignService.getAll(), HttpStatus.OK);
    }

    @GetMapping("changes")
    public ResponseEntity<?> getChanges(@RequestBody CampaignRequestDto campaignRequestDto) {
        return new ResponseEntity<>(campaignService.getChanges(campaignRequestDto), HttpStatus.OK);
    }
}
