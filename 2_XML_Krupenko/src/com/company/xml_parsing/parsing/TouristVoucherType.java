package com.company.xml_parsing.parsing;

public enum TouristVoucherType {

    TOURIST_VOUCHERS("TouristVouchers"),
    TOURIST_VOUCHER("tourist-voucher"),
    TRANSPORT("transport"),
    TYPE("type"),
    FOOD_TYPE("food-type"),
    VOUCHER_NUMBER("voucher-number"),
    COUNTRY("country"),
    NUMBER_OF_DAYS("number-of-days"),
    START_DATE("start-date"),
    COST("cost"),
    STARS("stars"),
    ROOMS("rooms"),
    HOTEL_CHARACTERISTIC("hotel-characteristic");

    private String value;

    private TouristVoucherType(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
