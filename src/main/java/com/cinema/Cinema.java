package com.cinema;
public class Cinema {
    private final Seat[][] seats;
    public Cinema(int[] rows) {
        seats = new Seat[rows.length][];
        initSeats(rows);
    }
    private void initSeats(int[] rows) {
        for (int i = 0; i < rows.length; i++) {
            seats[i] = new Seat[rows[i]];
        }
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                seats[i][j] = new Seat(i, j);
            }
        }
    }
    public int countAvailableSeats() {
        int count = 0;
        // recorre seats, un array de array de Seat, osea que cada elemento que recorra va a ser un array de Seat...
        // a este array de Seats lo llama rowl, luego recorre esta variable row
        for (Seat[] row : seats) {
            for (Seat seat : row) {
                if (seat.isAvailable()) {
                    count++;
                }
            }
        }
        return count;
    }
    public Seat findFirstAvailableSeatInRow(int row) {
        if (row <= seats.length && row >= 0){
            for (Seat seat : seats[row]) {
                if (seat.isAvailable()) {
                    return seat;
                }
            }
        }
        return null;
    }
    public Seat findFirstAvailableSeat() {
        for (Seat[] row : seats) {
            for (Seat seat : row) {
                if (seat.isAvailable()) {
                    return seat;
                }
            }
        }
        return null;
    }
    public Seat getAvailableSeatsInRow(int row, int amount) {
        int consecutiveFreeSeats = 0;
        Seat firstFreeSeat = null;

        if (row <= seats.length && row >= 0 && amount <= seats[row].length){ // verifica parametros validos
            for (Seat seat : seats[row]) {
                if (seat.isAvailable()) { // si encuentra un asiento libre, verifica si es el primero para el return
                    consecutiveFreeSeats++;
                    if (firstFreeSeat == null) {
                        firstFreeSeat = seat;
                    }
                    if (consecutiveFreeSeats == amount) {
                        return firstFreeSeat;
                    }
                } else { // si no encuentra todos los asientos libres necesarios, reinicia las variables
                    consecutiveFreeSeats = 0;
                    firstFreeSeat = null;
                }
            }
        }
        return null; // no se encontró la secuencia
    }
    public Seat getAvailableSeats(int amount) {
        for (int i = 0; i < seats.length; i++){
            if (getAvailableSeatsInRow(i, amount) != null){
                return getAvailableSeatsInRow(i, amount);
            }
        }
        return null;
    }
    public void takeSeats(Seat seat, int amount) {
        for (int i = 0; i < amount; i++){
            seats[seat.getRow()][seat.getSeatNumber()+i].takeSeat();
        }
    }
    public void releaseSeats(Seat seat, int amount) {
        for (int i = 0; i < amount; i++){
            seats[seat.getRow()][seat.getSeatNumber()+i].releaseSeat();
        }
    }
}
