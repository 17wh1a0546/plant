package com.ec.onlineplantnursery.seed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ec.onlineplantnursery.seed.entity.Seed;

public interface ISeedRepository extends JpaRepository<Seed, Integer>, CustomSeedRepository{
	
}
