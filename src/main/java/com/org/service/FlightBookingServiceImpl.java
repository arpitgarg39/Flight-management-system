package com.org.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.dto.FlightDto;
import com.org.dto.PassengerDto;
import com.org.entity.Flight;
import com.org.entity.Transaction;
import com.org.entity.User;
import com.org.exception.CustomException;
import com.org.repository.FlightBookingRepository;
import com.org.repository.FlightRepository;

// TODO: Auto-generated Javadoc

/**
 * The type Flight search booking service.
 */
@Service
public class FlightBookingServiceImpl implements FlightBookingService {

  /**
   * The User service.
   */
  @Autowired
UserService userService;

  /**
   * The Flight service.
   */
  @Autowired
FlightService flightService;

  /**
   * The Flight booking repository.
   */
  @Autowired
  FlightBookingRepository flightBookingRepository;



  @Override
  @Transactional
  public Transaction flightBooking(Long userId, Long flightId, PassengerDto passengerDto) {

    User updateUser = userService.findUser(userId,passengerDto);
    Flight flight=flightService.findFlightById(flightId,passengerDto);
    Transaction flightBooking = preparePayloadForBooking(flight, passengerDto, updateUser);

    Transaction flightBookingResponse = flightBookingRepository.save(flightBooking);

    if (flightBookingResponse == null)
      throw new CustomException("Internal serer error");
    return flightBookingResponse;
  }


  private Transaction preparePayloadForBooking(Flight flight, PassengerDto passengerDto, User updateUser) {
    int totalTraveller = passengerDto.getTravellerCount();
    Transaction flightBooking = new Transaction();
    flightBooking.setNoOfPassenger(totalTraveller);
    flightBooking.setUser(updateUser);
    flightBooking.setFlight(flight);

    double totalFlightPrice = flight.getPrice();
    double totalPrice = totalFlightPrice * totalTraveller;
    double discount = totalPrice * updateUser.getUserType().getDiscount() / 100;
    double finalAmount = totalPrice - discount;

    flightBooking.setDiscount(discount);
    flightBooking.setTotalAmount(totalPrice);
    flightBooking.setFinalAmount(finalAmount);
    return flightBooking;
  }


}
