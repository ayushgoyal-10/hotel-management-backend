package com.example.hotelmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Guest guest;
    @ManyToOne
    private Room room;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String status;


}
