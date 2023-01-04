package com.example.emrah.entities;

import jakarta.persistence.*;
import lombok.*;
import javax.validation.constraints.Size;
import java.util.Objects;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(min = 10, max = 50)
    private String title;
    @Size(min = 20, max = 200)
    private String description;
    private boolean mukerrer = false;
    private boolean active = false;
    private Category category;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Campaign campaign)) return false;
        return getTitle().equals(campaign.getTitle()) && getDescription().equals(campaign.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getDescription());
    }
}
