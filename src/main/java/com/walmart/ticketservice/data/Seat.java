/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.walmart.ticketservice.data;

import com.walmart.ticketservice.utils.Constants;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author John Ma
 * Contains all the information of the seat
 */
public class Seat {

    public Seat(int seatId, int rowNum, int colNum) {
        this.seatId = seatId;
        this.rowNum = rowNum;
        this.colNum = colNum;
    }
    // The id of the seat
    private int seatId;
    // The row number of the seat
    private int rowNum;
    // The column number of the seat
    private int colNum;

    // The hold status of seat
    private Hold hold;

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getColNum() {
        return colNum;
    }

    public void setColNum(int colNum) {
        this.colNum = colNum;
    }

    public Hold getHold() {
        return hold;
    }

    public void setHold(Hold hold) {
        this.hold = hold;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    /**
     * Find out if the seat is available,not hold nor reserved
     * @return if the seat is available
     */
    public boolean isAvailable() {
        if(this.getHold() == null){
            return true;
        }
        if(this.getHold().isReserved()){
            return  false;
        }
        java.time.Duration duration;
        duration = java.time.Duration.between(this.getHold().getHoldOnTime(), LocalDateTime.now());
        return  duration.getSeconds() > Constants.EXPIRE_TIME ;
    }
}
