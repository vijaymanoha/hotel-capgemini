package com.hotel.service;

import com.hotel.mock.MockDatabase;
import com.hotel.model.Booking;
import com.hotel.model.Customer;
import com.hotel.model.Room;
import com.hotel.util.DateRange;

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


//    private List<Booking> bookings;


    public BookingService() {
        customerService = new CustomerService();
        mockDatabase = new MockDatabase();

    }

    public void createBooking(Booking booking) {
        if (booking != null) {
            List<Booking> bookingsByRoomNum = mockDatabase.getAllBookingsByRoomNum(booking.getRoom().getRoomNumber());

            List<DateRange> existingDateRanges = bookingsByRoomNum.stream().map(b -> new DateRange(b.getFromDate(), b.getToDate())).toList();
            Boolean isOverlap = isNewDatesNotBetweenExisting(existingDateRanges, booking.getFromDate(), booking.getToDate());
            try {
                if (isOverlap) {
                    throw new Exception();
                } else {
                    mockDatabase.saveBooking(booking);
                    System.out.println("yes, new booking created" + bookingsByRoomNum.size());
                    if (!mockDatabase.getAllCustomers().stream().anyMatch(c -> c.getName().equalsIgnoreCase(booking.getCustomer().getName())))
                        customerService.createCustomer(booking.getCustomer().getName());
                }
            } catch (Exception e) {
                System.out.println(e + " No Can't Create Booking With These  From-date:" + booking.getFromDate() + "  To-date:" + booking.getToDate());
            }

        }

    }


    boolean isNewDatesNotBetweenExisting(List<DateRange> existingDateRanges, LocalDate newFromDate, LocalDate newToDate) {
        return existingDateRanges.stream().noneMatch(dateRange ->
                (newFromDate.isAfter(dateRange.getFromDate()) && newFromDate.isAfter(dateRange.getToDate()) &&
                        newToDate.isAfter(dateRange.getFromDate()) && newToDate.isAfter(dateRange.getToDate()))
                        ||
                        (newFromDate.isBefore(dateRange.getFromDate()) && newFromDate.isBefore(dateRange.getToDate()) &&
                                newToDate.isBefore(dateRange.getFromDate()) && newToDate.isBefore(dateRange.getToDate()))
        );
    }

    public List<Room> getAllAvailableRoomsByDate(LocalDate newFromDate, LocalDate newToDate) {
        List<Room> noOfAvailableRooms = new ArrayList<>();
        List<Booking> bookings = mockDatabase.getAllBookings();
        List<Room> rooms = bookings.stream().map(b -> b.getRoom()).toList();
        for (Room room : rooms) {
            Boolean bookingAvailable =bookings.stream().filter(b -> room.getRoomNumber() == b.getRoom().getRoomNumber()).anyMatch(bo ->
                    newFromDate.isAfter(bo.getFromDate()) && newFromDate.isAfter(bo.getToDate()) &&
                            newToDate.isAfter(bo.getFromDate()) && newToDate.isAfter(bo.getToDate()) || (newFromDate.isBefore(bo.getFromDate()) && newFromDate.isBefore(bo.getToDate()) &&
                            newToDate.isBefore(bo.getFromDate()) && newToDate.isBefore(bo.getToDate()))
            );
            if(bookingAvailable){
                noOfAvailableRooms.add(room);
            }
        }
        return noOfAvailableRooms;
    }

    public List<Booking> getAllBookings() {
        List<Booking> bookings = mockDatabase.getAllBookings();
        return bookings;
        //return bookings.stream().filter(distinctByKey(Booking::getCustomer)).toList();
    }

    public List<Booking> getAllBookingsByCustomer(String customerName) {
        List<Booking> bookings = mockDatabase.getAllBookingsByCustomerName(customerName);
        return bookings;
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}