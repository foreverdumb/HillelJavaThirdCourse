package com.hillel.homework_4.pojo;

import javax.persistence.*;

@Entity
@Table(name = "Regions")
public class RegionPojo {

    @Id
    @Column(name = "id_region")
    int idRegion;

    @Column(name = "id_con")
    int idCon;

    @Column(name = "region_name")
    String regionName;

    public RegionPojo() {
    }

    public RegionPojo(int idRegion, int id_country, String regionName) {
        this.idRegion = idRegion;
        this.idCon = id_country;
        this.regionName = regionName;
    }

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int id_region) {
        this.idRegion = id_region;
    }

    public int getIdCon() {
        return idCon;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setIdCon(int id_con) {
        this.idCon = id_con;
    }

    public void setRegionName(String region_name) {
        this.regionName = region_name;
    }

    @Override
    public String toString() {
        return "RegionPojo{" +
                "id_region=" + idRegion +
                ", id_con=" + idCon +
                ", region_name='" + regionName + '\'' +
                '}';
    }
}
