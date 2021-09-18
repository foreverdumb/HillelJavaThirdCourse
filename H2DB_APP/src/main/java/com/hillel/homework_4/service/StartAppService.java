package com.hillel.homework_4.service;

import com.hillel.homework_4.utils.dbUtil.H2Util;
import com.hillel.homework_4.utils.hibernateUtil.HibernateUtil;
import com.hillel.homework_4.utils.scannerUtil.InputUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class StartAppService {
    private static final Logger root = LogManager.getRootLogger();

    public static void start() {
        if (H2Service.configureDB()) root.log(Level.INFO, "Configured...");
        int option;

        do {
            option = InputUtil.setValueInt("option");
            switch (option) {
                case 1 -> {
                    System.out.println("-------------------------------------------");
                    if (H2Service.addCountry()) root.log(Level.INFO, "Added...");

                    System.out.println("-------------------------------------------" + "\n");
                }
                case 2 -> {
                    System.out.println("-------------------------------------------");
                    if (H2Service.addRegion()) root.log(Level.INFO, "Added...");
                    System.out.println("-------------------------------------------" + "\n");
                }
                case 3 -> {
                    System.out.println("-------------------------------------------");
                    if (H2Service.addCity()) root.log(Level.INFO, "Added...");
                    System.out.println("-------------------------------------------" + "\n");
                }
                case 4 -> {
                    System.out.println("-------------------------------------------");
                    if (H2Service.findCountryById()) root.log(Level.INFO, "Found...");
                    System.out.println("-------------------------------------------" + "\n");
                }
                case 5 -> {
                    System.out.println("-------------------------------------------");
                    if (H2Service.findCountryByName()) root.log(Level.INFO, "Found...");
                    System.out.println("-------------------------------------------" + "\n");
                }
                case 6 -> {
                    System.out.println("-------------------------------------------");
                    if (H2Service.findRegionById()) root.log(Level.INFO, "Found...");
                    System.out.println("-------------------------------------------" + "\n");
                }
                case 7 -> {
                    System.out.println("-------------------------------------------");
                    if (H2Service.findRegionByName()) root.log(Level.INFO, "Found...");
                    System.out.println("-------------------------------------------" + "\n");
                }
                case 8 -> {
                    System.out.println("-------------------------------------------");
                    if (H2Service.findCityById()) root.log(Level.INFO, "Found...");
                    System.out.println("-------------------------------------------" + "\n");
                }
                case 9 -> {
                    System.out.println("-------------------------------------------");
                    if (H2Service.findCityByName()) root.log(Level.INFO, "Found...");
                    System.out.println("-------------------------------------------" + "\n");
                }
                case 10 -> {
                    System.out.println("-------------------------------------------");
                    if (H2Util.printCountries()) root.log(Level.INFO, "Printed...");
                    System.out.println("-------------------------------------------" + "\n");
                }
                case 11 -> {
                    System.out.println("-------------------------------------------");
                    if (H2Util.printRegions()) root.log(Level.INFO, "Printed...");
                    System.out.println("-------------------------------------------" + "\n");
                }
                case 12 -> {
                    System.out.println("-------------------------------------------");
                    if (H2Util.printCities()) root.log(Level.INFO, "Printed...");
                    System.out.println("-------------------------------------------" + "\n");
                }
                case 13 -> {
                    System.out.println("-------------------------------------------");
                    if (H2Util.dropDB()) root.log(Level.INFO, "Dropped...");
                    HibernateUtil.shutdown();
                    System.out.println("-------------------------------------------" + "\n");
                }
            }
        } while (option != 13);
    }
}
