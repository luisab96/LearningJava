package com.UpandRunning.e1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class RestApiDemoController {
    private List<Coffee> coffees = new ArrayList<>();


    public RestApiDemoController(){
        coffees.addAll(List.of(
                        new Coffee("Café Cereza"),
                        new Coffee("Café Ganador"),
                        new Coffee("Café Lareño"),
                        new Coffee("Café Três Pontas"),
                        new Coffee("23455","Café Loco")
        ));
    }

//    @RequestMapping(value = "/coffees", method = RequestMethod.GET)
    @GetMapping("/coffees")
    Iterable<Coffee> getCoffees(){
        return coffees;
    }

    @GetMapping("/coffees/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable String id){
        for (Coffee c: coffees){
            if (c.getId().equals(id)){
                return  Optional.of(c);
            }
        }
        return Optional.empty();
    }

    @PostMapping("/coffees")
    Coffee addingCoffee(@RequestBody Coffee nCoffee){
        coffees.add(nCoffee);
        return nCoffee;
    }

    @PutMapping("/coffees/{id}")
    Coffee putCoffee(@PathVariable String id, @RequestBody Coffee coffee){

        int coffeeIndex = -1;

        for (Coffee c: coffees){
            if(c.getId().equals(id)){
                coffeeIndex = coffees.indexOf(c);
                coffees.set(coffeeIndex, coffee);
            }
        }
        return (coffeeIndex == -1) ?
                new ResponseEntity<>(addingCoffee(coffee), HttpStatus.CREATED).getBody() :
                new ResponseEntity<>(coffee, HttpStatus.OK).getBody();
    }

    @DeleteMapping("/coffees/{id}")
    void deleteCoffee(@PathVariable String id){
        coffees.removeIf(c -> c.getId().equals(id));
    }
}
