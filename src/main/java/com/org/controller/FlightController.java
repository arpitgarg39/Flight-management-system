package com.org.controller;

import com.org.dto.FlightDto;
import com.org.dto.PassengerDto;
import com.org.entity.Flight;
import com.org.entity.Transaction;
import com.org.exception.CustomException;
import com.org.exception.CustomInternalServerException;
import com.org.service.FlightBookingService;
import com.org.service.FlightService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The type Flight controller.
 */
@RestController @RequestMapping("/flight") public class FlightController {

  /**
   * The Flight booking service.
   */
  @Autowired FlightBookingService flightBookingService;

  /**
   * The Flight service.
   */
  @Autowired FlightService flightService;

  /**
   * Flight search response entity.
   *
   * @param flightDto the flight dto
   * @return the response entity
   */
  @GetMapping("/search") public ResponseEntity<List<Flight>> flightSearch(
      @ModelAttribute FlightDto flightDto) {

    validation(flightDto);
    List<Flight> searchFlightList = flightService.searchFlight(flightDto);
    return new ResponseEntity<List<Flight>>(searchFlightList, new HttpHeaders(), HttpStatus.OK);
  }

  /**
   * Flight book response entity.
   *
   * @param userId       the user id
   * @param flightId     the flight id
   * @param passengerDto the passenger dto
   * @return the response entity
   */
  @PostMapping("/{userId}/booking") public ResponseEntity<Transaction> flightBook(
      @PathVariable("userId") Long userId,
      @RequestParam(required = true, name = "flightId") Long flightId,
      @RequestBody PassengerDto passengerDto) {
    try {

      if (userId == null || flightId == null || passengerDto == null)
        throw new CustomException("Value can not be null");

      Transaction bookingRespDto = flightBookingService
          .flightBooking(userId, flightId, passengerDto);
      return new ResponseEntity<Transaction>(bookingRespDto, new HttpHeaders(), HttpStatus.OK);
    } catch (Exception e) {
      throw new CustomInternalServerException("Flight Booking failed, please try again");
    }

  }

  private void validation(FlightDto flightDto) {

    if (FlightBookingService.isNull(flightDto.getSource()))
      throw new CustomException("source param is mandatory");

    if (FlightBookingService.isNull(flightDto.getDestination()))
      throw new CustomException("destination param is mandatory");

    if (FlightBookingService.isNull(flightDto.getDepartureDate()))
      throw new CustomException("departure date is mandatory");

    if (flightDto.getFlightWay() == 2 && FlightBookingService.isNull(flightDto.getReturnDate()))
      throw new CustomException("return date is mandatory");
  }

}
