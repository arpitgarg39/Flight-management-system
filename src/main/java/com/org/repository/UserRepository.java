package com.org.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.org.entity.User;

/**
 * The interface User repository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  /**
   * Find by user id optional.
   *
   * @param userId the user id
   * @return the optional
   */
  @Query("SELECT u FROM User u JOIN  u.userType as type where u.userId= :userId")
  Optional<User> findByUserId(Long userId);

}
