package com.mossonthetree.model;

public class Trade {
    public String tradeId;

    public String accountId;

    public String instrument;

    public double buyPrice;

    public boolean isOpen;

    public Trade() {

    }

    public Trade(String tradeId, String accountId, String instrument, double buyPrice, boolean isOpen) {
        this.tradeId = tradeId;
        this.accountId = accountId;
        this.instrument = instrument;
        this.buyPrice = buyPrice;
        this.isOpen = isOpen;
    }
}
