package com.example.emrah.service;

import com.example.emrah.dto.response.ChangesResponseDto;
import com.example.emrah.entities.Campaign;
import com.example.emrah.entities.Changers;
import com.example.emrah.repository.ChangersRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChangersService {
    private final ChangersRepository changersRepository;
    private final ModelMapper modelMapper;

    public void save(Changers changers) {
        changersRepository.save(changers);
    }

    public List<ChangesResponseDto> findByCampaign(Campaign campaign) {
        return changersRepository.findByCampaign(campaign).stream().map(item -> modelMapper.map(item, ChangesResponseDto.class)).toList();
    }
}
