package com.hotel.service;

import com.hotel.exception.OverlappingBookingException;
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
        if (booking != null && booking.getRoom()!=null && booking.getRoom().getRoomNumber()!=0 && booking.getCustomer()!=null && booking.getCustomer().getName()!=null && !booking.getCustomer().getName().isEmpty()) {
            List<Booking> bookingsByRoomNum = mockDatabase.getAllBookingsByRoomNum(booking.getRoom().getRoomNumber());

            List<DateRange> existingDateRanges = bookingsByRoomNum.stream().map(b -> new DateRange(b.getFromDate(), b.getToDate())).toList();
            Boolean isOverlap = isNewDatesNotBetweenExisting(existingDateRanges, booking.getFromDate(), booking.getToDate());
            if (isOverlap) {
                throw new OverlappingBookingException("Cannot create booking due to date overlap.");
            } else {
                try{
                    mockDatabase.saveBooking(booking);
                    System.out.println("yes, new booking created" + bookingsByRoomNum.size());
                    if (!mockDatabase.getAllCustomers().stream().anyMatch(c -> c.getName().equalsIgnoreCase(booking.getCustomer().getName())))
                        customerService.createCustomer(booking.getCustomer().getName());
                }catch (Exception e){
                    System.out.println("Error saving booking: " + e.getMessage());
                }

            }
        }

    }


    boolean isNewDatesNotBetweenExisting(List<DateRange> existingDateRanges, LocalDate newFromDate, LocalDate newToDate) {
        if (existingDateRanges == null || existingDateRanges.isEmpty()) {
            return true; // No existing bookings, so no overlap
        }
       return existingDateRanges.stream().noneMatch(dateRange ->
                (newFromDate.isAfter(dateRange.getFromDate()) && newFromDate.isAfter(dateRange.getToDate()) &&
                        newToDate.isAfter(dateRange.getFromDate()) && newToDate.isAfter(dateRange.getToDate()))
                        ||
                        (newFromDate.isBefore(dateRange.getFromDate()) && newFromDate.isBefore(dateRange.getToDate()) &&
                                newToDate.isBefore(dateRange.getFromDate()) && newToDate.isBefore(dateRange.getToDate()))
        );

    }

    public List<Room> getAllAvailableRoomsByDate(LocalDate newFromDate, LocalDate newToDate) {
        try {
            if(newFromDate!=null && newToDate!=null){
                List<Room> noOfAvailableRooms = new ArrayList<>();
                List<Booking> bookings = mockDatabase.getAllBookings();
                List<Room> rooms = bookings.stream().map(b -> b.getRoom()).toList();
                for (Room room : rooms) {
                    Boolean bookingAvailable = bookings.stream().filter(b -> room.getRoomNumber() == b.getRoom().getRoomNumber()).anyMatch(bo ->
                            newFromDate.isAfter(bo.getFromDate()) && newFromDate.isAfter(bo.getToDate()) &&
                                    newToDate.isAfter(bo.getFromDate()) && newToDate.isAfter(bo.getToDate()) || (newFromDate.isBefore(bo.getFromDate()) && newFromDate.isBefore(bo.getToDate()) &&
                                    newToDate.isBefore(bo.getFromDate()) && newToDate.isBefore(bo.getToDate()))
                    );
                    if (bookingAvailable) {
                        noOfAvailableRooms.add(room);
                    }
                }
                return noOfAvailableRooms.stream().filter(distinctByKey(Room::getRoomNumber)).toList();
            }

        }catch (Exception e) {
            System.out.println("Error occurred while retrieving available rooms: " + e.getMessage());

        }
        return Collections.emptyList();
    }

    public List<Booking> getAllBookingsByCustomer(String customerName) {
        try{
            List<Booking> bookings = new ArrayList<>();
            if(customerName!=null){
                bookings = mockDatabase.getAllBookingsByCustomerName(customerName);

            }
            return bookings;
        }catch (Exception e){
            System.out.println("Error occurred while retrieving bookings by customer: " + e.getMessage());
            return Collections.emptyList();
        }

    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}