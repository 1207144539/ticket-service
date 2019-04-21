package com.walmart.test;

import com.walmart.ticketservice.data.SeatHold;
import com.walmart.ticketservice.TicketService;
import com.walmart.ticketservice.TicketServiceImpl;
import com.walmart.ticketservice.utils.Constants;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindAndHoldSeatsTest {
    TicketService ticketService;

    @Before
    public void setUp() {
        ticketService = new TicketServiceImpl();
    }

    @Test
    public void testfindAndHoldSeats(){

        int remainTicket = ticketService.numSeatsAvailable();
        SeatHold seatHold1=ticketService.findAndHoldSeats(5, "Bob@gmail.com");
        SeatHold seatHold2=ticketService.findAndHoldSeats(7, "Alice@gmail.com");
        SeatHold seatHold3=ticketService.findAndHoldSeats(2, "Peter@gmail.com");
        System.out.println("Run now. The remain Seats is:"+ticketService.numSeatsAvailable());
        assertEquals(remainTicket-14,ticketService.numSeatsAvailable());

        try
        {

            Thread.currentThread().sleep((Constants.EXPIRE_TIME+5)*1000);
        }
        catch(Exception e){}
        assertEquals(remainTicket,ticketService.numSeatsAvailable());
        System.out.println("Run after "+(Constants.EXPIRE_TIME+5)+"second. The remain Seats is:"+ticketService.numSeatsAvailable());
    }
}
