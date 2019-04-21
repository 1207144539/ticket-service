package com.walmart.ticketservice.data;

import java.time.LocalDateTime;

/**
 * @author John Ma
 * Contain all the information of the hold status
 */
public class Hold {
    // The time of hold
    private LocalDateTime holdOnTime;
    // Define if the status is on hold
    private boolean onHold;
    // Define if it is been reserved
    private boolean reserved;
    // The time of reserve
    private LocalDateTime reservedOn;

    public LocalDateTime getReservedOn() {
        return reservedOn;
    }

    public void setReservedOn(LocalDateTime reservedOn) {
        this.reservedOn = reservedOn;
    }

    public LocalDateTime getHoldOnTime() {
        return holdOnTime;
    }

    public void setHoldOnTime(LocalDateTime holdOnTime) {
        this.holdOnTime = holdOnTime;
    }

    public boolean isOnHold() {
        return onHold;
    }

    public void setOnHold(boolean onHold) {
        this.onHold = onHold;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}
