package com.org.service;

import com.org.dto.PassengerDto;
import com.org.entity.User;
import com.org.exception.CustomException;
import com.org.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type User service.
 */
@Service
public class UserServiceImpl implements UserService {

  /**
   * The User repository.
   */
  @Autowired UserRepository userRepository;

  @Override public User findUser(Long userId, PassengerDto passengerDto) {

    Optional<User> fetchUser = userRepository.findByUserId(userId);

    if (!fetchUser.isPresent())
      throw new CustomException("User Not found");

   return updateUserDetails(passengerDto, fetchUser.get());

  }



  private User updateUserDetails(PassengerDto passengerDto, User user) {
    user.setFirstName(passengerDto.getFirstName());
    user.setLastName(passengerDto.getLastName());
    user.setEmailId(passengerDto.getEmailId());
    user.setMobileNo(passengerDto.getMobileNo());

    return user;
  }
}
