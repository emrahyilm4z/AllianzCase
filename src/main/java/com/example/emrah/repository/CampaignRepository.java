package com.example.emrah.repository;

import com.example.emrah.entities.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    List<Campaign> findByActiveIsFalse();

    List<Campaign> findByActiveIsTrue();
}
