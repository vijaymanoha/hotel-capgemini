package com.hotel.model;

public class Room {

    private Integer id;
    private int roomNumber;

    private String roomType;

    public Room(int roomNumber, String roomType) {
        this.id = generateRandomId();
        this.roomNumber = roomNumber;
        this.roomType = roomType;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public Integer getId() {
        return id;
    }
    private int generateRandomId() {
        return (int) (Math.random() * 10000000);
    }

    public String toString() {
        return "id: " + id + ", name: " + roomNumber+", roomType: " + roomType ;
    }

}