package com.group.libraryapp.domain.fruit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fruit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
    @Column(nullable = false, length = 20)
    private String name;
    private Integer price;
    private String wareHouseDate;
    private boolean sell = false;

    private static long seq = 1;

    public Fruit(String name, Integer price, String wareHouseDate) {
        this.name = name;
        this.price = price;
        this.wareHouseDate = wareHouseDate;
    }

    public Fruit() {

    }


    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public String getWareHouseDate() {
        return wareHouseDate;
    }
    public void setSell(){
        this.sell = true;
    }
}
