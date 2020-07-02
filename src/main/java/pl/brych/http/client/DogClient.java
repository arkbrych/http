package pl.brych.http.client;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import pl.brych.http.api.Dog;

import java.util.stream.Stream;

@Controller
public class DogClient {

    private RestTemplate restTemplate;

    public DogClient() {
        this.restTemplate = new RestTemplate();
    }

    @EventListener(ApplicationReadyEvent.class)
    @Order(2)
    private void getDogs() {

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("amount", "4");
        HttpEntity httpEntity = new HttpEntity(headers);

        ResponseEntity<Dog[]> exchange = restTemplate.exchange(
                "http://localhost:8080/dogs",
                HttpMethod.GET,
                httpEntity,
                Dog[].class);

        Stream.of(exchange.getBody()).forEach(System.out::println);
    }

    @EventListener(ApplicationReadyEvent.class)
    @Order(1)
    private void addDogs() {

        Dog dog = new Dog("Tabsik", "Chow chow");

        HttpEntity httpEntity = new HttpEntity(dog);

        restTemplate.exchange(
                "http://localhost:8080/dogs",
                HttpMethod.POST,
                httpEntity,
                Void.class);

    }


}
