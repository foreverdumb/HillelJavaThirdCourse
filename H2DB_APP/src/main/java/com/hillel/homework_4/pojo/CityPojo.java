package com.hillel.homework_4.pojo;

public class CityPojo {
    int id_con;
    int id_reg;
    String city_name;

    public CityPojo() {
    }

    public CityPojo(int id_con, int id_reg, String city_name) {
        this.id_con = id_con;
        this.id_reg = id_reg;
        this.city_name = city_name;
    }

    public int getId_reg() {
        return id_reg;
    }

    public void setId_reg(int id_reg) {
        this.id_reg = id_reg;
    }

    public int getId_con() {
        return id_con;
    }

    public void setId_con(int id_con) {
        this.id_con = id_con;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }
}
