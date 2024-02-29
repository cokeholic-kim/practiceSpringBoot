package com;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class TestClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Integer> dice = createDice(6);

        int count = getCount(scanner);
        condition(dice,count);
        print(dice);
    }

    private static Map<Integer, Integer> createDice(int a) {
        Map<Integer, Integer> dice = new HashMap<>();
        for (int i = 0; i < a; i++) {
            dice.put(i + 1, 0); // key(주사위번호) : value(해당번호가 나온 횟수)
        }
        return dice;
    }

    private static int getCount(Scanner scanner) {
        System.out.println("숫자를 입력하세요 : ");
        return scanner.nextInt();
    }

    private static void condition(Map<Integer, Integer> dice,int count) {
        for (int i = 0; i < count; i++) {
            int b = (int) (Math.random() * dice.size()) + 1;

            dice.put(b, dice.getOrDefault(b, 0) + 1);
        }
    }

    private static void print(Map<Integer, Integer> dice) {
        for (Entry<Integer, Integer> entry : dice.entrySet()) {
            System.out.printf("%d은 %d번 나왔습니다.\n", entry.getKey(),entry.getValue());
        }
    }
}

