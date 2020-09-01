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
 * The type Transaction.
 */
@Entity
@Data
@Table(name = "transaction")
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long passengerId;

  private int noOfPassenger;
  private double TotalAmount;
  private double discount;
  private double finalAmount;

  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;
  @OneToOne
  @JoinColumn(name = "id")
  private Flight flight;

}
