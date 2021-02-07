package com.company.xml_parsing.handler;

public enum TouristVoucherType {

    TOURISTVOUCHERS("TouristVouchers"),
    TOURIST_VOUCHER("tourist-voucher"),
    REST_VOUCHER("rest-voucher"),
    SIGHTSEEING_VOUCHER("sightseeing-voucher"),
    WEEKEND_VOUCHER("weekend-voucher"),
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
    NUMBER_OF_NIGHTS("number-of-nights"),
    NUMBER_OF_PLACES("number-of-places"),
    CITY("city"),
    HOTEL_CHARACTERISTIC("hotel-characteristic");

    private String value;

    private TouristVoucherType(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
