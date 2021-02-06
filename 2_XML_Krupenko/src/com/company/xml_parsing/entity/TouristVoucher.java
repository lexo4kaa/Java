package com.company.xml_parsing.entity;

import java.time.LocalDate;

public class TouristVoucher {
    private String transport;
    private String voucherNumber;
    private String country;
    private int numberOfDays;
    private LocalDate startDate;
    private int cost;
    private HotelCharacteristic hotelCharacteristic;

    public TouristVoucher() {
        hotelCharacteristic = new HotelCharacteristic();
    }

    public TouristVoucher(String transport, String voucherNumber, String country, int numberOfDays,
                          LocalDate startDate, int cost, HotelCharacteristic hotelCharacteristic) {
        this.transport = transport;
        this.voucherNumber = voucherNumber;
        this.country = country;
        this.numberOfDays = numberOfDays;
        this.startDate = startDate;
        this.cost = cost;
        this.hotelCharacteristic = hotelCharacteristic;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public HotelCharacteristic getHotelCharacteristic() {
        return hotelCharacteristic;
    }

    public void setHotelCharacteristic(HotelCharacteristic hotelCharacteristic) {
        this.hotelCharacteristic = hotelCharacteristic;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((transport != null) ? transport.hashCode() : 0);
        result = prime * result + ((voucherNumber != null) ? voucherNumber.hashCode() : 0);
        result = prime * result + ((country != null) ? country.hashCode() : 0);
        result = prime * result + numberOfDays;
        result = prime * result + ((startDate != null) ? startDate.hashCode() : 0);
        result = prime * result + cost;
        result = prime * result + ((hotelCharacteristic != null) ? hotelCharacteristic.hashCode() : 0);
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        TouristVoucher other = (TouristVoucher) obj;
        if (transport == null) {
            if (other.transport != null) {
                return false;
            }
        } else if (!transport.equals(other.transport)) {
            return false;
        }
        if (voucherNumber == null) {
            if (other.voucherNumber != null) {
                return false;
            }
        } else if (!voucherNumber.equals(other.voucherNumber)) {
            return false;
        }
        if (country == null) {
            if (other.country != null) {
                return false;
            }
        } else if (!country.equals(other.country)) {
            return false;
        }
        if (numberOfDays != other.numberOfDays) {
            return false;
        }
        if (startDate == null) {
            if (other.startDate != null) {
                return false;
            }
        } else if (!startDate.equals(other.startDate)) {
            return false;
        }
        if (cost != other.cost) {
            return false;
        }
        if (hotelCharacteristic == null) {
            if (other.hotelCharacteristic != null) {
                return false;
            }
        } else if (!hotelCharacteristic.equals(other.hotelCharacteristic)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\nvoucher ");
        sb.append(voucherNumber).append(":");
        sb.append("\ncountry -- ").append(country);
        sb.append("\ntransport -- ").append(transport);
        sb.append("\nstart date -- ").append(startDate);
        sb.append("\nnumber of days -- ").append(numberOfDays);
        sb.append(hotelCharacteristic);
        sb.append("\ncost -- ").append(cost);
        return sb.toString();
    }
}