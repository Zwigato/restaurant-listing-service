package com.app.restaurantlisting.service;

import com.app.restaurantlisting.dto.RestaurantDTO;
import com.app.restaurantlisting.entity.Restaurant;
import com.app.restaurantlisting.mapper.RestaurantMapper;
import com.app.restaurantlisting.repo.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepo restaurantRepo;

    public List<RestaurantDTO> findAllRestaurants() {
        List<Restaurant> restaurants =  restaurantRepo.findAll();
        // map list of restaurants to restaurantsDTO
        return restaurants.stream().map(restaurant->
                RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant))
                .collect(Collectors.toList());
    }

    public RestaurantDTO addRestaurantInDB(RestaurantDTO restaurantDTO) {
        // change DTO to entity
        Restaurant savedRestaurant = restaurantRepo.save(RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(restaurantDTO));
        // change entity to DTO
        return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(savedRestaurant);
    }

    public ResponseEntity<RestaurantDTO> getRestaurantByID(Integer restaurantID) {
        Optional<Restaurant> restaurant =  restaurantRepo.findById(restaurantID);
        if(restaurant.isPresent())
            return new ResponseEntity<RestaurantDTO>(RestaurantMapper.INSTANCE.
                    mapRestaurantToRestaurantDTO(restaurant.get()), HttpStatus.OK);
        return new ResponseEntity<RestaurantDTO>((RestaurantDTO) null, HttpStatus.NOT_FOUND);
    }
}
