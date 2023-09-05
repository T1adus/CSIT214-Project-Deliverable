package com.example.flytdream;

import java.util.ArrayList;

public class BookingSession {
    private String customerName;
    private int customerAge;
    private long customerGenderId;
    private String customerGender;
    private String customerPassport;
    private long departureCityId;
     private String departureCityAlias;
    private long arrivalCityId;
    private String arrivalCityAlias;
    private String date;
    private long classId;
    private String classSelected;
    private int adult;
    private int child;
    private int infant;
    private Flight flight;
    private ArrayList<String> seats;
    private int totalCost;

    public BookingSession() {
        departureCityId = 0;
        departureCityAlias = "";
        arrivalCityId = 0;
        arrivalCityAlias = "";
        date = "";
        classId = 0;
        classSelected = "";
        adult = 0;
        child = 0;
        infant = 0;
        flight = new Flight();
        seats = new ArrayList<>();
        totalCost = 0;
    }

    public String getCustomerName() {return customerName;}
    public int getCustomerAge() {return customerAge;}
    public long getCustomerGenderId() {return customerGenderId;}
    public String getCustomerGender() {return customerGender;}
    public String getCustomerPassport() {return customerPassport;}
    public long getDepartureCityId() {return departureCityId;}
    public long getArrivalCityId() {return arrivalCityId;}
    public String getDepartureCityAlias() {return departureCityAlias;}
    public String getArrivalCityAlias() {return arrivalCityAlias;}
    public String getDate() {return date;}
    public long getClassId() {return classId;}
    public String getClassSelected() {return classSelected;}
    public int[] getPassenger() {
        int[] passenger = {adult, child, infant};
        return passenger;
    }
    public Flight getFlight() {return flight;}

    public void setCustomerName(String newCustomerName) {customerName = newCustomerName;}
    public void setCustomerAge(int newCustomerAge) {customerAge = newCustomerAge;}
    public void setCustomerGenderId(long newCustomerGenderId) {customerGenderId = newCustomerGenderId;}
    public void setCustomerGender(String newCustomerGender) {customerGender = newCustomerGender;}
    public void setCustomerPassport(String newCustomerPassport) {customerPassport = newCustomerPassport;}
    public void setDepartureCity(long newDepartureCityId) {
        departureCityId = newDepartureCityId;
    }
    public void setArrivalCityId(long newArrivalCityId) {
        arrivalCityId = newArrivalCityId;
    }
    public void setDepartureCityAlias(String newDepartureCityAlias) {
        departureCityAlias = newDepartureCityAlias;
    }
    public void setArrivalCityAlias(String newArrivalCityAlias) {
        arrivalCityAlias = newArrivalCityAlias;
    }
    public void setDate(String newDate) {
        date = newDate;
    }
    public void setClassId(long newClassId) {
        classId = newClassId;
    }
    public void setClass(String newClass) {
        classSelected = newClass;
    }
    public void setPassenger(int newAdult, int newChild, int newInfant) {
        adult = newAdult;
        child = newChild;
        infant = newInfant;
    }
    public void setFlight(Flight newFlight) {flight = newFlight;}
}
