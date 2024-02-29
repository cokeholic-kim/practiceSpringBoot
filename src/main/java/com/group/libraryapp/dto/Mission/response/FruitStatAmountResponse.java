package com.group.libraryapp.dto.Mission.response;

public class FruitStatAmountResponse {
    private String sell;
    private long totalAmount;

    public FruitStatAmountResponse(String sell, long totalAmount) {
        this.sell = sell;
        this.totalAmount = totalAmount;
    }

    public String getSell() {
        return sell;
    }

    public long getTotalAmount() {
        return totalAmount;
    }
}
