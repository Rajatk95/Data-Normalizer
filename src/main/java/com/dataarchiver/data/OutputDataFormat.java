package com.dataarchiver.data;

import java.util.Objects;

public class OutputDataFormat {

    private String name;
    private String city;
    private String country;
    private final static char SEP = ',';
    private final static String HEADER = "name"+SEP+"city"+SEP+"country"+"\n";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public static String getHEADER() { return HEADER; }

    @Override
    public String toString() { return name+SEP+city+SEP+country+"\n"; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutputDataFormat outputDataFormat = (OutputDataFormat) o;
        return Objects.equals(name, outputDataFormat.name) &&
                Objects.equals(city, outputDataFormat.city) &&
                Objects.equals(country, outputDataFormat.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, city, country);
    }

    public boolean isDataMissing() {
        return (this.name == null && this.city == null && this.country == null);
    }

}
