package com.ec.onlineplantnursery.web;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ec.onlineplantnursery.exceptions.ResourceNotFoundException;
import com.ec.onlineplantnursery.plant.entity.Plant;
import com.ec.onlineplantnursery.plant.service.IPlantService;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/api/plant")
@Api(value="My Plant Nursery")
public class PlantRestController {

	Logger log = org.slf4j.LoggerFactory.getLogger(PlantRestController.class);

	@Autowired
	private IPlantService plantService;
	
	/*Method Name:addPlant
	 *Parameters:Plant
	 *ReturnType:Plant
	 *Author Name:Ambidi Swathi
	 *Created Date: 22/05/2021 */
	
	@ApiOperation(value="Add Plant",response=Plant.class)
	@PostMapping("/addPlant")
	public Plant addPlant(@Valid @RequestBody Plant p) {
		log.info("Inside insert Plant");
		return plantService.addPlant(p);
	}
	

	/*Method Name:updatePlant
	 *Parameters:Plant
	 *ReturnType:Plant
	 *Author Name:Ambidi Swathi
	 *Created Date: 22/05/2021 */
	
	@ApiOperation(value="Updating a plant",response = Plant.class)
	@PutMapping("/updatePlant")
	public Plant updatePlant(@RequestBody Plant plant) {
		log.info("Inside Update Plant");
		return plantService.updatePlant(plant);
	}
	
	/*Method Name:deletePlant
	 *Parameters:Plant
	 *ReturnType:Plant
	 *Author Name:Ambidi Swathi
	 *Created Date: 25/05/2021 */
	
	@ApiOperation(value="Delete Plant",response=Plant.class)
	@DeleteMapping("/deletePlant/{plantId}")
	public Plant deletePlant(@RequestBody Plant p) {
		log.info("Inside Delete Plant");
		return plantService.deletePlant(p);
	}
	
	/*Method Name:viewAllPlants
	 *Parameters: No Parameters
	 *ReturnType:List<Plant>
	 *Author Name:Ambidi Swathi
	 *Created Date: 24/05/2021 */
	
	@ApiOperation(value="view All Plants",response=Plant.class)
	@GetMapping("/viewPlants")
	public List<Plant> viewAllPlants(){
		log.info("Inside view Plants");
		return plantService.viewAllPlants();
	}

	/*Method Name:viewAllPlants
	 *Parameters:typeOfPlant
	 *ReturnType:List<Plant>
	 *Author Name:Ambidi Swathi
	 *Created Date: 24/05/2021 */
	
	@ApiOperation(value="view All Plants",response=Plant.class)
	@GetMapping("/viewPlantsByType/{typeOfPlant}")
	public List<Plant> viewAllPlants(@PathVariable String typeOfPlant)throws ResourceNotFoundException{
		log.info("Inside View Plants by type of Plant");
		return plantService.viewAllPlants(typeOfPlant).get();
	}
	
	/*Method Name:viewPlant
	 *Parameters:PlantId
	 *ReturnType:Plant
	 *Author Name:Ambidi Swathi
	 *Created Date: 23/05/2021 */
	
	@ApiOperation(value="view Plant",response=Plant.class)
	@GetMapping("/viewPlant/{plantId}")
	public Plant viewPlant(@PathVariable int plantId) {
		log.info("Inside View Plant by Id");
		return plantService.viewPlant(plantId);
	}
	/*Method Name:viewPlant
	 *Parameters:commonName
	 *ReturnType:Optional<Plant>
	 *Author Name:Ambidi Swathi
	 *Created Date: 24/05/2021 */
	
	@ApiOperation(value="view Plant by Name",response=Plant.class)
	@GetMapping("/viewPlantByName/{commonName}")
	public Plant viewPlant(@PathVariable String commonName)throws ResourceNotFoundException {
		
		log.info("Inside View Plant by common Name");
		return plantService.viewPlant(commonName).get();
	}
	
	
	
	
}