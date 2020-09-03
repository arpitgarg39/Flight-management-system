package com.org.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

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
  @JsonIgnore
  @Version  //Optimistic Locking for concurrent access
  @Column(columnDefinition = "Integer DEFAULT 0",nullable = false)
  private long version=0L;

}
