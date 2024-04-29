package com.hotel.enums;
public enum RoomEnum {

    ROOM_101(0001,101,"DELUXE"),
    ROOM_102(0002,102,"DELUXE"),
    ROOM_103(0003,103,"DELUXE"),
    ROOM_104(0004,104,"SUPER DELUXE"),
    ROOM_105(0005,105,"SUPER DELUXE"),
    ROOM_106(0006,106,"SUPER DELUXE"),
    ROOM_107(0007,107,"DELUXE");




    public Integer getId() {
        return id;
    }
    private Integer id;
    public Integer getRoomNumber() {
        return roomNumber;
    }

    private  Integer roomNumber;

    public String getRoomType() {
        return roomType;
    }

    private  String roomType;


    private RoomEnum(Integer id, Integer roomNumber,String roomType) {
       this.id = id;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
    }

}