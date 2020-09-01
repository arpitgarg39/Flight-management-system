package com.org.service;

import com.org.dto.PassengerDto;
import com.org.entity.User;

/**
 * The interface User service.
 */
public interface UserService {

  /**
   * Find user user.
   *
   * @param userId       the user id
   * @param passengerDto the passenger dto
   * @return the user
   */
  User findUser(Long userId, PassengerDto passengerDto);
}
