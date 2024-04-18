package com.bikeproject.bikeRental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bikeproject.bikeRental.entity.Route;

public interface RouteRepository extends JpaRepository<Route, Long> {

}
