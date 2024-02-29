package com.group.libraryapp.service.fruit;

import com.group.libraryapp.dto.Mission.response.FruitShopAmoutResponse;

public interface FruitService {
    void saveFruit(String name, String date, Integer price);
    void updateFruit(long id);
    FruitShopAmoutResponse getFruitStat(String name);
}
