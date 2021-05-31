package com.ec.onlineplantnursery.plant.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.onlineplantnursery.exceptions.ResourceNotFoundException;
import com.ec.onlineplantnursery.plant.entity.Plant;
import com.ec.onlineplantnursery.plant.repository.IPlantRepository;


@Service
public class IPlantServiceImpl implements IPlantService{

	@Autowired
	IPlantRepository plantRepo;
	
	
	
	public IPlantServiceImpl() {
		super();
	}

	

	public IPlantServiceImpl(IPlantRepository plantRepo) {
		super();
		this.plantRepo = plantRepo;
	}


	/*Method Name:addPlant
	 *Parameters:Plant
	 *ReturnType:Plant
	 *Author Name:Ambidi Swathi
	 *Created Date: 22/05/2021 */
	

	@Override
	@Transactional
	public Plant addPlant(Plant plant) {
		
		plantRepo.save(plant);
		return plant;

	}

	/*Method Name:updatePlant
	 *Parameters:Plant
	 *ReturnType:Plant
	 *Author Name:Ambidi Swathi
	 *Created Date: 22/05/2021 */
	
	@Override
	public Plant updatePlant(Plant plant) {
		Optional<Plant> existingPlant = plantRepo.findById(plant.getPlantId());
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
           
    		plantRepo.save(p);
        }
		return p;
		
	}

	/*Method Name:deletePlant
	 *Parameters:Plant
	 *ReturnType:Plant
	 *Author Name:Ambidi Swathi
	 *Created Date: 25/05/2021 */
	
	@Override
	public Plant deletePlant(Plant plant) {
		boolean isDeleted = false;
		int plantId = plant.getPlantId();
		Optional<Plant> p= plantRepo.findById(plantId);
		if(p.isPresent()) {
			plantRepo.delete(plant);	
		}
		return plant;
	}

	/*Method Name:viewPlant
	 *Parameters:PlantId
	 *ReturnType:Plant
	 *Author Name:Ambidi Swathi
	 *Created Date: 23/05/2021 */
	
	@Override
	public Plant viewPlant(int plantId) {

		
		return plantRepo.findById(plantId).get();
	}

	/*Method Name:viewPlant
	 *Parameters:commonName
	 *ReturnType:Optional<Plant>
	 *Author Name:Ambidi Swathi
	 *Created Date: 24/05/2021 */
	
	@Override
	public Optional<Plant> viewPlant(String commonName) throws ResourceNotFoundException {
		Optional<Plant> p = plantRepo.viewPlant(commonName);
		
		if(p.isEmpty()) throw new ResourceNotFoundException(commonName);

		
		return p;
	}

	/*Method Name:viewAllPlants
	 *Parameters: No Parameters
	 *ReturnType:List<Plant>
	 *Author Name:Ambidi Swathi
	 *Created Date: 24/05/2021 */
	
	@Override
	public List<Plant> viewAllPlants() {

		return plantRepo.findAll();
	}

	/*Method Name:viewAllPlants
	 *Parameters:typeOfPlant
	 *ReturnType:Optional<List<Plant>>
	 *Author Name:Ambidi Swathi
	 *Created Date: 24/05/2021 */
	
	@Override
	public Optional<List<Plant>> viewAllPlants(String typeOfPlant) throws ResourceNotFoundException {
		Optional<List<Plant>> pList = plantRepo.viewAllPlants(typeOfPlant);
		
		if(pList==null || pList.isEmpty()) {
			throw new ResourceNotFoundException(typeOfPlant);
		}	
		return plantRepo.viewAllPlants(typeOfPlant);
	}



}
