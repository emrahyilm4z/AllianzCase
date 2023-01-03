package com.example.emrah.controller;

import com.example.emrah.dto.request.AddCampaignRequestDto;
import com.example.emrah.dto.request.AddCategoryToCampaignRequestDto;
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
    public ResponseEntity<Boolean> add(@RequestBody AddCampaignRequestDto addCampaignRequestDto) {
        return new ResponseEntity<>(campaignService.add(addCampaignRequestDto), HttpStatus.CREATED);
    }

    @PostMapping("addCategory")
    public ResponseEntity<Boolean> addCategory(@RequestBody AddCategoryToCampaignRequestDto addCategoryToCampaignRequestDto) {
        return new ResponseEntity<>(campaignService.addCategory(addCategoryToCampaignRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("active")
    public ResponseEntity<Boolean> active(@RequestParam(name = "id") long id) {
        return new ResponseEntity<>(campaignService.active(id), HttpStatus.OK);
    }
    @PutMapping("deactive")
    public ResponseEntity<Boolean> deActive(@RequestParam(name = "id") long id) {
        return new ResponseEntity<>(campaignService.deActive(id), HttpStatus.OK);
    }

}
