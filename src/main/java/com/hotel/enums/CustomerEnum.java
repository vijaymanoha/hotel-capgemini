package com.hotel.enums;

public enum CustomerEnum {
    CUSTOMER_0001(0001, "CUSTOMER-1"),
    CUSTOMER_0002(0002, "CUSTOMER-2"),
    CUSTOMER_0003(0003, "CUSTOMER-3"),
    CUSTOMER_0004(0004, "CUSTOMER-4");


    private  Integer id;

    public String getName() {
        return name;
    }

    private  String name;

    public Integer getId() {
        return id;
    }

    private  CustomerEnum(Integer id, String name){
        this.id = id;
        this.name = name;
    }
}
