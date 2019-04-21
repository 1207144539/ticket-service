/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.walmart.ticketservice.data;

import java.time.LocalDateTime;
import java.util.*;

/**
 *
 * @author John Ma
 * Create an class of SeatHold, and contains the information of Seat that
 * has been hold
 */
public class SeatHold {
    private Map<Integer,Seat> holdOnSeats = new HashMap<>();
    private int seatHoldId;
    private LocalDateTime reservedOn;
    private LocalDateTime holdOnTime;
    private String reservationCode;
    private String customerEmail;
    private boolean reserved;

    public LocalDateTime getHoldOnTime() {
        return holdOnTime;
    }

    public void setHoldOnTime(LocalDateTime holdOnTime) {
        this.holdOnTime = holdOnTime;
    }

    public SeatHold( String customerEmail,int seatHoldId) {
        this.customerEmail = customerEmail;

        this.seatHoldId =seatHoldId;
    }
    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public void setHoldSeatId(int seatHoldId) {
        this.seatHoldId = seatHoldId;
    }

    public String getReservationCode() {
        return reservationCode;
    }

    public Map<Integer, Seat> getHoldOnSeats() {
        return holdOnSeats;
    }

    public void setHoldOnSeats(Map<Integer, Seat> holdOnSeats) {
        this.holdOnSeats = holdOnSeats;
    }

    public void setReservedOn(LocalDateTime reservedOn) {
        this.reservedOn = reservedOn;
    }

    /**
     * Deal with the situation that customer wants to reserve the ticket
     */

    public void reserve() {
        this.reservedOn = LocalDateTime.now();
        this.reservationCode = "" + this.reservedOn.getYear() + this.reservedOn.getMonth() + this.reservedOn.getDayOfMonth()
                + this.reservedOn.getHour() + this.reservedOn.getMinute() + this.reservedOn.getSecond() +
                this.reservedOn.getNano();
        holdOnSeats.forEach((k,v)->{
            v.getHold().setReserved(true);
            v.getHold().setReservedOn(LocalDateTime.now());
        });
    }

    public int getHoldSeatId() {
        return seatHoldId;
    }

    public LocalDateTime getReservedOn() {
        return reservedOn;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    /**
     * Put the seats that needed to be hold into the map of HoldOnSeats
     * @param seats all the seats needed to be hold
     */
    public void addSeats(Map<Integer,Seat> seats) {
        seats.forEach((k,v)->{
            holdOnSeats.put(k,v);
        });
    }

    public String getSeatsInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Seats are [");
        holdOnSeats.forEach((k,v)->{
            sb.append("<");
            sb.append(v.getRowNum());
            sb.append(", ");
            sb.append(v.getColNum());
            sb.append("> ");
        });
        sb.append("]");
        return sb.toString();
    }
}
