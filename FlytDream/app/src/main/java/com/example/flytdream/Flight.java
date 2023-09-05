package com.example.flytdream;

public class Flight {
    private String departCity;
    private String departTime;
    private String flightTime;
    private String arriveCity;
    private String arriveTime;
    private int cost;

    public Flight() {
        departCity = "TBA";
        departTime = "TBA";
        flightTime = "TBA";
        arriveCity = "TBA";
        arriveTime = "TBA";
        cost = 0;
    }

    public Flight(String departCity, String departTime, String flightTime, String arriveCity, String arriveTime, int cost) {
        this.departCity = departCity;
        this.departTime = departTime;
        this.flightTime = flightTime;
        this.arriveCity = arriveCity;
        this.arriveTime = arriveTime;
        this.cost = cost;
    }

    public String getDepartCity() {return departCity;}
    public String getDepartTime() {return departTime;}
    public String getFlightTime() {return flightTime;}
    public String getArriveCity() {return arriveCity;}
    public String getArriveTime() {return arriveTime;}

    public int getCost() {
        return cost;
    }
}
