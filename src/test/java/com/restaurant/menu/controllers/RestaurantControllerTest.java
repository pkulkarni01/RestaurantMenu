package com.restaurant.menu.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.menu.models.Menu;
import com.restaurant.menu.models.MenuItem;
import com.restaurant.menu.models.Restaurant;
import com.restaurant.menu.repository.RestaurantRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.*;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void getRestaurants() throws Exception
    {
        mvc.perform(get("/api/restaurants").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void postRestaurants() throws Exception
    {
        Restaurant restaurant=new Restaurant();
        restaurant.setId("1");
        restaurant.setName("Pizza place");
        restaurant.setAddress("Dallas,Texas");
        mvc.perform(post("/api/restaurants").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(restaurant))).andExpect(status().isOk());
    }

    @Test
    public void getMenus() throws Exception
    {
        mvc.perform(get("/api/restaurants/1/menu").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }


    @Test
    public void postMenus() throws Exception
    {
        Menu menu = new Menu();
        menu.setId("1");
        menu.setName("Lunch");
        mvc.perform(post("/api/restaurants/1/menu").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(menu))).andExpect(status().isOk());
    }

    @Test
    public void getMenuItems() throws Exception
    {
        mvc.perform(get("/api/restaurants/1/menu/1/item").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }


    @Test
    public void postMenuItems() throws Exception
    {
        MenuItem menuItem = new MenuItem();
        menuItem.setId("1");
        menuItem.setName("Pizza");
        menuItem.setDescription("Cheese pizza with crust");
        menuItem.setPrice(20);
        mvc.perform(post("/api/restaurants/1/menu/1/item").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(menuItem))).andExpect(status().isOk());
    }
}