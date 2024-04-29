package com.hotel.model;

import java.time.LocalDate;

public class Booking {
    private Integer id;
    private Customer customer;
    private Room room;


    private LocalDate fromDate;
    private LocalDate toDate;

    public Booking(Customer customer, Room room, LocalDate fromDate, LocalDate toDate) {
        this.id = generateRandomId();
        this.customer = customer;
        this.room = room;
        this.fromDate = fromDate;
        this.toDate =toDate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Room getRoom() {
        return room;
    }

    public Integer getId() {
        return id;
    }
    private int generateRandomId() {
        return (int) (Math.random() * 10000000);
    }

    public String toString() {
        return "id: " + id +", customer name: " + customer.getName() +", room number : " + room.getRoomNumber() + ", from: " + fromDate + ", to: " + toDate;
    }

}
