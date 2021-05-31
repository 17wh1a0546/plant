package com.ec.onlineplantnursery.web;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.ec.onlineplantnursery.exceptions.SeedIdNotFoundException;
import com.ec.onlineplantnursery.seed.entity.Seed;
import com.ec.onlineplantnursery.seed.service.ISeedServiceImpl;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/api/seed")
@Api(value = "Online Nursery Application")
public class SeedRestController {

	Logger log = org.slf4j.LoggerFactory.getLogger(SeedRestController.class);
	@Autowired
	ISeedServiceImpl seedService;

	public SeedRestController() {
		log.info("OnlineNurseryRestController -- constructor ");
		System.out.println("---->> Online Nursery Rest constructor");
	}
	
	/*Method Name:insertSeed
	 *Parameters:Seed
	 *ReturnType:Seed
	 *Author Name:Nagolu Tejashwini
	 *Created Date: 21/05/2021 */
	@ApiOperation(value = "seed post mapping" , response = Seed.class)
	@PostMapping("/insertseed")
	public Seed insertSeed(@RequestBody @Valid Seed s) {
		log.info("inside insert seeds");
		seedService.addSeed(s);
		return s;
	}
	
	/*Method Name:getAllSeeds
	 *Parameters:No parameter
	 *ReturnType:List<Seed>
	 *Author Name:Nagolu Tejashwini
	 *Created Date: 21/05/2021 */
	@ApiOperation(value = "Seed Get mapping to fetch all seeds" , response = List.class)
	@GetMapping("/getallseeds")
	public List<Seed> getAllSeeds(){
		log.info("inside Get all seeds information");
		return seedService.viewAllSeeds();
	}
	

/*Method Name:getSeedById
	 *Parameters:seedId
	 *ReturnType:Seed
	 *Author Name:Nagolu Tejashwini
	 *Created Date: 21/05/2021 */

	
	@ApiOperation(value = "Seed Get mapping to fetch seed by id" , response = Seed.class)
	@GetMapping("/seedId/{id}")
	public Seed getSeedById(@PathVariable int id) throws SeedIdNotFoundException 
	{
		log.info("inside Get seed information by id");
		return seedService.viewSeed(id);
		
	}
	

/*Method Name:deleteSeed
	 *Parameters:Seed
	 *ReturnType:Seed
	 *Author Name:Nagolu Tejashwini
	 *Created Date: 24/05/2021 */
	
	@ApiOperation(value = "Seed delete mapping to delete seed" , response = Seed.class)
	@DeleteMapping("/delete")
	public Seed deleteSeed(@RequestBody Seed seed) throws SeedIdNotFoundException 
	{
		log.info("inside delete seed");
		return seedService.deleteSeed(seed);
		
	}
	
	/*Method Name:updateSeed
	 *Parameters:Seed
	 *ReturnType:Seed
	 *Author Name:Nagolu Tejashwini
	 *Created Date: 24/05/2021 */
	
	@ApiOperation(value = "Seed Put mapping to update seed" , response = Seed.class)
	@PutMapping("/update")
	public Seed updateSeed(@RequestBody Seed seed) throws SeedIdNotFoundException
	{
		log.info("inside update seed");
		return seedService.updateSeed(seed);
		
	}
	
	/*Method Name:getSeedByCommonName
	 *Parameters:commonName
	 *ReturnType:Seed
	 *Author Name:Nagolu Tejashwini
	 *Created Date: 24/05/2021 */

	
	@ApiOperation(value = "Seed Get mapping to fetch details of seed by commonName" , response = Seed.class)
	@GetMapping("/commonname/{commonName}")
	public Seed getSeedByCommonName(@PathVariable String commonName) throws ResourceNotFoundException 
	{
		log.info("inside get seed by common name");
		return seedService.viewSeed(commonName).get();
	}
	

/*Method Name:getSeedsByTypeOfSeed
	 *Parameters:typrOfSeed
	 *ReturnType:List<Seed>
	 *Author Name:Nagolu Tejashwini
	 *Created Date: 24/05/2021 */
	
	@ApiOperation(value = "Seed Get mapping to fetch all seeds by type of seed" , response = Seed.class)
	@GetMapping("/typeofseed/{typeOfSeed}")
	public List<Seed> getSeedsByTypeOfSeed(@PathVariable String typeOfSeed) throws ResourceNotFoundException 
	{
		log.info("inside get seed by type of seed");
		return seedService.viewAllSeeds(typeOfSeed).get();
	}
	
}
