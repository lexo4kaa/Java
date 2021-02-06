package com.company.xml_parsing.entity;

import java.time.LocalDate;

public class RestVoucher extends TouristVoucher {

    private String city;

    public RestVoucher(String transport, String voucherNumber, String country, String city, int numberOfDays,
                          LocalDate startDate, int cost, HotelCharacteristic hotelCharacteristic) {
        super(transport, voucherNumber, country, numberOfDays, startDate, cost, hotelCharacteristic);
        this.city = city;
    }

    public RestVoucher() {

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((city != null) ? city.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        RestVoucher other = (RestVoucher) obj;
        if (city == null) {
            if (other.city != null) {
                return false;
            }
        } else if (!city.equals(other.city)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\ncity -- ").append(city);
        sb.append("\ntype -- rest");
        return sb.toString();
    }

}
