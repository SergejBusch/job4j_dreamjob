package ru.job4j.dream.model;

public class City {
    private int id;
    private String city;

    public City(int id, String city) {
        this.id = id;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        City city1 = (City) o;

        if (id != city1.id) {
            return false;
        }
        return city.equals(city1.city);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + city.hashCode();
        return result;
    }
}
