package com.org.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.dto.FlightDto;
import com.org.dto.PassengerDto;
import com.org.entity.Flight;
import com.org.entity.Transaction;
import com.org.exception.CustomException;
import com.org.service.FlightService;

@RestController
@RequestMapping("/flight")
public class FlightController {

  @Autowired
  FlightService flightService;

  @GetMapping("/search")
  public ResponseEntity<List<Flight>> flightSearch(@ModelAttribute FlightDto flightDto) {

    validation(flightDto);
    List<Flight> searchFlightList = flightService.searchFlight(flightDto);
    return new ResponseEntity<List<Flight>>(searchFlightList, new HttpHeaders(), HttpStatus.OK);
  }

  @PostMapping("/{userId}/booking")
  public ResponseEntity<Transaction> flightBook(@PathVariable("userId") Long userId,
      @RequestParam(required = true, name = "flightId") Long flightId,
      @RequestBody PassengerDto passengerDto) {

    if (userId == null || flightId == null || passengerDto == null)
      throw new CustomException("Value can not be null");

    Transaction bookingRespDto = flightService.flightBooking(userId, flightId, passengerDto);

    return new ResponseEntity<Transaction>(bookingRespDto, new HttpHeaders(), HttpStatus.OK);
  }

  private void validation(FlightDto flightDto) {
    if (flightDto.getSource() == null || flightDto.getSource().isEmpty()) {
      throw new CustomException("source param is mandatory");
    }
    if (flightDto.getDestination() == null || flightDto.getDestination().isEmpty()) {
      throw new CustomException("destination param is mandatory");
    }
    if (flightDto.getDepartureDate() == null || flightDto.getDepartureDate().isEmpty()) {
      throw new CustomException("departure date is mandatory");
    }
    if (flightDto.getFlightWay() == 2
        && (flightDto.getSource() == null || flightDto.getSource().isEmpty())) {
      throw new CustomException("return date is mandatory");
    }
  }

}
