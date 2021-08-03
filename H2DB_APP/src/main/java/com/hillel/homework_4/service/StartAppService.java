package com.hillel.homework_4.service;

import com.hillel.homework_4.utils.dbUtil.H2Util;
import com.hillel.homework_4.utils.scannerUtil.InputUtil;

public class StartAppService {
    public static void start() {
        H2Service.configureDB();
        int option;
        do {
            option = InputUtil.setValueInt("option");
            switch (option) {
                case 1 -> {
                    System.out.println("-------------------------------------------");
                    H2Service.addCountry();
                    System.out.println("-------------------------------------------" + "\n");
                }
                case 2 -> {
                    System.out.println("-------------------------------------------");
                    H2Service.addRegion();
                    System.out.println("-------------------------------------------" + "\n");
                }
                case 3 -> {
                    System.out.println("-------------------------------------------");
                    H2Service.addCity();
                    System.out.println("-------------------------------------------" + "\n");
                }
                case 4 -> {
                    System.out.println("-------------------------------------------");
                    H2Service.findCountryById();
                    System.out.println("-------------------------------------------" + "\n");
                }
                case 5 -> {
                    System.out.println("-------------------------------------------");
                    H2Service.findCountryByName();
                    System.out.println("-------------------------------------------" + "\n");
                }
                case 6 -> {
                    System.out.println("-------------------------------------------");
                    H2Service.findRegionById();
                    System.out.println("-------------------------------------------" + "\n");
                }
                case 7 -> {
                    System.out.println("-------------------------------------------");
                    H2Service.findRegionByName();
                    System.out.println("-------------------------------------------" + "\n");
                }
                case 8 -> {
                    System.out.println("-------------------------------------------");
                    H2Service.findCityById();
                    System.out.println("-------------------------------------------" + "\n");
                }
                case 9 -> {
                    System.out.println("-------------------------------------------");
                    H2Service.findCityByName();
                    System.out.println("-------------------------------------------" + "\n");
                }
                case 10 -> {
                    System.out.println("-------------------------------------------");
                    H2Util.printCountries();
                    System.out.println("-------------------------------------------" + "\n");
                }
                case 11 -> {
                    System.out.println("-------------------------------------------");
                    H2Util.printRegions();
                    System.out.println("-------------------------------------------" + "\n");
                }
                case 12 -> {
                    System.out.println("-------------------------------------------");
                    H2Util.printCities();
                    System.out.println("-------------------------------------------" + "\n");
                }
                case 13 -> {
                    System.out.println("-------------------------------------------");
                    H2Util.dropDB();
                    System.out.println("-------------------------------------------" + "\n");
                }
            }
        } while (option != 13);
    }
}
