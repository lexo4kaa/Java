package com.company.xml_parsing.entity;

import java.time.LocalDate;

public class SightseeingVoucher extends TouristVoucher{
    private int numberOfPlaces;

    public SightseeingVoucher(String transport, String voucherNumber, String country,
                              int numberOfDays, int numberOfPlaces, LocalDate startDate,
                              int cost, HotelCharacteristic hotelCharacteristic) {
        super(transport, voucherNumber, country, numberOfDays, startDate, cost, hotelCharacteristic);
        this.numberOfPlaces = numberOfPlaces;
    }

    public SightseeingVoucher() {

    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + numberOfPlaces;
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
        SightseeingVoucher other = (SightseeingVoucher) obj;
        if (numberOfPlaces != other.numberOfPlaces) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\nnumber of places -- ").append(numberOfPlaces);
        sb.append("\ntype -- sightseeing");
        return sb.toString();
    }
}
