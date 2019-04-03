package com.example.weatherforecast;

import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;


public class WeatherService {
    private String result;
    private String city;
    DecimalFormat df = new DecimalFormat();

    public WeatherService(String city) {
        this.city = city;
    }

    public String GimmeCity() {
        if (city != null) {
            String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=ef2028e98b54649bf1f4c4582631f350";
            RestTemplate restTemplate = new RestTemplate();
            WeatherModel weatherModel = restTemplate.getForObject(url, WeatherModel.class);  //Obiekt ktory odnosi sie do restTemplate czyli klasy wbudowanej w Springa, czyli jaki obiekt (u nas String url) na weatherModel.class
            result = df.format(weatherModel.getMain().getTemp()) + "C ";
        }
        return result;
    }
}
