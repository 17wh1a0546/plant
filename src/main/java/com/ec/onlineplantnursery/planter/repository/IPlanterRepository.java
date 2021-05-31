package com.ec.onlineplantnursery.planter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ec.onlineplantnursery.planter.entity.Planter;

public interface IPlanterRepository extends JpaRepository<Planter, Integer>, CustomPlanterRepository{
}
