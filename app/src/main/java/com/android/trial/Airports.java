package com.android.trial;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Airports
{

    @SerializedName("Airport")
    AirPort airPorts;

    public Airports(AirPort airPorts) {
        this.airPorts = airPorts;
    }

    public AirPort getAirPortLists() {
        return airPorts;
    }

    public void setAirPortsList(AirPort airPorts) {
        this.airPorts = airPorts;
    }
}
