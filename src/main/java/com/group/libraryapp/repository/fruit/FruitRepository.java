package com.group.libraryapp.repository.fruit;

import com.group.libraryapp.dto.Mission.response.FruitShopAmoutResponse;

public interface FruitRepository {
    void saveFruit(String name,String date,Integer price);

    void updateFruit(long id);

    boolean isFruitExist(long id);
    FruitShopAmoutResponse getFruitStat(String name);
}
