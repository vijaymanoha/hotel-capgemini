package com.hotel.model;

public class Customer {

    private Integer id;
    private String name;

    public Customer() {
        this.id = generateRandomId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }
    private int generateRandomId() {
        return (int) (Math.random() * 10000000);
    }

    public String toString() {
        return "id: " + id + ", name: " + name ;
    }
}