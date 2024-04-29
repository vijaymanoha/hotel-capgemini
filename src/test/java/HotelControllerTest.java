import com.hotel.controller.HotelController;
import com.hotel.model.Booking;
import com.hotel.service.BookingService;
import com.hotel.service.CustomerService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class HotelControllerTest {
    @Mock
    private BookingService bookingServiceMock;

    @Mock
    private CustomerService customerServiceMock;

    @InjectMocks
    private HotelController hotelController;

    public HotelControllerTest(){
        this.hotelController = new HotelController();
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBooking() {
    }
}
