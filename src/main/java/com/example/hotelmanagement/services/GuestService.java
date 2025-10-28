package com.example.hotelmanagement.services;

import com.example.hotelmanagement.entities.Guest;
import com.example.hotelmanagement.repositories.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GuestService {
    @Autowired
    private GuestRepository guestRepository;

    public List<Guest> getAllGuests(){
        return guestRepository.findAll();
    }

    public Optional<Guest> getGuestById(Long id){
        return guestRepository.findById(id);
    }

    public Guest createGuest(Guest guest){
        return guestRepository.save(guest);
    }

    public Optional<Guest> findGuestByEmail(String email){
        return guestRepository.findByEmail(email);
    }

    public Guest updateGuest(Long id, Guest guestDetails){
        Optional<Guest> guestOpt = guestRepository.findById(id);
        if(guestOpt.isPresent()){
            Guest guest= guestOpt.get();
            guest.setFirstName(guestDetails.getFirstName());
            guest.setLastName(guestDetails.getLastName());
            guest.setPhone(guestDetails.getPhone());
            return guestRepository.save(guest);
        }
        return null;
    }


}
