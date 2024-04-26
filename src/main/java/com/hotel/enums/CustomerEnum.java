package com.hotel.enums;

public enum CustomerEnum {
    CUSTOMER_0001(0001, "VIJAY"),
    CUSTOMER_0002(0002, "MANOHAR"),
    CUSTOMER_0003(0003, "JHON"),
    CUSTOMER_0004(0004, "PERIKALA"),
    CUSTOMER_0005(0005, "SIRI");


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
