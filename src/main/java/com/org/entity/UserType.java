package com.org.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * The type User type.
 */
@Entity
@Table(name = "user_type")
@Data
public class UserType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long userTypeId;
  private String userType;
  private double discount = 0.0;

}
