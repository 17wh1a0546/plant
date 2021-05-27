package com.ec.onlineplantnursery.plant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ec.onlineplantnursery.plant.entity.Plant;

public interface PlantRepository extends JpaRepository<Plant, Integer>,CustomPlantRepository{

	
}
