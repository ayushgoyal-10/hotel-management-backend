package com.example.hotelmanagement.repositories;

import com.example.hotelmanagement.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByRoomIdAndStatusNot(Long roomId, String status);
}
