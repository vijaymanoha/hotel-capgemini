package com.hotel.enums;

import java.time.LocalDate;

public enum BookingEnum {

    BOOKING_1( 0001,"CUSTOMER-1",101,LocalDate.now(), LocalDate.now().plusDays(1)),
    BOOKING_2( 0002,"CUSTOMER-2",102,LocalDate.now().plusDays(1),LocalDate.now().plusDays(3)),
    BOOKING_3( 0003,"CUSTOMER-3",103,LocalDate.now().plusDays(2),LocalDate.now().plusDays(3)),
    BOOKING_4( 0004,"CUSTOMER-4",104,LocalDate.now().plusDays(3),LocalDate.now().plusDays(3)),
    BOOKING_5( 0005,"CUSTOMER-2",105,LocalDate.now().plusDays(4),LocalDate.now().plusDays(3)),
    BOOKING_6( 0006,"CUSTOMER-1",106,LocalDate.now(),LocalDate.now().plusDays(3)),
    BOOKING_7( 0007,"CUSTOMER-1",107,LocalDate.now(),LocalDate.now().plusDays(3));


    private  String customerName;
    private  Integer roomNumber;

    private  LocalDate fromDate;
    private  LocalDate toDate;

    private Integer id;

    BookingEnum(Integer id,String customerName, Integer roomNumber, LocalDate fromDate, LocalDate toDate) {
        this.id =id;
        this.customerName = customerName;
        this.roomNumber = roomNumber;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
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

