package com.example.flytdream;

public class Meal {
    private String name;
    private double price;
    private int numberOfDish;

    public Meal(){
        name = "";
        price = 0.0;
        numberOfDish = 0;
    }
    public Meal(String name,double price,int number){
        this.name = name;
        this.price = price;
        this.numberOfDish = number;
    }

    public String getName(){return name;}
    public double getPrice(){return price;}
    public int getNumberOfDish(){return numberOfDish;}

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfDish(int number) {
        this.numberOfDish = number;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
