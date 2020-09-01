package com.org.service;

import com.org.dto.FlightDto;
import com.org.dto.PassengerDto;
import com.org.entity.Flight;
import com.org.entity.User;
import java.util.List;

/**
 * The interface Flight service.
 */
public interface FlightService {

  /**
   * Search flight list.
   *
   * @param flightDto the flight dto
   * @return the list
   */
  List<Flight> searchFlight(FlightDto flightDto);

  /**
   * Find flight by id flight.
   *
   * @param flightId     the flight id
   * @param passengerDto the passenger dto
   * @return the flight
   */
  Flight findFlightById(Long flightId, PassengerDto passengerDto);
}
