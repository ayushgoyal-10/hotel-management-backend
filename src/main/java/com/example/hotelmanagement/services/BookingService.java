package com.example.hotelmanagement.services;

import com.example.hotelmanagement.dto.BookingRequest;
import com.example.hotelmanagement.entities.Booking;
import com.example.hotelmanagement.entities.Guest;
import com.example.hotelmanagement.entities.Room;
import com.example.hotelmanagement.exception.ResourceNotFoundException;
import com.example.hotelmanagement.repositories.BookingRepository;
import com.example.hotelmanagement.repositories.GuestRepository;
import com.example.hotelmanagement.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private RoomRepository roomRepository;

    public List<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }

    public Booking createBooking(BookingRequest request){
        // 1. finding the guest
        Optional<Guest> guest = guestRepository.findById(request.getGuestId());
        if(guest.isEmpty()){
            throw new RuntimeException("Guest not found");
        }
        // 2. finding the room
        Optional<Room> room = roomRepository.findById(request.getRoomId());
        if(room.isEmpty()){
            throw new RuntimeException("Room not found");
        }

        // 3. checking the availability of room
        if (!isRoomAvailable(request.getRoomId(), request.getCheckInDate(), request.getCheckOutDate())) {
            throw new RuntimeException("Room not available for these dates");
        }

        // 4. creating and saving the booking
        Booking booking= new Booking();
        booking.setGuest(guest.get());
        booking.setRoom(room.get());
        booking.setCheckInDate(request.getCheckInDate());
        booking.setCheckOutDate(request.getCheckOutDate());
        booking.setStatus("CONFIRMED");
        return bookingRepository.save(booking);
    }

    public boolean isRoomAvailable(Long roomId, LocalDate checkIn, LocalDate checkOut) {
        List<Booking> overlappingBookings = bookingRepository.findOverlappingBookings(roomId, checkIn, checkOut);

        return overlappingBookings.isEmpty();
    }


    public Booking checkIn(Long bookingId){
        Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);
        if(bookingOpt.isPresent()){
            Booking booking= bookingOpt.get();
            booking.setStatus("CHECKED-IN");
            booking.getRoom().setStatus("OCCUPIED");
            return bookingRepository.save(booking);
        }
        return null;
    }

    public Booking checkOut(Long bookingId){
        Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);
        if(bookingOpt.isPresent()){
            Booking booking = bookingOpt.get();
            booking.setStatus("CHECKED-OUT");
            booking.getRoom().setStatus("DIRTY");
            return bookingRepository.save(booking);
        }
        return null;
    }

    public void deleteBooking(Long id) {

        Booking booking = bookingRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));


        if(!booking.getStatus().equals("CHECKED-IN")) {
            bookingRepository.delete(booking);
        }else{
            throw new RuntimeException("Booking checked in cant be deleted");
        }
    }

}
