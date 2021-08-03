package com.hillel.homework_4.pojo;

public class RegionPojo {
    int id_con;
    String region_name;

    public RegionPojo() {
    }

    public RegionPojo(int id_country, String region_name) {
        this.id_con = id_country;
        this.region_name = region_name;
    }

    public int getId_con() {
        return id_con;
    }

    public void setId_con(int id_con) {
        this.id_con = id_con;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }
}
