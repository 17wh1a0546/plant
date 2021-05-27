package com.ec.onlineplantnursery.plant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ec.onlineplantnursery.exceptions.ResourceNotFoundException;
import com.ec.onlineplantnursery.plant.entity.Plant;

public interface IPlantService {
	public Plant addPlant(Plant plant);

	public Plant updatePlant(Plant plant);

	public Plant deletePlant(Plant plant);

	public Optional<Plant> viewPlant(int plantId);

	public Optional<Plant> viewPlant(String commonName)throws ResourceNotFoundException;

	public List<Plant> viewAllPlants();

	public Optional<List<Plant>> viewAllPlants(String typeOfPlant)throws ResourceNotFoundException;
}
