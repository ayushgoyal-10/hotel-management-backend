package com.example.hotelmanagement.services;

import com.example.hotelmanagement.dto.GuestDto;
import com.example.hotelmanagement.entities.Guest;
import com.example.hotelmanagement.exception.ResourceNotFoundException;
import com.example.hotelmanagement.repositories.GuestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GuestService {
    private ModelMapper modelMapper;
    private GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository, ModelMapper modelMapper) {
        this.guestRepository = guestRepository;
        this.modelMapper = modelMapper;
    }

    public List<GuestDto> getAllGuests(){
        List<GuestDto> guestDtos = guestRepository.findAll()
                .stream()
                .map(guest -> modelMapper.map(guest, GuestDto.class)).toList();

        return guestDtos;
    }

    public GuestDto getGuestById(Long id){
        Guest guest = guestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Guest not found"));
        return modelMapper.map(guest, GuestDto.class);
    }

    public GuestDto createGuest(GuestDto guestDto){
        Guest guest = modelMapper.map(guestDto, Guest.class);
        Guest saved = guestRepository.save(guest);
        return modelMapper.map(saved, GuestDto.class);
    }

    public GuestDto findGuestByEmail(String email){
        Guest guest = guestRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Guest not found"));
        return modelMapper.map(guest, GuestDto.class);
    }

    public GuestDto updateGuest(Long id, GuestDto guestDetails){
        Guest guest = guestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Guest not found"));
        guest.setFirstName(guestDetails.getFirstName());
        guest.setLastName(guestDetails.getLastName());
        guest.setPhone(guestDetails.getPhone());
        Guest saved = guestRepository.save(guest);

        return modelMapper.map(saved, GuestDto.class);
    }


    public void deleteGuest(Long id) {
        Guest guest = guestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Guest not found"));
        guestRepository.delete(guest);
    }
}
