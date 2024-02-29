package com.group.libraryapp.repository.fruit;

import com.group.libraryapp.domain.fruit.Fruit;
import com.group.libraryapp.dto.Mission.response.FruitShopAmoutResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Qualifier("first")
@Repository
public class FruitMemoryRepository implements FruitRepository {
    List<Fruit> fruitRepository = new ArrayList<>();

    @Override
    public void saveFruit(String name, String date, Integer price) {
        fruitRepository.add(new Fruit(name, price, date));
    }

    @Override
    public void updateFruit(long id) {
        fruitRepository.set((int) id, new Fruit("수정", 000, "00-00-00"));
    }


    @Override
    public boolean isFruitExist(long id) {
        if (fruitRepository.get((int) id) == null) {
            return false;
        }
        return true;
    }

    @Override
    public FruitShopAmoutResponse getFruitStat(String name) {
        return new FruitShopAmoutResponse(0, fruitRepository.stream().mapToLong(Fruit::getPrice).sum());
    }
}
