package com.hotel.service;

import static org.junit.jupiter.api.Assertions.*;

        import static org.mockito.Mockito.*;

        import java.time.LocalDate;
        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.List;

import com.hotel.exception.OverlappingBookingException;
import com.hotel.mock.MockDatabase;
import com.hotel.model.Booking;
import com.hotel.model.Customer;
import com.hotel.model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.MockitoAnnotations;

public class BookingServiceTest {

    @Mock
    private MockDatabase mockDatabase;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testCreateBooking_OverlappingDates() {

        Room room = new Room(102,"DELUXE");
        Customer customer = new Customer("CUSTOMER-7");
        Booking booking = new Booking(customer,room,LocalDate.now(),LocalDate.now().plusDays(1));

        List<Booking> existingBookings = new ArrayList<>();
        existingBookings.add(new Booking(customer,room,LocalDate.now().minusDays(1), LocalDate.now().plusDays(2)));
        when(mockDatabase.getAllBookingsByRoomNum(1)).thenReturn(existingBookings);

        assertThrows(OverlappingBookingException.class, () -> {
            bookingService.createBooking(booking);
        });

        verify(mockDatabase, never()).saveBooking(any(Booking.class));
    }

    @Test
    @DisplayName("create booking with null booking test case")
    public void testCreateBooking_NullBooking() {
        assertDoesNotThrow(() -> {
            bookingService.createBooking(null);
        });
    }

    @Test
    @DisplayName("create booking with null room test case")
    public void testCreateBooking_NullRoom() {
        LocalDate fromDate = LocalDate.now();
        LocalDate toDate = LocalDate.now().plusDays(1);
        Customer customer = new Customer("CUSTOMER-7");
        Booking booking = new Booking(customer,null,fromDate,toDate);
        assertDoesNotThrow(() -> {
            bookingService.createBooking(booking);
        });
    }

    @Test
    @DisplayName("create booking with room no zero test case")
    public void testCreateBooking_RoomNumberZero() {
        Room room = new Room(0,"DELUXE");
        Customer customer = new Customer("CUSTOMER-7");
        Booking booking = new Booking(customer,room,LocalDate.now(),LocalDate.now().plusDays(1));

        assertDoesNotThrow(() -> {
            bookingService.createBooking(booking);
        });
    }

    @Test
    public void testCreateBooking_NullCustomer() {
        Room room = new Room(102,"DELUXE");
        Booking booking = new Booking(null,room,LocalDate.now(),LocalDate.now().plusDays(1));

        assertDoesNotThrow(() -> {
            bookingService.createBooking(booking);
        });
    }

    @Test
    @DisplayName("create booking with customer name is null test case")
    public void testCreateBooking_CustomerNameNull() {
        Room room = new Room(102,"DELUXE");
        Customer customer = new Customer("");
        Booking booking = new Booking(customer,room,LocalDate.now().plusDays(20),LocalDate.now().plusDays(21));
        assertDoesNotThrow(() -> {
            bookingService.createBooking(booking);
        });
    }

    @Test
    @DisplayName("get all available rooms by date")
    public void testGetAllAvailableRoomsByDate_Success() {
        LocalDate fromDate = LocalDate.now();
        LocalDate toDate = LocalDate.now().plusDays(1);

        Room room = new Room(102,"DELUXE");
        Customer customer = new Customer("CUSTOMER-7");
        Booking booking = new Booking(customer,room,fromDate.minusDays(2),fromDate.minusDays(1));

        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking);

        when(mockDatabase.getAllBookings()).thenReturn(bookings);
        List<Room> availableRooms = bookingService.getAllAvailableRoomsByDate(fromDate, toDate);

        assertEquals(1, availableRooms.size());
        assertEquals(102, availableRooms.get(0).getRoomNumber());
    }

    @Test
    public void testGetAllBookingsByCustomer_Success() {
        LocalDate fromDate = LocalDate.now();
        LocalDate toDate = LocalDate.now().plusDays(1);

        List<Booking> bookings = new ArrayList<>();
        Room room = new Room(102,"DELUXE");
        Customer customer = new Customer("CUSTOMER-2");
        Booking booking = new Booking(customer,room,fromDate,toDate);
        bookings.add(booking);

        when(mockDatabase.getAllBookingsByCustomerName(customer.getName())).thenReturn(bookings);

        List<Booking> result = bookingService.getAllBookingsByCustomer(customer.getName());
        assertEquals(1, result.size());
        assertEquals(customer.getName(), result.get(0).getCustomer().getName());
    }

    @Test
    public void testGetAllBookingsByCustomer_NoBookings() {
        String customerName = "CUSTOMER-7";

        when(mockDatabase.getAllBookingsByCustomerName(customerName)).thenReturn(Collections.emptyList());

        List<Booking> result = bookingService.getAllBookingsByCustomer(customerName);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetAllBookingsByCustomer_NullCustomerName() {
        List<Booking> result = bookingService.getAllBookingsByCustomer(null);

        assertTrue(result.isEmpty());
    }
}
