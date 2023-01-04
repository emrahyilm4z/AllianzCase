package com.example.emrah.service;

import com.example.emrah.dto.request.AddCampaignRequestDto;
import com.example.emrah.dto.request.CampaignRequestDto;
import com.example.emrah.dto.response.CampaignResponseDto;
import com.example.emrah.dto.response.ChangesResponseDto;
import com.example.emrah.dto.response.StatisticsResponseDto;
import com.example.emrah.entities.Campaign;
import com.example.emrah.entities.Changers;
import com.example.emrah.exception.CampaingNotFoundException;
import com.example.emrah.exception.NotChangeableException;
import com.example.emrah.repository.CampaignRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CampaignService {
    private CampaignRepository campaignRepository;
    private ChangersService changersService;
    private ModelMapper modelMapper;

    public Boolean add(AddCampaignRequestDto addCampaignRequestDto) {
        Campaign campaign = modelMapper.map(addCampaignRequestDto, Campaign.class);
        campaign.setMukerrer(campaignRepository.findAll().stream().anyMatch(element -> element.hashCode() == campaign.hashCode()));
        if (campaign.getCategory().name().equals("HAYAT_SIGORTASI")) {
            campaign.setActive(true);
        }
        campaignRepository.save(campaign);
        return true;
    }


    public CampaignResponseDto categoryActivate(CampaignRequestDto campaignRequestDto) {
        Campaign campaign = campaignRepository.findById(campaignRequestDto.getId()).orElseThrow(CampaingNotFoundException::new);
        mukerrerControl(campaign);
        if (!campaign.getCategory().isStatus()) {
            campaign.getCategory().setStatus(true);
            Changers changer = new Changers(campaign, "Kategori aktif edildi.");
            campaignRepository.save(campaign);
            changersService.save(changer);
        }
        return modelMapper.map(campaign, CampaignResponseDto.class);
    }

    public StatisticsResponseDto getAll() {
        return new StatisticsResponseDto(campaignRepository.findByActiveIsTrue().size(), campaignRepository.findByActiveIsFalse().size());
    }

    public List<ChangesResponseDto> getChanges(CampaignRequestDto campaignRequestDto) {
        Campaign campaign = campaignRepository.findById(campaignRequestDto.getId()).orElseThrow(CampaingNotFoundException::new);
        return changersService.findByCampaign(campaign);
    }

    private static void mukerrerControl(Campaign campaign) {
        if (campaign.isMukerrer()) {
            throw new NotChangeableException();
        }
    }

    public CampaignResponseDto activateAndDeactivate(CampaignRequestDto campaignRequestDto) {
        Campaign campaign = campaignRepository.findById(campaignRequestDto.getId()).orElseThrow(CampaingNotFoundException::new);
        mukerrerControl(campaign);
        String status;
        if (!campaign.isActive()) {
            campaign.setActive(true);
            campaignRepository.save(campaign);
            status = "Kampanya aktif edildi";
        } else {
            campaign.setActive(false);
            campaignRepository.save(campaign);
            status = "Kampanya devre dışı bırakıldı";
        }
        Changers changer = new Changers(campaign, status);
        changersService.save(changer);
        return modelMapper.map(campaign, CampaignResponseDto.class);
    }
}
