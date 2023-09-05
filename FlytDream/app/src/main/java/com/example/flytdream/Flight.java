package com.example.flytdream;

public class Flight {
    private String from;
    private String to;
    private int cost;

    public Flight() {
        from = "";
        to = "";
        cost = 0;
    }

    public Flight(String from, String to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}
