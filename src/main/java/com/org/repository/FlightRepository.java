package com.org.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.org.entity.Flight;

/**
 * The interface Flight repository.
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

  /**
   * Find by source and destination order by price asc and departure date containing list.
   *
   * @param source        the source
   * @param destination   the destination
   * @param departureDate the departure date
   * @return the list
   */
  @Query("Select f from Flight f where f.source=:source and f.destination=:destination and f.departureDate like :departureDate% order by f.price ASC")
  List<Flight> findBySourceAndDestinationOrderByPriceAscAndDepartureDateContaining(
      @Param("source") String source, @Param("destination") String destination,
      @Param("departureDate") String departureDate);

  /**
   * Find by id optional.
   *
   * @param flightId the flight id
   * @return the optional
   */
  Optional<Flight> findById(Long flightId);

}
