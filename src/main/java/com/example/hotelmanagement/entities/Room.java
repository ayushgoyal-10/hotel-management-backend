package com.example.hotelmanagement.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String roomNumber;

    private String roomType;

    private String status;

    private Double price;

    public Room(){

    }

    public Room(String roomNumber, String status, Double price){    
        this.roomNumber= roomNumber;
        this.status= status;
        this.price= price;
    }

    public void setId(Long id){
        this.id= id;
    }

    public Long getId(){
        return this.id;
    }

    public void setRoomNumber(String rn){
        this.roomNumber=rn;
    }

    public String getRoomNumber(){
        return this.roomNumber;
    }

    public void setRoomType(String roomType){
        this.roomType= roomType;
    }

    public String getRoomType(){
        return this.roomType;
    }

    public void setStatus(String status){
        this.status= status;
    }

    public String getStatus(){
        return this.status;
    }

    public void setPrice(Double price){
        this.price= price;
    }

    public Double getPrice(){
        return this.price;
    }

}
