package com.org.service;

import java.util.List;

import com.org.dto.FlightDto;
import com.org.dto.PassengerDto;
import com.org.entity.Flight;
import com.org.entity.Transaction;

/**
 * The interface Flight booking service.
 */
public interface FlightBookingService {

  /**
   * Flight booking transaction.
   *
   * @param userId       the user id
   * @param flightId     the flight id
   * @param passengerDto the passenger dto
   * @return the transaction
   */
  Transaction flightBooking(Long userId, Long flightId, PassengerDto passengerDto);

  /**
   * Is null boolean.
   *
   * @param str the str
   * @return the boolean
   */
  static boolean isNull(String str) {

    return str == null ? true : "".equals(str) ? true : false;
  }

}
