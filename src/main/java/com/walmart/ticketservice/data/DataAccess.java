/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.walmart.ticketservice.data;

import com.walmart.ticketservice.utils.Constants;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author John Ma
 * The class DataAccess is used to storage all the Seats information.
 * In this class, seats, avialableSeat, seatHolds uses concurrentHashMap to make thread-safe
 */
public class DataAccess {
    // The instance of this class
    private static DataAccess instance;
    //  Store all the seats
    private static Map<Integer, Seat> seats = new ConcurrentHashMap<>();
    //  Store all the available seats
    private static Map<Integer, Seat> availableSeat = new ConcurrentHashMap<>();
    //  Store all the seatHolds
    private static Map<Integer, SeatHold> seatHolds = new ConcurrentHashMap<>();

    private DataAccess() {
    }

    /**
     *
     * Get the instance of this class
     * @return return a singleton Instance of DataAccess to make the DataAccess thread-safe
     *
     */
    public synchronized static DataAccess getInstance() {
        if (instance == null) {
            instance = new DataAccess();
            initData();
        }
        return instance;
    }

    /**
     *  Initial all the seat by seatId , then put all the seat with seatId into seats.
     */
    private static void initData() {
        int seatId = 0;
        //add all seat info into a map
        for (int i = 0; i < Constants.ROW_NUM; i++){
            for (int j = 0; j< Constants.COL_NUM; j++){
                Seat seat = new Seat(seatId++,i+1,j+1);
                seats.put(seatId,seat);
                //availableSeat.put(seatId,seat);
            }
        }
    }

    /**
     * Get all the available seats from map of seats, and then put all the available seat
     * into availableSeat
     * @return map of availableSeat
     */
    public Map<Integer, Seat> getAllAvailableSeat() {
        availableSeat.clear();
        seats.forEach((k,v)->{
            if (v.isAvailable()){
                availableSeat.put(k,v);
            }
        });
        return this.availableSeat;
    }

    /**
     * Put the seat available and update it in the map of availableSeat
     * @param seat needed to be changed of the status
     */
    public void setAvailableSeat(Seat seat){
        availableSeat.put(seat.getSeatId(),seat);
    }

    public Map<Integer, SeatHold> getAllSeatHolds() {
        return seatHolds;
    }

    /**
     *
     * @param setHoldId the id of the setHold
     * @return get the instance of the setHold with setHoldId
     */
    public SeatHold getSeatHold(int setHoldId) {
        return  getAllSeatHolds().get(setHoldId);
    }

    public Map<Integer, Seat> getAllSeats() {
        return seats;
    }

    /**
     * create a seatHold by seatHoldId and put it into the map of seatHold
     * @param seatHold the seatHold wanted to be added
     */
    public void addSeatHold (SeatHold seatHold) {
        seatHolds.put(seatHold.getHoldSeatId(),seatHold);
    }
}
