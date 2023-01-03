package com.example.emrah.service;


import com.example.emrah.dto.request.AddCategoryRequestDto;
import com.example.emrah.entities.Category;
import com.example.emrah.exception.CampaingNotFoundException;
import com.example.emrah.exception.CategoryNotFoundException;
import com.example.emrah.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    public Boolean add(AddCategoryRequestDto addCategoryRequestDto) {
        Category category = modelMapper.map(addCategoryRequestDto, Category.class);
        categoryRepository.save(category);
        return true;
    }

    public Category findById(long id) {
        return categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
    }
}
