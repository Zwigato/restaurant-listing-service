package com.app.restaurantlisting.controller;

import com.app.restaurantlisting.dto.RestaurantDTO;
import com.app.restaurantlisting.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/fetchAllRestaurants")
    public ResponseEntity<List<RestaurantDTO>> fetchAllRestaurants(){
        List<RestaurantDTO> allRestaurants = restaurantService.findAllRestaurants();
        return new ResponseEntity<>(allRestaurants, HttpStatus.OK);
    }

    @PostMapping("/addRestaurant")
    public ResponseEntity<RestaurantDTO> addRestaurantInDB(@RequestBody RestaurantDTO restaurantDTO) {
        return new ResponseEntity<>(restaurantService.addRestaurantInDB(restaurantDTO), HttpStatus.CREATED);
    }

    @GetMapping("/getRestaurant/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable  Integer id) {
        return restaurantService.getRestaurantByID(id);
    }
}
