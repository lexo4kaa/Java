package com.company.xml_parsing.entity;

import java.time.LocalDate;

public class WeekendVoucher extends TouristVoucher{
    private int numberOfNights;

    public WeekendVoucher(String transport, String voucherNumber, String country, int numberOfDays, int numberOfNights,
                          LocalDate startDate, int cost, HotelCharacteristic hotelCharacteristic) {
        super(transport, voucherNumber, country, numberOfDays, startDate, cost, hotelCharacteristic);
        this.numberOfNights = numberOfNights;
    }

    public WeekendVoucher() {

    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + numberOfNights;
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
        WeekendVoucher other = (WeekendVoucher) obj;
        if (numberOfNights != other.numberOfNights) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\nnumber of nights -- ").append(numberOfNights);
        sb.append("\ntype -- weekend");
        return sb.toString();
    }
}
