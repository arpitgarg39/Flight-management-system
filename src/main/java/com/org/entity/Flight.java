package com.org.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Data;

/**
 * The type Flight.
 */
@Entity
@Data
@Table(name = "FLIGHT")
public class Flight {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @NotNull
  private Long id;
  private String source;
  private String destination;
  // @Temporal(TemporalType.TIMESTAMP)
  private String departureDate;
  // @Temporal(TemporalType.TIMESTAMP)
  private String arrivalDate;
  private String flightName;
  private String flightNo;

  private double price = 0.0;
  private int capacity;

}
