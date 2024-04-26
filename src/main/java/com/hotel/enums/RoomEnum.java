package com.hotel.enums;
public enum RoomEnum {

    ROOM_101(101,"DELUXE"),
    ROOM_102(102,"DELUXE"),
    ROOM_103(103,"DELUXE"),
    ROOM_104(104,"SUPER DELUXE"),
    ROOM_105(105,"SUPER DELUXE"),
    ROOM_106(106,"SUPER DELUXE"),
    ROOM_107(107,"DELUXE");




    public Integer getRoomNumber() {
        return roomNumber;
    }

    private  Integer roomNumber;

    public String getRoomType() {
        return roomType;
    }

    private  String roomType;


    private RoomEnum(Integer roomNumber,String roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
    }

}