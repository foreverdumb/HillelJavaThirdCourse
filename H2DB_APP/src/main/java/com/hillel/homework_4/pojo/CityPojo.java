package com.hillel.homework_4.pojo;

import javax.persistence.*;

@Entity
@Table(name = "Cities")
public class CityPojo {

    @Id
    @Column(name = "id_city")
    int idCity;

    @Column(name = "id_con")
    int idCon;

    @Column(name = "id_reg")
    int idReg;

    @Column(name = "city_name")
    String cityName;

    public CityPojo() {
    }

    public CityPojo(int idCity, int idCon, int idReg, String cityName) {
        this.idCity = idCity;
        this.idCon = idCon;
        this.idReg = idReg;
        this.cityName = cityName;
    }

    public int getIdReg() {
        return idReg;
    }

    public int getIdCon() {
        return idCon;
    }

    public String getCityName() {
        return cityName;
    }

    public void setIdCity(int id_city) {
        this.idCity = id_city;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCon(int id_con) {
        this.idCon = id_con;
    }

    public void setIdReg(int id_reg) {
        this.idReg = id_reg;
    }

    public void setCityName(String city_name) {
        this.cityName = city_name;
    }

    @Override
    public String toString() {
        return "CityPojo{" +
                "id_city=" + idCity +
                ", id_con=" + idCon +
                ", id_reg=" + idReg +
                ", city_name='" + cityName + '\'' +
                '}';
    }
}

