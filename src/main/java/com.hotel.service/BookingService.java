package com.hotel.service;

import com.hotel.mock.MockDatabase;
import com.hotel.model.Booking;
import com.hotel.model.Customer;
import com.hotel.model.Room;

import java.time.LocalDate;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BookingService {

    private MockDatabase mockDatabase;

    private CustomerService customerService;


    private HashMap<Integer, Booking> bookings;


    private Integer numberOfRooms = 0;


    public BookingService() {
        customerService = new CustomerService();
        mockDatabase = new MockDatabase();

    }

    public boolean createBooking(Booking booking) {
        int numOfNonAvailableRooms = 0;
        numberOfRooms = mockDatabase.getAllRooms().size();
        bookings = mockDatabase.getAllBookings();

        for (Entry<Integer, Booking> entry : bookings.entrySet()) {
            if (bookingAreOverlapping(booking, entry.getValue())) {
                numOfNonAvailableRooms++;
            }
        }

        if (numOfNonAvailableRooms == this.numberOfRooms) {
            return false;
        } else {
            bookings.put(booking.getId(), booking);
            customerService.createCustomer(booking.getCustomerName());
            return true;
        }
    }

    private boolean bookingAreOverlapping(Booking b1, Booking b2) {
        return ((b1.getFromDate().isAfter(b2.getFromDate()) || b1.getFromDate().isEqual(b2.getFromDate()))
                && (b1.getFromDate().isBefore(b2.getToDate()) || b1.getFromDate().isEqual(b2.getToDate())))

                ||

                ((b2.getFromDate().isAfter(b1.getFromDate()) || b2.getFromDate().isEqual(b1.getFromDate()))
                        && (b2.getFromDate().isBefore(b1.getToDate()) || b2.getFromDate().isEqual(b1.getToDate())));

    }

    public List<Room> getAllAvailableRoomsByDate(LocalDate dateToCheck) {
        bookings = mockDatabase.getAllBookings();
        List<Room> noOfRoomsList = mockDatabase.getAllRooms();
        List<Room> noOfAvailableRooms = new ArrayList<>();
        for (Entry<Integer, Booking> entry : bookings.entrySet()) {
            Booking existingReservation = entry.getValue();
            if (dateToCheck.isBefore(existingReservation.getFromDate())
                    && existingReservation.getFromDate().isAfter(dateToCheck)) {
                Optional<Room> numberOfNonAvailableRoom = noOfRoomsList.stream().filter(room -> room.getRoomNumber() == existingReservation.getRoomNumber()).findFirst();
                noOfAvailableRooms.add(numberOfNonAvailableRoom.get());
            }

        }
        return noOfAvailableRooms;
    }

    public List<Booking> getAllBookings() {
        bookings = mockDatabase.getAllBookings();
        return bookings.entrySet().stream().map(Entry::getValue).filter(distinctByKey(Booking::getCustomerName)).toList();
    }

    public List<Booking> getAllBookingsByCustomer(Customer customer) {
        bookings = mockDatabase.getAllBookings();
        return bookings.entrySet().stream().filter(booking -> booking.getValue().getCustomerName().equalsIgnoreCase(customer.getName())).map(Entry::getValue).collect(Collectors.toList());
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t)) ;
    }
}