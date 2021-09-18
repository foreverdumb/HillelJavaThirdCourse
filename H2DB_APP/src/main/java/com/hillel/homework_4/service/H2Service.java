package com.hillel.homework_4.service;

import com.hillel.homework_4.pojo.CityPojo;
import com.hillel.homework_4.pojo.CountryPojo;
import com.hillel.homework_4.pojo.RegionPojo;
import com.hillel.homework_4.utils.dbUtil.H2Util;
import com.hillel.homework_4.utils.scannerUtil.InputUtil;
import com.hillel.homework_4.utils.unzipUtil.UnzippingUtil;

public final class H2Service {
    public static boolean addCountry() {
        int idCon = InputUtil.setValueInt("id_con");
        String countryName = InputUtil.setValueString("country_name");
        return H2Util.customInsertCountries(new CountryPojo(idCon, countryName));
    }

    public static boolean addRegion() {
        int idReg = InputUtil.setValueInt("id_reg");
        int idCon = InputUtil.setValueInt("id_con");
        String region_name = InputUtil.setValueString("region_name");
        return H2Util.customInsertRegions(new RegionPojo(idReg, idCon, region_name));
    }

    public static boolean addCity() {
        int idCity = InputUtil.setValueInt("id_city");
        int idCon = InputUtil.setValueInt("id_con");
        int idReg = InputUtil.setValueInt("id_reg");
        String city_name = InputUtil.setValueString("city_name");
        return H2Util.customInsertCities(new CityPojo(idCity, idCon, idReg, city_name));
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

    public static boolean configureDB() {
        boolean isUnzipped = UnzippingUtil.unzip();
        boolean isCreated = H2Util.createDB();
        boolean isInsertedCsvCountries = H2Util.insertCountries();
        boolean isInsertedCsvRegions = H2Util.insertRegions();
        boolean isInsertedCsvCities = H2Util.insertCities();

        return isUnzipped && isCreated && isInsertedCsvCities && isInsertedCsvCountries && isInsertedCsvRegions;
    }
}

