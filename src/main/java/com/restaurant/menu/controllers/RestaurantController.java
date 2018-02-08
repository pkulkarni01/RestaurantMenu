package com.restaurant.menu.controllers;

import javax.validation.Valid;

import com.restaurant.menu.models.Menu;
import com.restaurant.menu.models.MenuItem;
import com.restaurant.menu.models.Restaurant;
import com.restaurant.menu.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
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

    @PostMapping(value="/restaurants/{id}/menu")
    public Menu addRestaurantMenu(@PathVariable("id") String id, @Valid @RequestBody Menu menu ) {
        Restaurant restaurant = restaurantRepository.findOne(id);
        List<Menu> menus;
        if(restaurant != null) {
            menus = restaurant.getMenu();
            if(menus == null) {
                menus = new ArrayList<>();
            }
                menus.add(menu);
                restaurant.setMenu(menus);
                restaurantRepository.save(restaurant);
                return menu;
        }
        return null;
    }

    @GetMapping("/restaurants/{id}/menu")
    public ResponseEntity<List<Menu>> getAllRestaurantMenus(@PathVariable("id") String id) {
        Restaurant restaurant = restaurantRepository.findOne(id);
        if(restaurant == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<Menu> menus = restaurant.getMenu();
            return new ResponseEntity<>(menus, HttpStatus.OK);
        }
    }

    @PostMapping(value="/restaurants/{rid}/menu/{id}/item")
    public MenuItem addRestaurantMenuItem(@PathVariable("rid") String rid, @PathVariable("id") String id, @Valid @RequestBody MenuItem item ) {
        Restaurant restaurant = restaurantRepository.findOne(rid);
        List<Menu> menus;
        if(restaurant != null) {
            menus = restaurant.getMenu();
            if(menus != null) {
                Iterator<Menu> itr = menus.iterator();
                while(itr.hasNext()) {
                    Menu menu = itr.next();
                    if (menu.getId().equals(id))
                    {
                        List<MenuItem> menuItems = menu.getMenuItems();
                        if(menuItems == null)
                            menuItems = new ArrayList<>();
                        menuItems.add(item);
                        menu.setMenuItems(menuItems);
                        menus.add(menu);
                        restaurant.setMenu(menus);
                        restaurantRepository.save(restaurant);
                        return item;
                    }
                }

            }
        }
        return null;
    }

    @GetMapping("/restaurants/{rid}/menu/{id}/item")
    public ResponseEntity<List<MenuItem>> getAllMenuItems(@PathVariable("rid") String rid, @PathVariable("id") String id) {
        List<MenuItem> menuItems=null;
        Restaurant restaurant = restaurantRepository.findOne(rid);
        if(restaurant == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Menu> menus = restaurant.getMenu();
        if(menus == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
                Iterator<Menu> itr = menus.iterator();
                while(itr.hasNext()) {
                    Menu menu = itr.next();
                    if (menu.getId().equals(id)) {
                        menuItems = menu.getMenuItems();
                    }
                }
            return new ResponseEntity<>(menuItems, HttpStatus.OK);
        }
    }


    @DeleteMapping(value="/restaurants/{id}")
    public void deleteRestaurant(@PathVariable("id") String id) {
        restaurantRepository.delete(id);
    }

    @DeleteMapping(value="/restaurants/{rid}/menu/{id}")
    public void deleteRestaurantMenu(@PathVariable("rid") String rid, @PathVariable("id") String id) {
        Restaurant restaurant = restaurantRepository.findOne(rid);
        List<Menu> menus;
        if(restaurant != null) {
            menus = restaurant.getMenu();
            if(menus != null) {
                Iterator<Menu> itr = menus.iterator();
                while(itr.hasNext()) {
                    Menu menu = itr.next();
                    if (menu.getId().equals(id)) {
                        itr.remove();
                        break;
                    }
                }
                restaurant.setMenu(menus);
                restaurantRepository.save(restaurant);
            }
        }
    }
}