package com.hotel.controller;

import com.hotel.model.Booking;
import com.hotel.model.Customer;
import com.hotel.model.Room;
import com.hotel.service.BookingService;
import com.hotel.service.CustomerService;

import java.time.LocalDate;
import java.util.List;

public class HotelController {
    private final CustomerService customerService;

    private final BookingService bookingService;

    public HotelController() {
        this.customerService = new CustomerService();
        this.bookingService = new BookingService();
    }

    public void createBooking(Booking booking) {
          bookingService.createBooking(booking);
    }

    public List<Room> getAllAvailableRoomsByDate(LocalDate fromDate , LocalDate toDate) {
        return bookingService.getAllAvailableRoomsByDate(fromDate,toDate);
    }


    public List<Booking> getAllBookingsByCustomer(String customerName){
        return bookingService.getAllBookingsByCustomer(customerName);
    }
}