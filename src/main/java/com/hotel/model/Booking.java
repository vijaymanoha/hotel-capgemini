package com.hotel.model;

import java.time.LocalDate;

public class Booking {
    private Integer id;
    private String customerName;
    private Integer roomNumber;


    private LocalDate fromDate;
    private LocalDate toDate;

    public Booking(String customerName, Integer roomNumber, LocalDate fromDate, LocalDate toDate) {
        this.id = generateRandomId();
        this.customerName = customerName;
        this.roomNumber = roomNumber;
        this.fromDate = fromDate;
        this.toDate =toDate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public Integer getId() {
        return id;
    }
    private int generateRandomId() {
        return (int) (Math.random() * 10000000);
    }

    public String toString() {
        return "id: " + id + ", from: " + fromDate + ", to: " + toDate;
    }

}
