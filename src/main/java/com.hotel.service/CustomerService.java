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
        Integer id=mockDatabase.getAllCustomers().size()+1;
        Customer customer = new Customer();
        customer.setName(name);
        mockDatabase.insertCustomer(customer);

    }
    public List<Customer> getCustomers(){
      return   mockDatabase.getAllCustomers();
    }

}