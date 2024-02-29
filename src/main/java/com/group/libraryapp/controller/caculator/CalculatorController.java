package com.group.libraryapp.controller.caculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorArrayRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMultiplyRequest;
import com.group.libraryapp.dto.calculator.response.CalcResult;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import net.bytebuddy.asm.Advice.Local;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    @GetMapping("/add")
    public int addTwoNumbers(CalculatorAddRequest request) {
        return request.getNumber1() + request.getNumber2();
    }

    @PostMapping("/multiply")
    public int multiplyTWoNumbers(@RequestBody CalculatorMultiplyRequest request){
        return  request.getNumber1() * request.getNumber2();

    }

    @GetMapping("/api/v1/calc")
    public CalcResult returnResult(CalculatorAddRequest request) {
        return new CalcResult(request);
    }

    @GetMapping("/api/v1/dayOfWeek")
    public String calcDate(String date){
        int[] dateArray = parseDate(date);
        LocalDate localDate = LocalDate.of(dateArray[0],dateArray[1],dateArray[2]);
        return localDate.getDayOfWeek().toString();
    }

    private int[] parseDate(String dateString) {
        return Arrays.stream(dateString.split("-"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    @PostMapping("/api/v1/arraySum")
    public Integer arraySum(@RequestBody CalculatorArrayRequest numbers){
        return numbers.getNumbers().stream().mapToInt(Integer::intValue).sum();
    }


}
