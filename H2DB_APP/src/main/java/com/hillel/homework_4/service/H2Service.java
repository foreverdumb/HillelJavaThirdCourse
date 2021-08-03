package com.hillel.homework_4.service;

import com.hillel.homework_4.pojo.CityPojo;
import com.hillel.homework_4.pojo.CountryPojo;
import com.hillel.homework_4.pojo.RegionPojo;
import com.hillel.homework_4.utils.dbUtil.H2Util;
import com.hillel.homework_4.utils.scannerUtil.InputUtil;
import com.hillel.homework_4.utils.unzipUtil.UnzippingUtil;

public class H2Service {
    public static void addCountry() {
        String country_name = InputUtil.setValueString("country_name");
        H2Util.customInsertCountries(new CountryPojo(country_name));
    }

    public static void addRegion() {
        int id_con = InputUtil.setValueInt("id_con");
        String region_name = InputUtil.setValueString("region_name");
        H2Util.customInsertRegions(new RegionPojo(id_con, region_name));
    }

    public static void addCity() {
        int id_con = InputUtil.setValueInt("id_con");
        int id_reg = InputUtil.setValueInt("id_reg");
        String city_name = InputUtil.setValueString("city_name");
        H2Util.customInsertCities(new CityPojo(id_con, id_reg, city_name));
    }

    public static void findCountryById() {
        int id_con = InputUtil.setValueInt("id_con");
        H2Util.findCountryById(id_con);
    }

    public static void findCountryByName() {
        String country_name = InputUtil.setValueString("country_name");
        H2Util.findCountryByName(country_name);
    }

    public static void findRegionById() {
        int id_reg = InputUtil.setValueInt("id_reg");
        H2Util.findRegionById(id_reg);
    }

    public static void findRegionByName() {
        String region_name = InputUtil.setValueString("region_name");
        H2Util.findRegionByName(region_name);
    }

    public static void findCityById() {
        int id_city = InputUtil.setValueInt("id_city");
        H2Util.findCityById(id_city);
    }

    public static void findCityByName() {
        String city_name = InputUtil.setValueString("city_name");
        H2Util.findCityByName(city_name);
    }

    public static void configureDB() {
        UnzippingUtil.unzip();
        H2Util.createDB();
        H2Util.insertCountries();
        H2Util.insertRegions();
        H2Util.insertCities();
    }
}

