package com.android.trial;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Airports
{

    @SerializedName("Airport")
    List<AirPort> airPorts;

    public Airports(List<AirPort> airPorts) {
        this.airPorts = airPorts;
    }

    public List<AirPort> getAirPortLists() {
        return airPorts;
    }

    public void setAirPortsList(List<AirPort> airPorts) {
        this.airPorts = airPorts;
    }
}
