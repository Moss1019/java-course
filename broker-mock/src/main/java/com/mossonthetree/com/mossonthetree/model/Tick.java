package com.mossonthetree.com.mossonthetree.model;

import java.time.LocalDateTime;

public class Tick {
    public String epic;

    public double bid;

    public double ask;

    public double change;

    public LocalDateTime time;

    public Tick() {
    }

    public Tick(String epic, double bid, double ask, double change, LocalDateTime time) {
        this.epic = epic;
        this.bid = bid;
        this.ask = ask;
        this.change = change;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Tick{" +
                "epic='" + epic + '\'' +
                ", bid=" + bid +
                ", ask=" + ask +
                ", change=" + change +
                ", time=" + time +
                '}';
    }
}
