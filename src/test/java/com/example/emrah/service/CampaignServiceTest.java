package com.example.emrah.service;

import com.example.emrah.dto.request.AddCampaignRequestDto;
import com.example.emrah.dto.request.CampaignRequestDto;
import com.example.emrah.dto.response.CampaignResponseDto;
import com.example.emrah.dto.response.ChangesResponseDto;
import com.example.emrah.dto.response.StatisticsResponseDto;
import com.example.emrah.entities.Campaign;
import com.example.emrah.entities.Category;
import com.example.emrah.repository.CampaignRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        AddCampaignRequestDto addCampaignRequestDto = AddCampaignRequestDto.builder().title("heheheh").category(Category.OZEL_SAGLIK_SIGORTASI).description("ahehahehahe").build();

        Campaign campaign = new Campaign(1L, "heheheh", "ahehahehahe", false, false, Category.OZEL_SAGLIK_SIGORTASI);
        CampaignResponseDto campaignResponseDto = new CampaignResponseDto("heheheh", "ahehahehahe");


        Mockito.when(modelMapper.map(addCampaignRequestDto, Campaign.class)).thenReturn(campaign);
        Mockito.when(campaignRepository.save(campaign)).thenReturn(campaign);
        Mockito.when(modelMapper.map(campaign, CampaignResponseDto.class)).thenReturn(campaignResponseDto);
        CampaignResponseDto result = campaignService.add(addCampaignRequestDto);
        assertEquals(result, campaignResponseDto);

    }

    @Test
    public void testCategoryActivate() {
        CampaignRequestDto campaignRequestDto = new CampaignRequestDto(1L);
        Campaign campaign = new Campaign(1L, "heheheh", "ahehahehahe", false, false, Category.OZEL_SAGLIK_SIGORTASI);
        CampaignResponseDto campaignResponseDto = new CampaignResponseDto("heheheh", "ahehahehahe");
        Long id = 1L;

        Mockito.when(campaignRepository.findById(id)).thenReturn(Optional.of(campaign));
        Mockito.when(campaignRepository.save(campaign)).thenReturn(campaign);
        Mockito.when(modelMapper.map(campaign, CampaignResponseDto.class)).thenReturn(campaignResponseDto);

        CampaignResponseDto result = campaignService.categoryActivate(campaignRequestDto);

        assertEquals(result, campaignResponseDto);

    }


    @Test
    public void testGetAll() {
        StatisticsResponseDto statisticsResponseDto = new StatisticsResponseDto(2, 1);

        Campaign campaign = new Campaign();
        List<Campaign> act = new ArrayList<>(2);
        act.add(campaign);
        act.add(campaign);
        List<Campaign> dec = new ArrayList<>(1);
        dec.add(campaign);

        Mockito.when(campaignRepository.findByActiveIsTrue()).thenReturn(act);
        Mockito.when(campaignRepository.findByActiveIsFalse()).thenReturn(dec);

        StatisticsResponseDto result = campaignService.getAll();
        assertEquals(result, statisticsResponseDto);
    }

    @Test
    public void testGettChanges() {
        CampaignRequestDto campaignRequestDto = new CampaignRequestDto(1L);
        Campaign campaign = new Campaign(1L, "heheheh", "ahehahehahe", false, false, Category.OZEL_SAGLIK_SIGORTASI);

        List<ChangesResponseDto> arr = new ArrayList<>();

        Mockito.when(campaignRepository.findById(1L)).thenReturn(Optional.of(campaign));
        Mockito.when(changersService.findByCampaign(campaign)).thenReturn(arr);
        List<ChangesResponseDto> result = campaignService.getChanges(campaignRequestDto);

        assertEquals(result, arr);

    }

    @Test
    public void testactivateAndDeactivate() {
        CampaignRequestDto campaignRequestDto = new CampaignRequestDto(1L);
        Campaign campaign = new Campaign(1L, "heheheh", "ahehahehahe", false, false, Category.OZEL_SAGLIK_SIGORTASI);

        CampaignResponseDto campaignResponseDto = new CampaignResponseDto("heheheh", "ahehahehahe");


        Mockito.when(campaignRepository.findById(1L)).thenReturn(Optional.of(campaign));
        Mockito.when(campaignRepository.save(campaign)).thenReturn(campaign);
        Mockito.when(modelMapper.map(campaign, CampaignResponseDto.class)).thenReturn(campaignResponseDto);

        CampaignResponseDto result = campaignService.activateAndDeactivate(campaignRequestDto);
        assertEquals(result, campaignResponseDto);

    }
}
