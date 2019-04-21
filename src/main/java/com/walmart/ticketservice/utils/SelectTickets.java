package com.walmart.ticketservice.utils;

import com.walmart.ticketservice.data.DataAccess;
import com.walmart.ticketservice.data.Hold;
import com.walmart.ticketservice.data.Seat;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author John Ma
 *
 */
public class SelectTickets {
    /**
     *
     * @param seatNumber the number of seat that the customer wants to hold
     * @return the result of the select ticket stored in a map
     * First find the empty tickets in the mid row and mid column, then expand to the front and back seats.
     *
     */
    public static Map<Integer, Seat> selectTicket(int seatNumber) {
        // Map of all the seats
        Map<Integer, Seat> allSeats;
        // Map of all the selected Seats
        Map<Integer, Seat> selectedSeats = new ConcurrentHashMap<>();
        int initRow, initCol;
        int currRow, currCol;
        int increment = -1;
        int nextRowIncrease = 0;
        int seatNum;

        initRow = (Constants.ROW_NUM + 1) / 2;
        initCol = (Constants.COL_NUM + 1) / 2;
        currRow = initRow;
        currCol = initCol;
        allSeats = DataAccess.getInstance().getAllSeats();
        while (true) {
            // Use the current row and current column to get the seatNum
            seatNum = (currRow - 1) * Constants.COL_NUM + currCol;
            Seat seat = allSeats.get(seatNum);
            //System.out.println("Row:" + currRow + "; Col:" + currCol + "; sertNum:" + seatNum);
            // If the seat is available. select the seat, set the holdOnTime, and put it into the map of selectedSeats
            if (seat.isAvailable()) {
                Hold hold = new Hold();
                hold.setHoldOnTime(LocalDateTime.now());
                seat.setHold(hold);
                selectedSeats.put(seatNum, seat);
            }
            if (selectedSeats.size() == seatNumber) {
                break;
            }
            // Get the next number of col and row

            currCol = currCol + increment;
            if (currCol < 1) {
                increment = increment * (-1);
                currCol = initCol + increment;
            } else if (currCol > Constants.COL_NUM) {
                increment = increment * (-1);
                currCol = initCol;
                if (nextRowIncrease <= 0) {
                    nextRowIncrease = nextRowIncrease * (-1);
                    nextRowIncrease++;
                } else {
                    nextRowIncrease = nextRowIncrease * (-1);
                }
                currRow = initRow + nextRowIncrease;
            }

            if (currRow < 1 || currRow > Constants.ROW_NUM) {
                break;
            }
        }
        return selectedSeats;

    }
}
