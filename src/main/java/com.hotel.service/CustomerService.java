package com.hotel.service;

import com.hotel.mock.MockDatabase;
import com.hotel.model.Customer;

import java.util.List;

public class CustomerService {

    private  MockDatabase mockDatabase;

    public CustomerService() {
        mockDatabase = new MockDatabase();
    }

    public void createCustomer(String name) {
        Customer customer = new Customer(name);
        mockDatabase.insertCustomer(customer);

    }

}