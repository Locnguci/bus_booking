package org.app.bookingbus.model.entities;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;
import org.app.bookingbus.enums.DistrictEnum;

import java.util.List;

@Entity
@Data
@ToString
public class District {
    @Id
    private Long id;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<DistrictEnum> districts;

}
