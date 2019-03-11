package com.example.weatherforecast;

public class WeatherModel {

    /*
    tworzymy klase wewnetrzna, bo w main w tym konkretnym jasonie, bo obiekt temp miesci sie w klasie main

     */
    private  WeatherDetails main;

    public WeatherDetails getMain() {
        return main;
    }

    public void setMain(WeatherDetails main) {
        this.main = main;
    }


    public static class WeatherDetails{

        private double temp;

        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp - 273;
        }
    }




}
