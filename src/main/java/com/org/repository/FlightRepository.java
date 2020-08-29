package com.org.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.org.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

  @Query("Select f from Flight f where f.source=:source and f.destination=:destination and f.departureDate like :departureDate% order by f.price ASC")
  List<Flight> findBySourceAndDestinationOrderByPriceAscAndDepartureDateContaining(
      @Param("source") String source, @Param("destination") String destination,
      @Param("departureDate") String departureDate);

  Optional<Flight> findById(Long flightId);

}
