package com.org.service;

import com.org.dto.FlightDto;
import com.org.dto.PassengerDto;
import com.org.entity.Flight;
import com.org.exception.CustomException;
import com.org.repository.FlightRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Flight service.
 */
@Service
public class FlightServiceImpl implements  FlightService {

  /**
   * The Flight repository.
   */
  @Autowired FlightRepository flightRepository;


  public List<Flight> searchFlight(FlightDto flightDto) {

    List<Flight> flights = flightRepository
        .findBySourceAndDestinationOrderByPriceAscAndDepartureDateContaining(flightDto.getSource(),
            flightDto.getDestination(), flightDto.getDepartureDate());

    if (flightDto.getFlightWay() == 2) {
      List<Flight> twoWayFlightList = flightRepository
          .findBySourceAndDestinationOrderByPriceAscAndDepartureDateContaining(
              flightDto.getDestination(), flightDto.getSource(), flightDto.getReturnDate());
      flights.addAll(twoWayFlightList);

    }
    boolean isTrue = flights == null ? true : flights.size() == 0 ? true : false;
    if (isTrue)
      throw new CustomException("Flight not found!");
    return flights;

  }


  @Override public Flight findFlightById(Long flightId, PassengerDto passengerDto) {
    Optional<Flight> flight = flightRepository.findById(flightId);

    if (!flight.isPresent())
      throw new CustomException("Flight Not found");

    int totalTraveller = passengerDto.getTravellerCount();

    if (flight.get().getCapacity() < totalTraveller) {
      throw new CustomException("No of seat is availalbe: " + flight.get().getCapacity());
    }

    return updateFlightCapacity(flight.get(), totalTraveller);
  }


  private Flight updateFlightCapacity(Flight flight, int totalTraveller) {

    flight.setCapacity(flight.getCapacity() - totalTraveller);

    return flight;
  }



}
