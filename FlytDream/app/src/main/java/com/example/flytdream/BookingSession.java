package com.example.flytdream;

import java.util.ArrayList;

public class BookingSession {
    private City departCity;
    private City arriveCity;
    private String date;
    private int classId;
    private String classSelected;
    private int adult;
    private int child;
    private int infant;
    private Flight flight;
    private ArrayList<String> seats;
    private int totalCost;
    private ArrayList<Passenger> passengers;
    private ArrayList<Meal> meals;

    public BookingSession() {
        departCity = new City("0", "0", "0");
        arriveCity = new City("0", "0", "0");
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

    public City getDepartCity() {return departCity;}
    public City getArriveCity() {return arriveCity;}
    public String getDate() {return date;}
    public int getClassId() {return classId;}
    public String getClassSelected() {return classSelected;}
    public int[] getPassenger() {
        int[] passenger = {adult, child, infant};
        return passenger;
    }
    public Flight getFlight() {return flight;}
    public int getTotalPassenger() {return adult+child+infant;}
    public ArrayList<Passenger> getPassengers() {return passengers;}
    public ArrayList<String> getSeats() {return seats;}

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setDepartCity(City newDepartCity) {departCity = newDepartCity;}
    public void setArriveCity(City newArriveCity) {arriveCity = newArriveCity;}
    public void setDate(String newDate) {
        date = newDate;
    }
    public void setClassId(int newClassId) {
        classId = newClassId;
    }
    public void setClass(String newClass) {
        classSelected = newClass;
    }
    public void setPassengerCount(int newAdult, int newChild, int newInfant) {
        adult = newAdult;
        child = newChild;
        infant = newInfant;
    }
    public void setFlight(Flight newFlight) {flight = newFlight;}
    public void setPassengers(ArrayList<Passenger> newPassengers) {passengers = newPassengers;}
    public void setSeats(ArrayList<String> newSeats){seats = newSeats;}

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    public String checkOutPassengerName(ArrayList<Passenger> passengers){
        String nameString = "";
        for(Passenger passenger: passengers){
            nameString += passenger.getPassengerName() + "\n";
        }
        return nameString;
    }

    public String checkOutPassengerSeats(ArrayList<String> seats){
        String seatString = "";
        for(String seat: seats){
            seatString += seat + " ";
        }
        return seatString;
    }
}
