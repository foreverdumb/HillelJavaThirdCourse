package com.hillel.homework_4.utils.scannerUtil;

import java.util.Scanner;

public final class InputUtil {
    public static int setValueInt(String option) {
        int value;
        Scanner scanner = new Scanner(System.in);

        do {
            switch (option) {
                case "id_reg" -> System.out.println("Please, enter region ID: ");
                case "id_con" -> System.out.println("Please, enter country ID: ");
                case "id_city" -> System.out.println("Please, enter city ID: ");
                case "option" -> System.out.println("""
                        1  - Add country;
                        2  - Add region;
                        3  - Add city;
                        4  - Find country by ID;
                        5  - Find country by name;
                        6  - Find region by ID;
                        7  - Find region by name;
                        8  - Find city by ID;
                        9  - Find city by name;
                        10 - Print all countries;
                        11 - Print all regions;
                        12 - Print all cities;
                        13 - Exit + drop tables.
                        """);
            }
            while (!scanner.hasNextInt()) {
                System.out.println("Integer required!");
                scanner.next();
            }
            value = scanner.nextInt();
        } while (value <= 0);
        return value;
    }

    public static String setValueString(String option) {
        String value;
        Scanner scanner = new Scanner(System.in);

        do {
            switch (option) {
                case "country_name" -> System.out.println("Please, enter country name: ");
                case "region_name" -> System.out.println("Please, enter region name: ");
                case "city_name" -> System.out.println("Please, enter city name: ");
            }
            while (!scanner.hasNext("[A-Za-z]+")) {
                System.out.println("English alphabet required!");
                scanner.nextLine();
            }
            value = scanner.nextLine();
        } while (!Character.isUpperCase(value.charAt(0)));
        return value;
    }
}
