package com.hotel.mock;

import com.hotel.enums.BookingEnum;
import com.hotel.enums.CustomerEnum;
import com.hotel.enums.RoomEnum;
import com.hotel.model.Booking;
import com.hotel.model.Customer;
import com.hotel.model.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MockDatabase {
    private static boolean initialized = false;
    private static List<Customer> customers;
    private static List<Room> rooms;

    private static HashMap<Integer, Booking> bookings;


    public MockDatabase() {
        initializeOnce();
    }
    private static synchronized void initializeOnce() {
        if (!initialized) {
            RoomEnum[] roomEnums = RoomEnum.values();
            rooms = new ArrayList<>();
            for (RoomEnum roomEnum: roomEnums) {
                rooms.add(new Room(roomEnum.getRoomNumber(),roomEnum.getRoomType()));
            }

            CustomerEnum[] customerEnums = CustomerEnum.values();
            customers = new ArrayList<>();
            for (CustomerEnum customerEnum: customerEnums) {
                Customer customer = new Customer();
                customer.setName(customerEnum.getName());
                customers.add(customer);
            }

            BookingEnum[] bookingEnums = BookingEnum.values();
            bookings = new HashMap<>();
            for (BookingEnum bookingEnum: bookingEnums) {
                bookings.put(bookingEnum.getId(),new Booking(bookingEnum.getCustomerName(),bookingEnum.getRoomNumber(),bookingEnum.getFromDate(),bookingEnum.getToDate()));
            }

            System.out.println("Initializing...");
            initialized = true;
        }
    }

    public List<Customer> getAllCustomers(){
        return customers;
    }

    public List<Room> getAllRooms(){
        return rooms;
    }

    public HashMap<Integer, Booking> getAllBookings(){
        return bookings;
    }


    public void insertCustomer(Customer customer) {
        customers.add(customer);
    }


}

