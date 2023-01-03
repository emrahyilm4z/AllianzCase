package com.example.emrah.controller;

import com.example.emrah.dto.request.AddCategoryRequestDto;
import com.example.emrah.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("category")
public class CategoryController {
    private CategoryService categoryService;

    @PostMapping("add")
    public ResponseEntity<Boolean> add(@RequestBody AddCategoryRequestDto addCategoryRequestDto) {
        return new ResponseEntity<>(categoryService.add(addCategoryRequestDto), HttpStatus.CREATED);
    }
}
