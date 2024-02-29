package com.group.libraryapp.dto.Mission.response;

public class FruitResponse {
    private String name;
    private Integer price;
    private String wareHouseDate;

    public FruitResponse(String name, Integer price, String wareHouseDate) {
        this.name = name;
        this.price = price;
        this.wareHouseDate = wareHouseDate;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getWareHouseDate() {
        return wareHouseDate;
    }
}
