package com.group.libraryapp.dto.Mission.response;

public class FruitShopResponse {
    private long id;
    private String name;
    private String warehouseDate;
    private long price;
    private Boolean sell;

    public FruitShopResponse(long id, String name, String warehouseDate, long price, Boolean sell) {
        this.id = id;
        this.name = name;
        this.warehouseDate = warehouseDate;
        this.price = price;
        this.sell = sell;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getWarehouseDate() {
        return warehouseDate;
    }

    public long getPrice() {
        return price;
    }

    public Boolean getSell() {
        return sell;
    }
}
