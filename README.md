# Hotel Booking System

This Java application simulates a hotel booking system. It allows users to create bookings, check available rooms, and view existing bookings.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Maven (for building and managing dependencies)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/vijaymanoha/hotel-capgemini.git

2.Navigate to the project directory:
   cd hotel-booking-system
   
3.Build the project using Maven:
mvn clean install

### Usage
Open the project in your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse).
Navigate to the src/main/java directory and locate the Main.java file.
Run the Main class with the public static void main(String[] args) method.
Follow the on-screen instructions to interact with the hotel booking system.

## Main Method to execute 
<pre>
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
        List<Booking> bookings1 = hotelController.getAllBookingsByCustomer(customer.getName());
        bookings1.stream().map(b->b.toString()).forEach(System.out::println);

    }
}
</pre>

