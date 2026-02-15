package com.example.hotelmanagement.controllers;

import com.example.hotelmanagement.dto.RoomDto;
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
    public List<RoomDto> getAllRooms(){
        return roomService.getAllRooms();
    }

    @GetMapping("/available")
    public List<RoomDto> getAvailableRooms(){
        return roomService.getAvailableRooms();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable Long id){
        RoomDto roomById = roomService.getRoomById(id);
        return ResponseEntity.ok(roomById);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<RoomDto> updateRoomStatus(
            @PathVariable Long id,
            @RequestParam String status){
        RoomDto roomDto = roomService.updateRoomStatus(id, status);
        return ResponseEntity.ok(roomDto);
    }

    @PostMapping
    public ResponseEntity<RoomDto> createRoom(@RequestBody RoomDto roomDto){
        RoomDto room = roomService.createRoom(roomDto);
        return ResponseEntity.ok(room);
    }

    @GetMapping("type/{roomType}")
    public List<RoomDto> getRoomsByType(@PathVariable String roomType){
        return roomService.getRoomsByType(roomType);
    }

    @GetMapping("count/available")
    public long countAvailableRooms(){
        return roomService.countAvailableRooms();
    }
}
