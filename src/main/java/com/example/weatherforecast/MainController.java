package com.example.weatherforecast;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


@Controller
public class MainController {

    @ResponseBody
    @GetMapping("/a")
    public String index() {   //zwracanie stringa
        String url = "http://api.openweathermap.org/data/2.5/weather?q=Warszawa&appid=ef2028e98b54649bf1f4c4582631f350";

        RestTemplate restTemplate = new RestTemplate(); // klasa wbudowana w Springu, w WebClientRestamble  "po skrocie rozbiera stringa"    Zamienia nam Jasona na obiekt

        WeatherModel weatherModel = restTemplate.getForObject(url, WeatherModel.class); // get for Object, czyli jaki obiekt ma zamienic, czyli w naszym wypadku string url na weathermodel (klasa)
        return "temp max: " + weatherModel.getMain().getTemp();        //Jason zwraca tablice,  nawiasy kwadratowe oznaczaja liste w jasonie

    }
    @GetMapping("/weathers/search")
    public String search(@RequestParam(required = false)
                                 String city, ModelMap modelMap) {


        WeatherService weatherService = new WeatherService(city);
        modelMap.put("weather", weatherService.GimmeCity());
        return "search";
    }


}
