/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.walmart.ticketservice.presentation;

import com.walmart.ticketservice.data.DataAccess;
import com.walmart.ticketservice.data.SeatHold;
import com.walmart.ticketservice.TicketService;
import com.walmart.ticketservice.TicketServiceImpl;
import com.walmart.ticketservice.utils.Constants;
import com.walmart.ticketservice.utils.SelectTickets;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 *
 * @author John Ma
 * This is the main method. Create a user interface based on command line. In this method, You can use the
 * method of numSeatsAvailable, findAndHoldSeats and reserveSeats.
 */
public class Main {

    public static void main(String[] args) throws Exception{
        TicketService ticketService = new TicketServiceImpl();
        while(true){
            System.out.println("***************************************************************************");
            System.out.println("* 1-> Run the method of numSeatsAvailable");
            System.out.println("* 2-> Run the method of findAndHoldSeats");
            System.out.println("* 3-> Run the method of reserveSeats");
            System.out.println("* exit -> exit this program");
            System.out.println("***************************************************************************");
            System.out.println("* Please enter 1 or 2 or 3 or exit");

            Scanner sc = new Scanner( System.in );
            String str =sc.next();

            // Run the method of numSeatsAvailable to find the number of remain available seats.
            if(str.equals("1")){
                int remainTicket = ticketService.numSeatsAvailable();
                System.out.println("Ticket remains:"+remainTicket);
            }

            // Exit this program
            else if(str.equals("exit")){
                System.out.println("Program is exit now.");
                break;
            }
            //Run the method of findAndHoldSeats to hold number of seats and return a seatHoldId.
            else if(str.equals("2")){
                int inputCount = 3;
                while (inputCount > 0) {
                    try {
                        System.out.println("please enter seatNumber:");
                        sc = new Scanner( System.in );
                        String seatNumber =sc.next();
                        System.out.println("please enter email:");
                        sc = new Scanner( System.in );
                        String email =sc.next();
                        SeatHold seatHold=ticketService.findAndHoldSeats(Integer.parseInt(seatNumber), email);
                        System.out.println("Your seatHoldId is: "+seatHold.getHoldSeatId()+", "
                                //+ticketService.numSeatsAvailable());
                                + seatHold.getSeatsInfo());
                        System.out.println("You should reserve it in " +(Constants.EXPIRE_TIME / 60) + " minutes, or it will be cancel");
                        inputCount = 0;

                    } catch (Exception e) {
                        System.out.println("Exception. Input error.");
                        inputCount--;
                    }

                }

            }
            // Use the seatsHoldId and email to reserve the tickets.
            else if(str.equals("3")) {
                int inputCount = 3;
                while (inputCount > 0) {
                    try {
                        int intSeStHoldId;
                        System.out.println("Please enter your seatHoldId");
                        sc = new Scanner( System.in );
                        String seatHoldId =sc.next();
                        intSeStHoldId = Integer.parseInt(seatHoldId);
                        SeatHold seatHold;
                        seatHold = DataAccess.getInstance().getSeatHold(intSeStHoldId);

                        System.out.println("Please enter your email");
                        sc = new Scanner( System.in );
                        String email =sc.next();
                        String reverseCode = ticketService.reserveSeats(Integer.parseInt(seatHoldId),email);
                        if (reverseCode == null) {
                            inputCount--;
                        }
                        else{
                            System.out.println("Success! Your reverseCode is:"+reverseCode +", and seat remains:"
                                    +ticketService.numSeatsAvailable());
                            break;
                        }

                    } catch (Exception e) {
                        System.out.println("Exception. Input error.");
                        inputCount--;
                    }
                }
            }
            else{
                System.out.println("Type wrong");
            }
        }
    }
}
