package com.hillel.homework_4.service;

import com.hillel.homework_4.pojo.CityPojo;
import com.hillel.homework_4.pojo.CountryPojo;
import com.hillel.homework_4.pojo.RegionPojo;
import com.hillel.homework_4.utils.dbUtil.H2Util;
import com.hillel.homework_4.utils.scannerUtil.InputUtil;
import com.hillel.homework_4.utils.unzipUtil.UnzippingUtil;

public class H2Service {
    public static boolean addCountry() {
        String country_name = InputUtil.setValueString("country_name");
        return H2Util.customInsertCountries(new CountryPojo(country_name));
    }

    public static boolean addRegion() {
        int id_con = InputUtil.setValueInt("id_con");
        String region_name = InputUtil.setValueString("region_name");
        return H2Util.customInsertRegions(new RegionPojo(id_con, region_name));
    }

    public static boolean addCity() {
        int id_con = InputUtil.setValueInt("id_con");
        int id_reg = InputUtil.setValueInt("id_reg");
        String city_name = InputUtil.setValueString("city_name");
        return H2Util.customInsertCities(new CityPojo(id_con, id_reg, city_name));
    }

    public static boolean findCountryById() {
        int id_con = InputUtil.setValueInt("id_con");
        return H2Util.findCountryById(id_con);
    }

    public static boolean findCountryByName() {
        String country_name = InputUtil.setValueString("country_name");
        return H2Util.findCountryByName(country_name);
    }

    public static boolean findRegionById() {
        int id_reg = InputUtil.setValueInt("id_reg");
        return H2Util.findRegionById(id_reg);
    }

    public static boolean findRegionByName() {
        String region_name = InputUtil.setValueString("region_name");
        return H2Util.findRegionByName(region_name);
    }

    public static boolean findCityById() {
        int id_city = InputUtil.setValueInt("id_city");
        return H2Util.findCityById(id_city);
    }

    public static boolean findCityByName() {
        String city_name = InputUtil.setValueString("city_name");
        return H2Util.findCityByName(city_name);
    }

    public static void configureDB() {
        UnzippingUtil.unzip();
        H2Util.createDB();
        H2Util.insertCountries();
        H2Util.insertRegions();
        H2Util.insertCities();
    }
}

