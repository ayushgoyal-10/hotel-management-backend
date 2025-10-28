package com.example.hotelmanagement.controllers;

import com.example.hotelmanagement.dto.BookingRequest;
import com.example.hotelmanagement.entities.Booking;
import com.example.hotelmanagement.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public List<Booking> getAllBookings(){
        return bookingService.getAllBookings();
    }

    @PostMapping
    public Booking createBooking(@RequestBody BookingRequest request){
        return bookingService.createBooking(request);
    }

    @PutMapping("/{id}/checkin")
    public ResponseEntity<Booking> checkIn(@PathVariable Long id){
        Booking booking = bookingService.checkIn(id);
        if(booking!=null){
            return ResponseEntity.ok(booking);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/checkout")
    public ResponseEntity<Booking> checkOut(@PathVariable Long id){
        Booking booking = bookingService.checkOut(id);
        if(booking!=null){
            return ResponseEntity.ok(booking);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
