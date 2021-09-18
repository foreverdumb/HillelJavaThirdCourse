package com.hillel.homework_4.pojo;

import javax.persistence.*;

@Entity
@Table(name = "Countries")
public class CountryPojo {

    @Id
    @Column(name = "id_country")
    int idCountry;

    @Column(name = "country_name")
    String countryName;

    public CountryPojo() {
    }

    public int getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }

    public CountryPojo(int idCountry, String countryName) {
        this.idCountry = idCountry;
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "CountryPojo{" +
                "id_country=" + idCountry +
                ", country_name='" + countryName + '\'' +
                '}';
    }
}
