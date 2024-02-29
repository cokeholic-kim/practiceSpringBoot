package com.group.libraryapp.dto.Mission.response;

public class FruitShopAmoutResponse {
    private long salesAmount;
    private long notSalesAmount;

    public FruitShopAmoutResponse(long salesAmount, long notSalesAmount) {
        this.salesAmount = salesAmount;
        this.notSalesAmount = notSalesAmount;
    }

    public long getSalesAmount() {
        return salesAmount;
    }

    public long getNotSalesAmount() {
        return notSalesAmount;
    }
}
