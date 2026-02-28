package com.example.hotelmanagement.controllers;

import com.example.hotelmanagement.dto.GuestDto;
import com.example.hotelmanagement.services.GuestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/guests")
public class GuestController {
    @Autowired
    private GuestService guestService;

    @GetMapping
    public List<GuestDto> getAllGuests(){
        return guestService.getAllGuests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuestDto> getGuestById(@PathVariable Long id){
        GuestDto guestById = guestService.getGuestById(id);
        return ResponseEntity.ok(guestById);
    }

    @PostMapping
    public ResponseEntity<GuestDto> createGuest(@Valid @RequestBody GuestDto guestDto){
        GuestDto guest = guestService.createGuest(guestDto);
        return ResponseEntity.ok(guest);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<GuestDto> getGuestByEmail(@PathVariable String email){
        GuestDto guestByEmail = guestService.findGuestByEmail(email);
        return ResponseEntity.ok(guestByEmail);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuestDto> updateGuestById(@PathVariable Long id, @Valid @RequestBody GuestDto guestDetails){
        GuestDto guestDto = guestService.updateGuest(id, guestDetails);
        return ResponseEntity.ok(guestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuest(@PathVariable Long id) {
        try {
            guestService.deleteGuest(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
