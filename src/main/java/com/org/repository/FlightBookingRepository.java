package com.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.entity.Transaction;

@Repository
public interface FlightBookingRepository extends JpaRepository<Transaction, Integer> {

}
