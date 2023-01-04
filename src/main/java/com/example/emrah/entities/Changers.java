package com.example.emrah.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Changers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Campaign campaign;
    private String description;
    private LocalDateTime dateTime = LocalDateTime.now();

    public Changers(Campaign campaign, String description) {
        this.campaign = campaign;
        this.description = description;
    }


}
