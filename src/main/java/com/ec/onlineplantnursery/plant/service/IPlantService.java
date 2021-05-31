package com.ec.onlineplantnursery.plant.service;

import java.util.List;
import java.util.Optional;

import com.ec.onlineplantnursery.exceptions.ResourceNotFoundException;
import com.ec.onlineplantnursery.plant.entity.Plant;

public interface IPlantService {
	Plant addPlant(Plant plant);

	Plant updatePlant(Plant plant);

	Plant deletePlant(Plant plant);

	Plant viewPlant(int plantId);

	Optional<Plant> viewPlant(String commonName) throws ResourceNotFoundException;

	List<Plant> viewAllPlants();

	Optional<List<Plant>> viewAllPlants(String typeOfPlant) throws ResourceNotFoundException;
}
