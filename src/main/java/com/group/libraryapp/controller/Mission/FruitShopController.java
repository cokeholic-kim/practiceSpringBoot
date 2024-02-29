package com.group.libraryapp.controller.Mission;

import com.group.libraryapp.dto.Mission.request.FruitSaveRequest;
import com.group.libraryapp.dto.Mission.response.FruitResponse;
import com.group.libraryapp.dto.Mission.response.FruitShopAmoutResponse;
import com.group.libraryapp.dto.Mission.response.FruitShopResponse;
import com.group.libraryapp.service.fruit.FruitService;
import com.group.libraryapp.service.fruit.FruitServiceJpa;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fruit")
public class FruitShopController {

    private FruitServiceJpa service;

    public FruitShopController(FruitServiceJpa service) {
        this.service = service;
    }

    @PostMapping
    public void saveFruit(@RequestBody FruitSaveRequest request) {
        service.saveFruit(request.getName(),request.getWarehousingDate(),request.getPrice());
    }

    @PutMapping
    public void updateFruit(@RequestBody Map<String, Object> requestJson) {
        long id = Long.parseLong(requestJson.get("id").toString());
        service.updateFruit(id);
    }

    @GetMapping("/stat")
    public FruitShopAmoutResponse getFruitStat(@RequestParam("name") String name) {
       return service.getFruitStat(name);
    }

    @GetMapping("/count")
    public Map<String,Long> getFruitCount(@RequestParam String name){
        return service.getFruitCount(name);
    }

    @GetMapping("/list")
    public List<FruitResponse> getListCondition(String condition, Integer price){
        return service.getListCondition(condition,price);
    }
}
