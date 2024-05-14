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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
                Customer customer = new Customer(customerEnum.getName());
                customers.add(customer);
            }

            BookingEnum[] bookingEnums = BookingEnum.values();
            bookings = new HashMap<>();
            for (BookingEnum bookingEnum: bookingEnums) {
                Customer customer = new Customer(bookingEnum.getCustomerEnum().getName());
                Room room = new Room(bookingEnum.getRoomEnum().getRoomNumber(),bookingEnum.getRoomEnum().getRoomType());
                bookings.put(bookingEnum.getId(),new Booking(customer,room,bookingEnum.getFromDate(),bookingEnum.getToDate()));
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

    public List<Booking> getAllBookings(){
        return bookings.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    public  List<Booking> getAllBookingsByRoomNum(Integer roomNum){
        return  bookings.entrySet().stream().filter(b->b.getValue().getRoom().getRoomNumber()==roomNum).map(Map.Entry::getValue).collect(Collectors.toList());
    }

    public  List<Booking> getAllBookingsByCustomerName(String customerName) {
        List<Booking> filteredBookings = new ArrayList<>();
        System.out.println(customerName);
        System.out.println(bookings.size());
        for (Map.Entry<Integer,Booking> entry: bookings.entrySet()) {
            String tempCust = entry.getValue().getCustomer().getName();
            if (tempCust.equalsIgnoreCase(customerName)) {
                filteredBookings.add(entry.getValue());
            }
        }
        return filteredBookings;
//        return bookings.entrySet().stream().filter(b->b.getValue().getCustomer().getName().equalsIgnoreCase(customerName)).map(Map.Entry::getValue).collect(Collectors.toList());
    }

    public void insertCustomer(Customer customer) {
        customers.add(customer);
    }


    public  void saveBooking(Booking booking){
        bookings.put(bookings.size()+1, booking);
        System.out.println(bookings.size());
    }
    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        ConcurrentHashMap.KeySetView<Object, Boolean> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t)) ;
    }

}

