package com.example.hotelmanagement.services;

import com.example.hotelmanagement.dto.RoomDto;
import com.example.hotelmanagement.entities.Room;
import com.example.hotelmanagement.repositories.RoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private ModelMapper modelMapper;
    private RoomRepository roomRepository;

    public RoomService(ModelMapper modelMapper, RoomRepository roomRepository) {
        this.modelMapper = modelMapper;
        this.roomRepository = roomRepository;
    }

    public List<RoomDto> getAllRooms(){
        List<RoomDto> roomDtos = roomRepository.findAll()
                .stream()
                .map(room -> modelMapper.map(room, RoomDto.class)).toList();

        return roomDtos;
    }

    public List<RoomDto> getAvailableRooms(){
        List<RoomDto> roomDtos = roomRepository.findByStatus("AVAILABLE")
                .stream()
                .map(room -> modelMapper.map(room, RoomDto.class)).toList();
        return roomDtos;
    }

    public RoomDto getRoomById(Long id){
        Room room = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
        return modelMapper.map(room, RoomDto.class);
    }

    public RoomDto updateRoomStatus(Long id, String newStatus) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
        room.setStatus(newStatus);
        Room saved = roomRepository.save(room);
        return modelMapper.map(room, RoomDto.class);
    }

    public RoomDto createRoom(RoomDto roomDto){
        Room room = modelMapper.map(roomDto, Room.class);
        Room saved = roomRepository.save(room);
        return modelMapper.map(saved, RoomDto.class);
    }

    public List<RoomDto> getRoomsByType(String roomType){
        List<RoomDto> roomDtos = roomRepository.findByRoomType(roomType)
                .stream()
                .map(room -> modelMapper.map(room, RoomDto.class)).toList();
        return roomDtos;
    }

    public long countAvailableRooms(){
        return roomRepository.countByStatus("AVAILABLE");
    }

}
