package com.example.hotelmanagement.repositories;

import com.example.hotelmanagement.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByStatus(String available);

    List<Room> findByRoomType(String roomType);

    long countByStatus(String available);
}
