package com.example.weatherforecast;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


@Controller
public class MainController {

    @ResponseBody //zwraca stringa na stronie
    @GetMapping("/a")
    public String index() {   //metoda do zwracania stringa, czyli w naszym wypadku string (adres html)
        String url = "http://api.openweathermap.org/data/2.5/weather?q=Warszawa&appid=ef2028e98b54649bf1f4c4582631f350";

        RestTemplate restTemplate = new RestTemplate();
        // klasa wbudowana w Springu, w WebClientRestamble, sluzy do zmiany stringa na obiekt i daje do Jasona
        // "po skrocie rozbiera stringa"    Zamienia nam Jasona na obiekt

        WeatherModel weatherModel = restTemplate.getForObject(url, WeatherModel.class);
        // get for Object, czyli jaki obiekt ma zamienic, czyli w naszym wypadku string url na weathermodel (klasa)
        return "temp max: " + weatherModel.getMain().getTemp();
        //zwracamy w klasie weathermodel, do metody main i pozniej do temepratury w podklasie

        //Jason zwraca tablice,  nawiasy kwadratowe oznaczaja lsite w jasonie
    }
    @GetMapping("/weathers/search")
    public String search(@RequestParam(required = false)
                                 String city, ModelMap modelMap) {

//Tworze obiekt WeatherService  by moc sie odniesc do drugiej klasy, klasy i od razu do obiektu city (private city)
        WeatherService weatherService = new WeatherService(city);
        modelMap.put("weather", weatherService.GimmeCity());
        //Odnosimy sie do "weather" jako klucz,    oraz do wartosci czyli obiektu weatherService i do metody GimmeCity w ktorej podajemy obliczenia
        return "search";
    }


}
