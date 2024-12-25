package org.app.bookingbus.model.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
@Data
public class Company extends BaseEntity{
    private String companyName;
    private String companyAddress;
    private String email;
    private String phoneNumber;
    private String representativeName;
    private int numberOfVehicles;
    private boolean isActive;
}
