package com.company.xml_parsing.entity;

public class HotelCharacteristic {
    private String foodType;
    private int stars;
    private int rooms;

    public HotelCharacteristic() {

    }

    public HotelCharacteristic(String foodType, int stars, int rooms) {
        this.foodType = foodType;
        this.stars = stars;
        this.rooms = rooms;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((foodType != null) ? foodType.hashCode() : 0);
        result = prime * result + stars;
        result = prime * result + rooms;
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
        HotelCharacteristic other = (HotelCharacteristic) obj;
        if (foodType == null) {
            if (other.foodType != null) {
                return false;
            }
        } else if (!foodType.equals(other.foodType)) {
            return false;
        }
        if (stars != other.stars) {
            return false;
        }
        if (rooms != other.rooms) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nfood type -- ").append(foodType);
        sb.append("\nstars -- ").append(stars);
        sb.append("\nrooms -- ").append(rooms);
        return sb.toString();
    }
}
