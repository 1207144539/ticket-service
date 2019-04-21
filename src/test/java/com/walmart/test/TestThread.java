package com.walmart.test;

import com.walmart.ticketservice.TicketService;
import com.walmart.ticketservice.TicketServiceImpl;
import com.walmart.ticketservice.data.SeatHold;
import org.junit.Before;
import org.junit.Test;

public class TestThread {

    public static void main(String[] args){
        TicketService ticketService = new TicketServiceImpl();
        SeatHold seatHold = ticketService.findAndHoldSeats(90,"Alice@gmail.com");
        ticketService.reserveSeats(seatHold.getHoldSeatId(),"Alice@gmail.com");
        // Thread1 is used to hold 6ticket
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                System.out.println("Thread1-Ticket remains: "+ticketService.numSeatsAvailable());
                SeatHold seatHold1 = ticketService.findAndHoldSeats(6,"mk@gmail.com");
                if (seatHold1 == null) {
                    System.out.println("Thread1- Do not have enough ticket.");
                }
                else {
                    System.out.println("Thread1-After mk hold" + seatHold1.getHoldOnSeats().size() + " tickets, tickets remain:"
                            +ticketService.numSeatsAvailable());
                }

            }

        };
        Thread thread2 = new Thread(){
            @Override
            public void run() {
                System.out.println("Thread2-Ticket remains: "+ticketService.numSeatsAvailable());
                SeatHold seatHold2 = ticketService.findAndHoldSeats(6,"ry@gmail.com");
                if(seatHold2 == null) {
                    System.out.println("Thread2- Do not have enough ticket.");
                }
                else {
                    System.out.println("Thread2-After ry hold " + seatHold2.getHoldOnSeats().size() + "tickets, tickets remain: "
                            + ticketService.numSeatsAvailable());
                }

            }
        };
        thread1.start();
        thread2.start();
        try{
            thread1.join();
            thread2.join();
        } catch(Exception e){
            System.err.println(e);
        }
        System.out.println("End of Main");
    }

}
