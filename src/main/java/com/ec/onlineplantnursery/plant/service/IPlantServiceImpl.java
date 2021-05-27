package com.ec.onlineplantnursery.plant.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.onlineplantnursery.exceptions.ResourceNotFoundException;
import com.ec.onlineplantnursery.plant.entity.Plant;
import com.ec.onlineplantnursery.plant.repository.IPlantRepository;
import com.ec.onlineplantnursery.plant.repository.PlantRepository;

@Service
public class IPlantServiceImpl implements IPlantService{

	@Autowired
	private PlantRepository repo;
	
	@Transactional
	@Override
	public Plant addPlant(Plant plant) {
		repo.save(plant);
		return plant;
	}

	public IPlantServiceImpl(PlantRepository repo) {
		super();
		this.repo = repo;
	}

	public IPlantServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Plant updatePlant(Plant plant) {
		Optional<Plant> existingPlant = repo.findById(plant.getPlantId());
        Plant p = null;
        if(existingPlant.isPresent()) {
        	p = existingPlant.get();
        	p.setPlantId(plant.getPlantId());
            p.setPlantCost(plant.getPlantCost());
            p.setPlantDescription(plant.getPlantDescription());
            p.setPlantHeight(plant.getPlantHeight());
            p.setPlantSpread(plant.getPlantSpread());
            p.setPlantsStock(plant.getPlantsStock());
            p.setTypeOfPlant(plant.getTypeOfPlant());
            p.setBloomTime(plant.getBloomTime());
            p.setCommonName(plant.getCommonName());
            p.setDifficultyLevel(plant.getDifficultyLevel());
            p.setMedicinalOrCulinaryUse(plant.getMedicinalOrCulinaryUse());
            p.setTemparature(plant.getTemparature());
           
    		repo.save(p);
        }
		return p;
	}

	@Override
	public Plant deletePlant(Plant plant) {
		repo.delete(plant);	
		return plant;
	}

	@Override
	public Optional<Plant> viewPlant(int plantId) {
		
		return repo.findById(plantId);
	}

//---------------------------------------------------------------------
	@Override
	public Optional<Plant> viewPlant(String commonName)throws ResourceNotFoundException {

		Optional<Plant> p = repo.viewPlant(commonName);
		
		if(p.isEmpty()) throw new ResourceNotFoundException(commonName);

		
		return p;
	}
//-------------------------------------------------------------------------
	@Override
	public List<Plant> viewAllPlants() {
		
		return repo.findAll();
	}

	@Override
	public Optional<List<Plant>> viewAllPlants(String typeOfPlant)throws ResourceNotFoundException {
		Optional<List<Plant>> pList = repo.viewAllPlants(typeOfPlant);
		
		if(pList==null || pList.isEmpty()) {
			throw new ResourceNotFoundException(typeOfPlant);
		}

		
		return repo.viewAllPlants(typeOfPlant);
	}

}












