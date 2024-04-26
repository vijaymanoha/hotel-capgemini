package com.hotel.main;

import com.hotel.controller.HotelController;
import com.hotel.model.Booking;
import com.hotel.model.Customer;
import com.hotel.model.Room;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Instantiate your controller
        HotelController hotelController = new HotelController();
        System.out.println("Welcome to Hotel Capgemini");

        Booking booking = new Booking("CUSTOMER-5",105, LocalDate.now(), LocalDate.now().plusDays(3));
        hotelController.createBooking(booking);
        System.out.println("-------------Get All Customers in bookings--------------------------");
        List<Booking> bookings =hotelController.getAllBookings();
        bookings.stream().map(b -> b.getCustomerName()).forEach(System.out::println);

        System.out.println("-------------No of available room--------------------------");
        List<Room> rooms =hotelController.getAllAvailableRoomsByDate(LocalDate.now());
        rooms.stream().map(room -> room.getRoomNumber()).forEach(System.out::println);

        Customer customer = new Customer();
        customer.setName("CUSTOMER-5");
        System.out.println("-------------Booking--------------------------");
        List<Booking> bookings1 = hotelController.getAllBookingsByCustomer(customer);
        bookings1.stream().map(b->b.getCustomerName()).forEach(System.out::println);

    }
}