package com.example.emrah.repository;

import com.example.emrah.entities.Campaign;
import com.example.emrah.entities.Changers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChangersRepository extends JpaRepository<Changers, Long> {
    List<Changers> findByCampaign(Campaign campaign);
}
