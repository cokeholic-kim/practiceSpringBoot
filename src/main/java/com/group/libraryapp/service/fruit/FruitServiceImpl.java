package com.group.libraryapp.service.fruit;

import com.group.libraryapp.dto.Mission.response.FruitShopAmoutResponse;
import com.group.libraryapp.repository.fruit.FruitRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class FruitServiceImpl implements FruitService{

    private final FruitRepository repository;

    public FruitServiceImpl(@Qualifier("first")FruitRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveFruit(String name, String date, Integer price) {
        repository.saveFruit(name,date,price);
    }

    @Override
    public void updateFruit(long id) {
        if(repository.isFruitExist(id)){
            throw new IllegalArgumentException("수정할 자료가 없습니다.");
        }
        repository.updateFruit(id);
    }

    @Override
    public FruitShopAmoutResponse getFruitStat(String name) {
        return repository.getFruitStat(name);
    }
}
