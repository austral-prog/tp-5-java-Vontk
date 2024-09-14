package com.cinema;

public class Seat {

    private final int row;
    private final int seatNumber;
    private boolean available = true;
    
    public Seat(int row, int seatNumber) {
        this.row = row;
        this.seatNumber = seatNumber;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getSeatNumber() {
        return seatNumber;
    }
    
    public boolean isAvailable() {
        return available;
    }
    
    public void takeSeat() {
        available = false;
    }
    
    public void releaseSeat() {
        available = true;
    }
}
