package com.group.libraryapp.dto.Mission.request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

public class FruitSaveRequest {

    private String name;
    private Integer price;
    private String warehousingDate;

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getWarehousingDate() {
        return warehousingDate;
    }
}
