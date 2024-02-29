package com.group.libraryapp.service.fruit;

import com.group.libraryapp.domain.fruit.Fruit;
import com.group.libraryapp.domain.fruit.FruitRepositoryJpa;
import com.group.libraryapp.dto.Mission.response.FruitResponse;
import com.group.libraryapp.dto.Mission.response.FruitShopAmoutResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class FruitServiceJpa implements FruitService {

    private final FruitRepositoryJpa repository;

    public FruitServiceJpa(FruitRepositoryJpa repository) {
        this.repository = repository;
    }

    @Override
    public void saveFruit(String name, String date, Integer price) {
        repository.save(new Fruit(name, price, date));
    }

    @Override
    public void updateFruit(long id) {
        Fruit fruit = repository.findById(id).orElseThrow(IllegalArgumentException::new);
        fruit.setSell();
        repository.save(fruit);
    }

    @Override
    public FruitShopAmoutResponse getFruitStat(String name) {
        return mapToAmount(repository.findSellAndTotalAmountByName(name));
    }
//    @Override
//    public FruitShopAmoutResponse getFruitStat(String name) {
//        return repository.findSellAndTotalAmountByName(name);
//    }

    public Map<String, Long> getFruitCount(String name) {
        long count = repository.countByName(name);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("count", count);
        return resultMap;
    }

    private FruitShopAmoutResponse mapToAmount(List<Object> result) {
        AtomicLong salesAmount = new AtomicLong(0);
        AtomicLong notSalesAmount = new AtomicLong(0);
        result.stream()
                .map(obj -> (Object[]) obj)
                .forEach(rs -> {
                    boolean sell = (boolean) rs[0];
                    long totalAmount = ((BigDecimal) rs[1]).longValue();
                    if (sell) {
                        salesAmount.addAndGet(totalAmount);
                    } else {
                        notSalesAmount.addAndGet(totalAmount);
                    }
                });
        return new FruitShopAmoutResponse(salesAmount.get(), notSalesAmount.get());
    }

    public List<FruitResponse> getListCondition(String condition, Integer price) {
        if (condition.equals("GTE")) {
            // select * from fruit where price > 16000
            return repository.findByPriceGreaterThan(price).stream()
                    .map(fruit -> new FruitResponse(fruit.getName(), Integer.valueOf((int) fruit.getPrice()),
                            fruit.getWareHouseDate()))
                    .collect(Collectors.toList());
        } else{
            return repository.findByPriceLessThan(price).stream()
                    .map(fruit -> new FruitResponse(fruit.getName(), Integer.valueOf((int) fruit.getPrice()), fruit.getWareHouseDate()))
                    .collect(Collectors.toList());
        }
    }
}
