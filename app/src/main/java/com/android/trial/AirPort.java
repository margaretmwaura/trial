package com.android.trial;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class AirPort
{

    @SerializedName("AirportCode")
    String airportCode;
    @SerializedName("CityCode")
    String cityCode;
    @SerializedName("CountryCode")
    String countryCode;

    protected AirPort(Parcel in) {
        airportCode = in.readString();
        cityCode = in.readString();
        countryCode = in.readString();
    }


    public String getAirportCode()
    {
        return this.airportCode;
    }
    public String getCityCode()
    {
        return this.cityCode;
    }
    public String getCountryCode()
    {
        return this.countryCode;
    }
    public void setAirportCode(String airportCode)
    {
        this.airportCode = airportCode;
    }
    public void setCityCode(String cityCode)
    {
        this.cityCode = cityCode;
    }
    public void setCountryCode(String countryCode)
    {
        this.countryCode = countryCode;
    }


}
