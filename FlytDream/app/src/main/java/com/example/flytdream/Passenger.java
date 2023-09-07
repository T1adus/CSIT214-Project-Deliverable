package com.example.flytdream;

public class Passenger {
    private String passengerName;
    private String passengerPassport;
    private boolean extraBaggage;

    public Passenger() {
        passengerName = "";
        passengerPassport = "";
        extraBaggage = false;
    }

    public String getPassengerName() {return passengerName;}
    public String getPassengerPassport() {return passengerPassport;}
    public boolean getExtraBaggageStatus() {return extraBaggage;}
    public void setPassengerName(String newPassengerName) {passengerName = newPassengerName;}
    public void setPassengerPassport(String newPassengerPassport) {passengerPassport = passengerPassport;}
    public void setExtraBaggage(boolean extraBaggageStatus) {
        extraBaggage = extraBaggageStatus;
    }
}
