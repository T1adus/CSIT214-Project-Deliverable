package com.example.flytdream;

import java.util.ArrayList;

public class BookingSession {
    //Field
    private City departCity;
    private City arriveCity;
    private String date;
    private String returnDate;
    private int classId;
    private String classSelected;
    private int adult;
    private int child;
    private int infant;
    private ArrayList<Flight> flight;
    private ArrayList<String> seats;
    private int totalCost;
    private ArrayList<Passenger> passengers;
    private ArrayList<Meal> meals;
    public String nameString = "";
    public String seatString = "";
    public String flightType; //Used to determine if the current flight type and control the program accordingly
    public BookingSession() {
        departCity = new City("0", "0", "0");
        arriveCity = new City("0", "0", "0");
        date = "";
        returnDate = "";
        classId = 0;
        classSelected = "";
        adult = 0;
        child = 0;
        infant = 0;
        flight = new ArrayList<>();
        seats = new ArrayList<>();
        meals = new ArrayList<>();
        totalCost = 0;
        flightType = "One Way";
    }

    public City getDepartCity() {return departCity;}
    public City getArriveCity() {return arriveCity;}
    public String getDate() {return date;}
    public String getReturnDate() {return returnDate;}
    public int getClassId() {return classId;}
    public String getClassSelected() {return classSelected;}
    public int[] getPassenger() {
        int[] passenger = {adult, child, infant};
        return passenger;
    }
    public ArrayList<Flight> getFlight() {return flight;}
    public int getTotalPassenger() {return adult+child+infant;}
    public ArrayList<Passenger> getPassengers() {return passengers;}
    public ArrayList<String> getSeats() {return seats;}

    public ArrayList<Meal> getMeals() {
        return meals;
    }
    public String getFlightType(){return flightType;}
    public int getTotalCost(){return totalCost;}

    public void setDepartCity(City newDepartCity) {departCity = newDepartCity;}
    public void setArriveCity(City newArriveCity) {arriveCity = newArriveCity;}
    public void setDate(String newDate) {
        date = newDate;
    }
    public void setReturnDate(String newReturnDate) {returnDate = newReturnDate;}
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
    public void setPassengers(ArrayList<Passenger> newPassengers) {passengers = newPassengers;}
    public void setSeats(ArrayList<String> newSeats){seats = newSeats;}

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }
    public void setTotalCost(int newCost) {this.totalCost = newCost;}

    //Get all the passenger name
    public String checkOutPassengerName(ArrayList<Passenger> passengers){
        for(Passenger passenger: passengers){
            nameString += passenger.getPassengerName() + "\n";
        }
        return nameString;
    }

    //Get all the selected seat
    public String checkOutPassengerSeats(ArrayList<String> seats){
        for(String seat: seats){
            seatString += seat + " ";
        }
        return seatString;
    }

    //Calculate the total price of the booking
    public void calculatePrice() {
        for (Flight flight: flight) {
            totalCost += flight.getCost();
        }

        int totalPassenger = adult + child + infant;
        totalCost *= totalPassenger;


        for(Passenger passenger: passengers){
            if (passenger.getExtraBaggageStatus() == true) {
                totalCost += 200;
            }
        }

        if (flightType.equals("Round Trip")) {
            for(Passenger passenger: passengers){
                if (passenger.getExtraBaggageStatus() == true) {
                    totalCost += 200;
                }
            }
        }

        for(Meal meal: meals){
            totalCost += meal.getPrice();
        }
    }
}
