package com.example.hotelmanagement.repositories;

import com.example.hotelmanagement.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDate;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByRoomIdAndStatusNot(Long roomId, String status);

    @Query("SELECT b FROM Booking b WHERE b.room.id = :roomId AND b.status != 'CANCELLED' AND " +
            "(b.checkInDate BETWEEN :checkIn AND :checkOut OR " +
            "b.checkOutDate BETWEEN :checkIn AND :checkOut OR " +
            "(b.checkInDate <= :checkIn AND b.checkOutDate >= :checkOut))")
    List<Booking> findOverlappingBookings(@Param("roomId") Long roomId,
                                          @Param("checkIn") LocalDate checkIn,
                                          @Param("checkOut") LocalDate checkOut);
}
