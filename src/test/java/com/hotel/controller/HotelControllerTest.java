package com.hotel.controller;

import com.hotel.controller.HotelController;
import com.hotel.model.Customer;
import com.hotel.model.Room;
import com.hotel.service.BookingService;
import com.hotel.service.CustomerService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class HotelControllerTest {
    @Mock
    private BookingService bookingServiceMock;

    @Mock
    private CustomerService customerServiceMock;

    @InjectMocks
    private HotelController hotelController;

    @BeforeEach
    public  void beforeAllInit(){
        this.hotelController = new HotelController();
    }
    @AfterEach
    public   void afterAllEnd(){
        System.out.println("Clean Up ...");
    }



    @Test
    @DisplayName("Booking creation")
    public void testBookingCreation() {
        // Create Customer
        Customer customer = new Customer("CUSTOMER-7");
        Assertions.assertEquals("CUSTOMER-7", customer.getName(), "Customer name should be CUSTOMER-7");

        // Create Room
        Room room = new Room(102, "DELUXE");
        Assertions.assertEquals(102, room.getRoomNumber(), "Room number should be 102");
        Assertions.assertEquals("DELUXE", room.getRoomType(), "Room type should be DELUXE");
    }
}
