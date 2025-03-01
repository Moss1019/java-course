package com.mossonthetree;

import com.google.gson.Gson;

public class Person extends Traveler implements JsonSerializable {
    public String firstName;

    public String lastName;

    public Address address;
    // A person has-a(n) address

    public TimeSink timeSink;

    protected int numberOfFamilyMembers;

    public void sayHello() {
        System.out.println("Hello, I am " + firstName + " " + lastName);
    }

    @Override
    public String toJson() {
        return new Gson().toJson(this);
    }

    @Override
    public String getMode() {
        return "Walking with my feet";
    }
}
