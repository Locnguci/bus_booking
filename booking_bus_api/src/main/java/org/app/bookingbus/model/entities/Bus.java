package org.app.bookingbus.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
@Data
public class Bus extends BaseEntity {
    private String busName;
    private double price;
    private boolean isAvailable;
    private LocalTime startTime;
    private LocalTime returnTime;
    private int totalSeats;
    private int seatAvailable;
    @OneToMany(mappedBy = "bus")
    private List<Ticket> tickets;
    @OneToOne(mappedBy = "bus")
    private BusRoutine busRoutine;
}
