package com.mossonthetree;

public abstract class Traveler {
    int kilometersTraveled = 0;

    public void travel(int kilometers) {
        kilometersTraveled += kilometers;
    }

    public abstract String getMode();

    protected void calculateDistance() {
        System.out.println("Calculating distance...");
    }
}
