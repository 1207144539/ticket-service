/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.walmart.ticketservice;

import com.walmart.ticketservice.data.DataAccess;
import com.walmart.ticketservice.data.Seat;
import com.walmart.ticketservice.data.SeatHold;
import com.walmart.ticketservice.utils.CheckEmail;
import com.walmart.ticketservice.utils.Constants;
import com.walmart.ticketservice.utils.SelectTickets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

/**
 *
 * @author John Ma
 * Implement the interface of TicketService
 */
public class TicketServiceImpl implements TicketService {
    /**
     * The number of seats in the requested level that are neither held nor reserved
     *
     * @return the number of tickets available on the provided level
     */
    @Override
    public int numSeatsAvailable() {
        return DataAccess.getInstance().getAllAvailableSeat().size();
    }

    /**
     * Find and hold the best available seats for a customer
     *
     * @param numSeats the number of seats to find and hold
     * @param customerEmail unique identifier for the customer
     * @return a Seat object identifying the specific seats and related information
     */
    @Override
    public synchronized SeatHold findAndHoldSeats(int numSeats, String customerEmail) {
        if (numSeats<1) {
            System.out.println("Please enter the positive number of numSeats");
            return null;
        }
        if (numSeats > numSeatsAvailable()) {
            System.out.println("No enough tickets");
            return null;
        }
        Map<Integer, Seat> seats = SelectTickets.selectTicket(numSeats);
        if (CheckEmail.isEmail(customerEmail)) {
            int generateSeatHoldId = (int)(Math.random() * (10000));
            while (DataAccess.getInstance().getAllSeatHolds().containsKey(generateSeatHoldId)) {
                generateSeatHoldId = (int)(Math.random() * (10000));
            }
            SeatHold seatHold = new SeatHold(customerEmail, generateSeatHoldId);
            seatHold.addSeats(seats);
            seatHold.setHoldOnTime(LocalDateTime.now());
            DataAccess.getInstance().addSeatHold(seatHold);
            return seatHold;
        }
        else {
            System.out.println("Please enter the right format of email: like example@gmail.com");
            return null;
        }
    }

    /**
     * Commit seats held for a specific customer
     *
     * @param seatHoldId the seat hold identifier
     * @param customerEmail the email address of the customer to which the seat
     * hold is assigned
     * @return a reservation confirmation code
     */
    @Override
    public String reserveSeats(int seatHoldId, String customerEmail) {

        SeatHold seatHold = DataAccess.getInstance().getSeatHold(seatHoldId);
        if (seatHold == null) {
            System.out.println("SeatHoldId is not Exist. Please enter again.");
            return null;
        }
        else {
            Duration duration;
            duration = java.time.Duration.between(seatHold.getHoldOnTime(), LocalDateTime.now());
            if (duration.getSeconds() > Constants.EXPIRE_TIME) {
                System.out.println("SeatHoldId expires now");
                DataAccess.getInstance().getAllSeatHolds().remove(seatHoldId);
                return null;
            }
        }
        if (!seatHold.getCustomerEmail().equals(customerEmail)) {

            System.out.println("Wrong email. Please enter the correct Email");
            return null;
        }
        seatHold.reserve();
        DataAccess.getInstance().getAllSeatHolds().remove(seatHoldId);
        return seatHold.getReservationCode();
    }
}
