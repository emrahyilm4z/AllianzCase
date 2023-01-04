package com.example.emrah.service;

import com.example.emrah.dto.request.AddCampaignRequestDto;
import com.example.emrah.dto.response.CampaignResponseDto;
import com.example.emrah.entities.Campaign;
import com.example.emrah.entities.Category;
import com.example.emrah.repository.CampaignRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CampaignServiceTest {

    private CampaignService campaignService;
    private CampaignRepository campaignRepository;
    private ChangersService changersService;
    private ModelMapper modelMapper;

    @BeforeEach
    void setup() {
        campaignRepository = Mockito.mock(CampaignRepository.class);
        modelMapper = Mockito.mock(ModelMapper.class);
        changersService = Mockito.mock(ChangersService.class);
        campaignService = new CampaignService(campaignRepository, changersService, modelMapper);
    }

    @Test
    public void testAdd_WithValidRequest_ShouldReturnCampaignResponseDto() {
        AddCampaignRequestDto addCampaignRequestDto = AddCampaignRequestDto.builder()
                .title("heheheh")
                .category(Category.OZEL_SAGLIK_SIGORTASI)
                .description("ahehahehahe")
                .build();
        Campaign campaign = new Campaign(1L, "2", "23", false, false, Category.OZEL_SAGLIK_SIGORTASI);
        CampaignResponseDto campaignResponseDto = new CampaignResponseDto("2", "23");

        Mockito.when(campaignRepository.save(campaign)).thenReturn(campaign);


        CampaignResponseDto result = campaignService.add(addCampaignRequestDto);
        assertEquals(result, campaignResponseDto);
        assertThat(campaign).isNotNull();
    }

}
