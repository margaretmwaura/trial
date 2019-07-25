package com.android.trial;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class AirPort implements Parcelable
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

    public static final Creator<AirPort> CREATOR = new Creator<AirPort>() {
        @Override
        public AirPort createFromParcel(Parcel in) {
            return new AirPort(in);
        }

        @Override
        public AirPort[] newArray(int size) {
            return new AirPort[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(airportCode);
        dest.writeString(cityCode);
        dest.writeString(countryCode);
    }
}
