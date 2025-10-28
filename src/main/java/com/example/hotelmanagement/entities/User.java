package com.example.hotelmanagement.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    public User(String username, String password, String role){
        this.username= username;
        this.password= password;
        this.role= role;
    }

    public User() {
        
    }

    public void setUsername(String username){
        this.username= username;
    }

    public String getUsername(){
        return this.username;
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id= id;
    }

    public void setRole(String role){
        this.role= role;
    }

    public String getRole(){
        return this.role;
    }

}
