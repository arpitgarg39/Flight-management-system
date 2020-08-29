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
import com.org.repository.UserRepository;

@Service
public class FlightSearchServiceImpl implements FlightService {

  @Autowired
  FlightRepository flightRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  FlightBookingRepository flightBookingRepository;

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
    if (flights == null || flights.isEmpty())
      throw new CustomException("Flight not found!");
    return flights;

  }

  @Override
  @Transactional
  public Transaction flightBooking(Long userId, Long flightId, PassengerDto passengerDto) {

    Optional<User> fetchUser = userRepository.findByUserId(userId);

    if (!fetchUser.isPresent())
      throw new CustomException("User Not found");

    Optional<Flight> flight = flightRepository.findById(flightId);

    if (!flight.isPresent())
      throw new CustomException("Flight Not found");

    int totalTraveller = passengerDto.getTravellerCount();

    if (flight.get().getCapacity() < totalTraveller) {
      throw new CustomException("No of seat is availalbe: " + flight.get().getCapacity());
    }

    User updateUser = updateUserDetails(passengerDto, fetchUser.get());
    Transaction flightBooking = preparePayloadForBooking(flight.get(), totalTraveller,
        updateUser);

    Transaction flightBookingResponse = flightBookingRepository.save(flightBooking);

    if (flightBookingResponse == null)
      throw new CustomException("Internal serer error");
    return flightBookingResponse;
  }

  private Transaction preparePayloadForBooking(Flight flight, int totalTraveller,
      User updateUser) {
    Transaction flightBooking = new Transaction();
    flightBooking.setNoOfPassenger(totalTraveller);
    flightBooking.setUser(updateUser);
    flight.setCapacity(flight.getCapacity() - totalTraveller);
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

  private User updateUserDetails(PassengerDto passengerDto, User user) {
    user.setFirstName(passengerDto.getFirstName());
    user.setLastName(passengerDto.getLastName());
    user.setEmailId(passengerDto.getEmailId());
    user.setMobileNo(passengerDto.getMobileNo());

    return user;
  }

}
