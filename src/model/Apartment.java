package model;

public class Apartment {
    private String number;
    private int numberOfRooms;
    private int numberOfBathrooms;
    private boolean hasABalcony;
    private boolean hasOwner;
    private boolean hasTenant;
    private double monthlyRentalValue;

    public Apartment(String number, int numberOfRooms, int numberOfBathrooms, boolean hasABalcony, double monthlyRentalValue) {
        this.number = number;
        this.numberOfRooms = numberOfRooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.hasABalcony = hasABalcony;
        this.monthlyRentalValue = monthlyRentalValue;
        this.hasOwner = false;
        this.hasTenant = false;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(int numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public boolean isHasABalcony() {
        return hasABalcony;
    }

    public void setHasABalcony(boolean hasABalcony) {
        this.hasABalcony = hasABalcony;
    }

    public double getMonthlyRentalValue() {
        return monthlyRentalValue;
    }

    public void setMonthlyRentalValue(double monthlyRentalValue) {
        this.monthlyRentalValue = monthlyRentalValue;
    }

    public boolean getHasOwner() {
        return hasOwner;
    }

    public void setHasOwner(boolean hasOwner) {
        this.hasOwner = hasOwner;
    }
    public boolean getHasTenant() {
        return hasTenant;
    }

    public void setHasTenant(boolean hasTenant) {
        this.hasTenant = hasTenant;
    }
}
