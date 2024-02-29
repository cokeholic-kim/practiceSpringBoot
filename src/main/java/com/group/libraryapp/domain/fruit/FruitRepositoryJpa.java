package com.group.libraryapp.domain.fruit;

import com.group.libraryapp.dto.Mission.response.FruitShopAmoutResponse;
import com.group.libraryapp.dto.Mission.response.FruitStatAmountResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FruitRepositoryJpa extends JpaRepository<Fruit, Long> {
    @Query(value = "SELECT sell, SUM(price) as totalAmount FROM fruit WHERE name = :name GROUP BY sell", nativeQuery = true)
    List<Object> findSellAndTotalAmountByName(@Param("name") String name);

//    @Query(value = "SELECT "
//            + "  SUM(CASE WHEN sell = TRUE THEN price ELSE 0 END) AS salesAmount, "
//            + "  SUM(CASE WHEN sell = FALSE THEN price ELSE 0 END) AS notSalesAmount "
//            + "FROM fruit "
//            + "WHERE name = :name", nativeQuery = true)
//    FruitShopAmoutResponse findSellAndTotalAmountByName(@Param("name") String name);
    Long countByName(String name);
    List<Fruit> findByPriceGreaterThan(Integer price);
    List<Fruit> findByPriceLessThan(Integer price);
}
