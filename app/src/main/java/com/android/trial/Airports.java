package com.android.trial;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Airports
{

    @SerializedName("Airport")
    List<AirPort> airPorts = new ArrayList<>();



    public Airports(List<AirPort> airPorts) {
        this.airPorts = airPorts;
    }

    public List<AirPort> getAirPorts() {
        return airPorts;
    }

    public void setAirPorts(List<AirPort> airPorts) {
        this.airPorts = airPorts;
    }
}
