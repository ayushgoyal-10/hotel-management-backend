package com.example.hotelmanagement.controllers;

import com.example.hotelmanagement.entities.Guest;
import com.example.hotelmanagement.services.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/guests")
public class GuestController {
    @Autowired
    private GuestService guestService;

    @GetMapping
    public List<Guest> getAllGuests(){
        return guestService.getAllGuests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guest> getGuestById(@PathVariable Long id){
        Optional<Guest> guestById = guestService.getGuestById(id);
        if(guestById.isPresent()){
            return ResponseEntity.ok(guestById.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Guest createGuest(@RequestBody Guest guest){
        return guestService.createGuest(guest);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Guest> getGuestByEmail(@PathVariable String email){
        Optional<Guest> guestByEmail = guestService.findGuestByEmail(email);
        if(guestByEmail.isPresent()){
            return ResponseEntity.ok(guestByEmail.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Guest> updateGuestById(@PathVariable Long id, @RequestBody Guest guestDetails){
        Guest guest = guestService.updateGuest(id, guestDetails);
        if(guest!=null){
            return ResponseEntity.ok(guest);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
