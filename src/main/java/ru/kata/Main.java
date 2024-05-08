package ru.kata;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два числа (арабские или римские) от 1 до 10: ");
        String input = scanner.nextLine();

        System.out.println("Результат : " + calc(input));

    }

    public static String calc(String input) throws Exception {
        String[] parts = input.split(" ");
        if (parts.length != 3) {
            throw new Exception("Введите два числа");
        }

        int num1, num2;
        boolean isRoman = false;

        // Проверяем, является ли числа римскими
        if (RomanNumbers.mapRomanToArabian.containsKey(parts[0]) && RomanNumbers.mapRomanToArabian.containsKey(parts[2])) {
            num1 = RomanNumbers.mapRomanToArabian.get(parts[0]);
            num2 = RomanNumbers.mapRomanToArabian.get(parts[2]);
            isRoman = true;
        } else {
            try {
                num1 = Integer.parseInt(parts[0]);
                num2 = Integer.parseInt(parts[2]);
            } catch (NumberFormatException e) {
                throw new Exception("Используются одновременно разные системы счисления");
            }
        }

        if (num1 <= 0 || num1 > 10 || num2 <= 0 || num2 > 10) {
            throw new Exception("Введите числа от 1 до 10");
        }

        char operation = parts[1].charAt(0);
        int result = calculate(num1, num2, operation);

        if (isRoman) {
            return (CalculateUtil.arabianToRoman(result));
        }
        return String.valueOf(result);
    }

     static int calculate(int num1, int num2, char operation) throws Exception {
        int result;
        switch (operation) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
            default:
                throw new Exception("Неверная операция");
        }
        return result;
    }
}

class CalculateUtil{

     static String arabianToRoman(int number) throws Exception{
        if (number < 1) {
            throw new Exception("В римской системе нет отрицательных чисел");
        }
        if(number > 10){
            return RomanNumbers.mapArabianToRoman.get(number / 10 * 10) + RomanNumbers.mapArabianToRoman.get(number % 10);
        }
        return RomanNumbers.mapArabianToRoman.get(number);
    }
}

class RomanNumbers{
    static final Map<String, Integer> mapRomanToArabian = new HashMap<>();
    static final Map<Integer, String> mapArabianToRoman = new HashMap<>();

    static {
        mapRomanToArabian.put("I", 1);
        mapRomanToArabian.put("II", 2);
        mapRomanToArabian.put("III", 3);
        mapRomanToArabian.put("IV", 4);
        mapRomanToArabian.put("V", 5);
        mapRomanToArabian.put("VI", 6);
        mapRomanToArabian.put("VII", 7);
        mapRomanToArabian.put("VIII", 8);
        mapRomanToArabian.put("IX", 9);
        mapRomanToArabian.put("X", 10);

        mapArabianToRoman.put(0, "");
        mapArabianToRoman.put(1, "I");
        mapArabianToRoman.put(2, "II");
        mapArabianToRoman.put(3, "III");
        mapArabianToRoman.put(4, "IV");
        mapArabianToRoman.put(5, "V");
        mapArabianToRoman.put(6, "VI");
        mapArabianToRoman.put(7, "VII");
        mapArabianToRoman.put(8, "VIII");
        mapArabianToRoman.put(9, "IX");
        mapArabianToRoman.put(10, "X");
        mapArabianToRoman.put(20, "XX");
        mapArabianToRoman.put(30, "XXX");
        mapArabianToRoman.put(40, "XL");
        mapArabianToRoman.put(50, "L");
        mapArabianToRoman.put(60, "LX");
        mapArabianToRoman.put(70, "LXX");
        mapArabianToRoman.put(80, "LXXX");
        mapArabianToRoman.put(90, "XC");
        mapArabianToRoman.put(100, "C");
    }
}