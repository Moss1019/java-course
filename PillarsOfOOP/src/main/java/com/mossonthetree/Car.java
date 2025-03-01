package com.mossonthetree;

import com.google.gson.Gson;

public class Car extends Traveler implements JsonSerializable {
    public String model;

    public String year;

    @Override
    public String toJson() {
        return new Gson().toJson(this);
    }

    @Override
    public String getMode() {
        return "Driving with wheels";
    }
}
