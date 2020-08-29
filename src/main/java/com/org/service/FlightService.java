package com.org.service;

import java.util.List;

import com.org.dto.FlightDto;
import com.org.dto.PassengerDto;
import com.org.entity.Flight;
import com.org.entity.Transaction;

public interface FlightService {

  List<Flight> searchFlight(FlightDto flightDto);

  Transaction flightBooking(Long userId, Long flightId, PassengerDto passengerDto);

}
