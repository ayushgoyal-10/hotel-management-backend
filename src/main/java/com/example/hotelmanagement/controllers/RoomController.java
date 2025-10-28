package com.example.hotelmanagement.controllers;

import com.example.hotelmanagement.entities.Room;
import com.example.hotelmanagement.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping
    public List<Room> getAllRooms(){
        return roomService.getAllRooms();
    }

    @GetMapping("/available")
    public List<Room> getAvailableRooms(){
        return roomService.getAvailableRooms();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id){
        Optional<Room> room= roomService.getRoomById(id);
        if(room.isPresent()){
            return ResponseEntity.ok(room.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Room> updateRoomStatus(
            @PathVariable Long id,
            @RequestParam String status){
        Room updateRoom = roomService.updateRoomStatus(id, status);
        return updateRoom!=null ? ResponseEntity.ok(updateRoom): ResponseEntity.notFound().build();
    }

    @PostMapping
    public Room createRoom(@RequestBody Room room){
        return roomService.createRoom(room);
    }

    @GetMapping("type/{roomType}")
    public List<Room> getRoomsByType(@PathVariable String roomType){
        return roomService.getRoomsByType(roomType);
    }

    @GetMapping("count/available")
    public long countAvailableRooms(){
        return roomService.countAvailableRooms();
    }
}
