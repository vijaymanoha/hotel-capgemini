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

        System.out.println("-------------Booking creation--------------------------");
        Customer customer = new Customer("CUSTOMER-7");
        Room room =new Room(102,"DELUXE");

        Booking booking = new Booking(customer,room, LocalDate.of(2024,05,1), LocalDate.of(2024,05,2));
        hotelController.createBooking(booking);

        System.out.println("-------------No of available room--------------------------");
        List<Room> rooms =hotelController.getAllAvailableRoomsByDate(LocalDate.of(2024,05,1),LocalDate.of(2024,05,2));
        rooms.stream().map(r -> r.toString()).forEach(System.out::println);


        System.out.println("-------------Booking by customer name--------------------------");
        List<Booking> bookings1 = hotelController.getAllBookingsByCustomer("CUSTOMER-2");
        bookings1.stream().map(b->b.toString()).forEach(System.out::println);
        System.out.println();

    }
}