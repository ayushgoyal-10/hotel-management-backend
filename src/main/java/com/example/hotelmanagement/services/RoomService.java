package com.example.hotelmanagement.services;

import com.example.hotelmanagement.entities.Room;
import com.example.hotelmanagement.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    public List<Room> getAvailableRooms(){
        return roomRepository.findByStatus("AVAILABLE");
    }

    public Optional<Room> getRoomById(Long id){
        return roomRepository.findById(id);
    }

    public Room updateRoomStatus(Long id, String newStatus){
        Optional<Room> foundRoom = roomRepository.findById(id);
        if(foundRoom.isPresent()){
            Room room= foundRoom.get();
            room.setStatus(newStatus);
            return roomRepository.save(room);
        }
        return null;
    }

    public Room createRoom(Room room){
        return roomRepository.save(room);
    }

    public List<Room> getRoomsByType(String roomType){
        return roomRepository.findByRoomType(roomType);
    }

    public long countAvailableRooms(){
        return roomRepository.countByStatus("AVAILABLE");
    }

}
