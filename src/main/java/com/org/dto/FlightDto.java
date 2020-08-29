package com.org.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightDto implements Serializable {

  private static final long serialVersionUID = 1L;
  private String source;
  @NotNull
  private String destination;
  @NotNull
  private String departureDate;
  private String returnDate;
  @NotNull
  private int flightWay;

}
