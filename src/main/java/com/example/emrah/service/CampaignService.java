package com.example.emrah.service;

import com.example.emrah.dto.request.AddCampaignRequestDto;
import com.example.emrah.dto.request.AddCategoryToCampaignRequestDto;
import com.example.emrah.entities.Campaign;
import com.example.emrah.entities.Category;
import com.example.emrah.exception.CampaingNotFoundException;
import com.example.emrah.repository.CampaignRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class CampaignService {
    private CampaignRepository campaignRepository;
    private CategoryService categoryService;
    private ModelMapper modelMapper;

    public Boolean add(AddCampaignRequestDto addCampaignRequestDto) {
        Campaign campaign = modelMapper.map(addCampaignRequestDto, Campaign.class);
        campaign.setMukerrer(campaignRepository.findAll().stream().anyMatch(element -> element.hashCode() == campaign.hashCode()));
        campaignRepository.save(campaign);
        return true;
    }

    public Boolean addCategory(AddCategoryToCampaignRequestDto addCategoryToCampaignRequestDto) {
        Category category = categoryService.findById(addCategoryToCampaignRequestDto.getCategoryId());
        Campaign campaign = campaignRepository.findById(addCategoryToCampaignRequestDto.getCampaignId()).orElseThrow();
        Set<Category> categories = campaign.getCategories();
        categories.add(category);
        campaign.setCategories(categories);
        campaignRepository.save(campaign);
        return true;
    }

    public Boolean active(long id) {
        Campaign campaign = campaignRepository.findById(id).orElseThrow(CampaingNotFoundException::new);
        campaign.setActive(!campaign.isMukerrer());
        campaignRepository.save(campaign);
        return campaign.isActive();
    }

    public Boolean deActive(long id) {
        Campaign campaign = campaignRepository.findById(id).orElseThrow(CampaingNotFoundException::new);
        campaign.setActive(false);
        campaignRepository.save(campaign);
        return !campaign.isActive();
    }
}
