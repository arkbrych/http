package pl.brych.http.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dogs")
public class DogApi {

    private List<Dog> dogList;

    public DogApi() {
        this.dogList = new ArrayList<>();
        dogList.add(new Dog("Rydzyk", "Kundel"));
        dogList.add(new Dog("Reksio", "Chow Chow"));
        dogList.add(new Dog("Burek", "Somoyed"));
    }

    @GetMapping
    public List<Dog> getDog(@RequestHeader int amount){
        return dogList.subList(0,amount);
    }

    @PostMapping
    public void addDog(@RequestBody Dog dog){
        dogList.add(dog);
    }
}
