package com.restaurant.menu.controllers;

import javax.validation.Valid;
import com.restaurant.menu.models.Restaurant;
import com.restaurant.menu.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestaurantController {

    @Autowired
    RestaurantRepository restaurantRepository;

    @GetMapping("/restaurants")
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @PostMapping("/restaurants")
    public Restaurant createRestaurant(@Valid @RequestBody Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @GetMapping(value="/restaurants/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable("id") String id) {
        Restaurant restaurant = restaurantRepository.findOne(id);
        if(restaurant == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        }
    }

    @DeleteMapping(value="/restaurants/{id}")
    public void deleteRestaurant(@PathVariable("id") String id) {
        restaurantRepository.delete(id);
    }
}