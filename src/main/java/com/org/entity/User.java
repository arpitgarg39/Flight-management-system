package com.org.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * The type User.
 */
@Entity
@Table(name = "users")
@Data
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;
  private String firstName;
  private String lastName;
  private String mobileNo;
  private String emailId;
  @OneToOne
  @JoinColumn(name = "userType_id")
  private UserType userType;

}
