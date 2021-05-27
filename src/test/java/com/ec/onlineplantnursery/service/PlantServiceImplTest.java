package com.ec.onlineplantnursery.service;

import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import com.ec.onlineplantnursery.exceptions.ResourceNotFoundException;
import com.ec.onlineplantnursery.plant.entity.Plant;
import com.ec.onlineplantnursery.plant.repository.PlantRepository;
import com.ec.onlineplantnursery.plant.service.IPlantServiceImpl;

import java.util.Optional;

@SpringBootTest
public class PlantServiceImplTest {

	//@Mock
	PlantRepository repo;
	private static IPlantServiceImpl service;//
	private static AutoCloseable ac;
	
	
	//private TestEntityManager em;
	
	@BeforeEach
	public void doinit() {
		repo = mock(PlantRepository.class);
		service = new IPlantServiceImpl(repo);
		ac = MockitoAnnotations.openMocks(this);
	}
	
	@AfterEach
	public void doAtEnd() throws Exception{
		ac.close();
	}
	
	@Test
	//@Disabled
	void testSavePlant() {
		Plant input = new Plant(1,10,"6 inches","Ice Plant","Throughout year","culinary","Easy to grow","20-35 C","Succulent","This is a delightful,easy to grow plant",10,150.0);
		
		Plant savedProduct = new Plant(1,10,"6 inches","Ice Plant","Throughout year","culinary","Easy to grow","20-35 C","Succulent","This is a delightful,easy to grow plant",10,150.0);
		
	
		when(repo.save(input)).thenReturn(savedProduct);
		service.addPlant(input);
		verify(repo).save(input);
		
	}
	
	@Test
	@DisplayName("test- get all Plants")
	//@Disabled
	void testGetAllPlants() {
		
		List<Plant> pList = mock(List.class);
		when(repo.findAll()).thenReturn(pList);
		service.viewAllPlants();
		verify(repo).findAll();
		
	}
	
	@Test
	//@Disabled
	void deletePlant() {
		Plant input = new Plant(1,10,"6 inches","Ice Plant","Throughout year","culinary","Easy to grow","20-35 C","Succulent","This is a delightful,easy to grow plant",10,150.0);
		repo.save(input);
		service.deletePlant(input);
		
		verify(repo).delete(input);
	}
	
	@Test
	//@Disabled
	void testViewPlantByName() {
		
		String commonName = "abc";
		Optional<Plant> s = Optional.empty();
		when(repo.viewPlant(commonName)).thenReturn(s);
		Executable executable = ()->service.viewPlant(commonName);
		assertThrows(ResourceNotFoundException.class, executable);
		

	}
	@Test
	//@Disabled
	void testViewPlantByTypeOfPlant() {
		String typeOfPlant = "Air Plant";
		Optional<List<Plant>> pList = Optional.empty();
		when(repo.viewAllPlants(typeOfPlant)).thenReturn(pList);
		Executable executable = ()->service.viewAllPlants(typeOfPlant);
		assertThrows(ResourceNotFoundException.class, executable);
		
	}
	
	
	@Test
    void viewPlantById() {
		Optional<Plant> p = Optional.empty();
		when(repo.findById(1)).thenReturn(p);
		//Executable exec = ()->service.viewPlant(1);
		service.viewPlant(1);
		/*try{
			Plant output = service.viewPlant(1);
			verify(repo).findById(1);
			//assertEquals(expected,output);
		}
		catch(NoSuchElementException e) {
			System.out.println(e.getMessage());
		}*/
		verify(repo).findById(1);
		
	}
	
	
}//end




