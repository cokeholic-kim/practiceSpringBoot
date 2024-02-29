package com.group.libraryapp.repository.fruit;

import com.group.libraryapp.dto.Mission.response.FruitShopAmoutResponse;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FruitSQLRepository implements FruitRepository {

    private final JdbcTemplate template;

    public FruitSQLRepository(JdbcTemplate jdbcTemplate) {
        this.template = jdbcTemplate;
    }

    @Override
    public void saveFruit(String name, String date, Integer price) {
        String sql = "insert into fruitshop (name,warehousedate,price) values (?,?,?)";
        template.update(sql, name, date, price);
    }

    @Override
    public void updateFruit(long id) {
        String sql = "update fruitshop set sell = true where id = ?";
        template.update(sql, id);
    }

    @Override
    public boolean isFruitExist(long id) {
        String readSql = "select id from fruitshop where id = ?";
        return template.query(readSql, (rs, rowNum) -> 0, id).isEmpty();
    }

    @Override
    public FruitShopAmoutResponse getFruitStat(String name) {
        String sql = "SELECT sell, SUM(price) as totalAmount FROM fruitshop WHERE name = ? GROUP BY sell";
        AtomicLong salesAmount = new AtomicLong(0);
        AtomicLong notSalesAmount = new AtomicLong(0);

        template.query(sql, new Object[]{name}, (rs, rowNum) -> {
            boolean status = rs.getBoolean("sell");
            long totalAmount = rs.getLong("totalAmount");

            if (status) {
                salesAmount.addAndGet(totalAmount);
            } else {
                notSalesAmount.addAndGet(totalAmount);
            }
            return null;
        });
        return new FruitShopAmoutResponse(salesAmount.get(), notSalesAmount.get());
    }
}
