package com.hotel.enums;

import com.hotel.model.Customer;
import com.hotel.model.Room;

import java.time.LocalDate;

public enum BookingEnum {


//    BOOKING_1( 0001,CustomerEnum.CUSTOMER_0001,RoomEnum.ROOM_101,LocalDate.of(2024,05,1), LocalDate.of(2024,05,5)),
    BOOKING_2( 0002,CustomerEnum.CUSTOMER_0002,RoomEnum.ROOM_102,LocalDate.of(2024,05,4),LocalDate.of(2024,05,15)),
    BOOKING_3( 0003,CustomerEnum.CUSTOMER_0003,RoomEnum.ROOM_103,LocalDate.of(2024,05,8),LocalDate.of(2024,05,11)),
    BOOKING_4( 0004,CustomerEnum.CUSTOMER_0004,RoomEnum.ROOM_104,LocalDate.of(2024,05,11),LocalDate.of(2024,05,13)),
    BOOKING_5( 0005,CustomerEnum.CUSTOMER_0002,RoomEnum.ROOM_105,LocalDate.of(2024,05,10),LocalDate.of(2024,05,11));
//    BOOKING_6( 0006,CustomerEnum.CUSTOMER_0001,RoomEnum.ROOM_106,LocalDate.of(2024,05,13),LocalDate.of(2024,05,15)),
//    BOOKING_7( 0007,CustomerEnum.CUSTOMER_0001,RoomEnum.ROOM_107,LocalDate.of(2024,05,1),LocalDate.of(2024,05,9));


    private  CustomerEnum customerEnum;
    private  RoomEnum roomEnum;

    private  LocalDate fromDate;
    private  LocalDate toDate;

    private Integer id;

    BookingEnum(Integer id, CustomerEnum customerEnum, RoomEnum roomEnum, LocalDate fromDate, LocalDate toDate) {
        this.id =id;
        this.customerEnum = customerEnum;
        this.roomEnum = roomEnum;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public CustomerEnum getCustomerEnum() {
        return customerEnum;
    }

    public void setCustomerEnum(CustomerEnum customerEnum) {
        this.customerEnum = customerEnum;
    }

    public RoomEnum getRoomEnum() {
        return roomEnum;
    }

    public void setRoomEnum(RoomEnum roomEnum) {
        this.roomEnum = roomEnum;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

