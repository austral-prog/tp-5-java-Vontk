package com.cinema;


/**
 * Clase que representa una sala de cine.
 */
public class Cinema {

    private final Seat[][] seats;

    /**
     * Construye una sala de cine. Se le pasa como dato un arreglo cuyo tamaño
     * es la cantidad de filas y los enteros que tiene son el número de butacas en cada fila.
     */
    public Cinema(int[] rows) {
        seats = new Seat[rows.length][];
        initSeats(rows);
    }

    /**
     * Inicializa las butacas de la sala de cine.
     *
     * @param rows arreglo que contiene la cantidad de butacas en cada fila
     */
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

    /**
     * Cuenta la cantidad de seats disponibles en el cine.
     */
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

    /**
     * Busca la primera butaca libre dentro de una fila o null si no encuentra.
     */
    public Seat findFirstAvailableSeatInRow(int row) {
        for (Seat seat : seats[row]) {
            if (seat.isAvailable()) {
                return seat;
            }
        }
        return null;
    }

    /**
     * Busca la primera butaca libre o null si no encuentra.
     */
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

    /**
     * Busca las N butacas libres consecutivas en una fila. Si no hay, retorna null.
     *
     * @param row    fila en la que buscará las butacas.
     * @param amount el número de butacas necesarias (N).
     * @return La primer butaca de la serie de N butacas, si no hay retorna null.
     */
    public Seat getAvailableSeatsInRow(int row, int amount) {
        int consecutiveFreeSeats = 0;
        Seat firstFreeSeat = null;

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
        return null; // no se encontró la secuencia
    }

    /**
     * Busca en toda la sala N butacas libres consecutivas. Si las encuentra
     * retorna la primer butaca de la serie, si no retorna null.
     *
     * @param amount el número de butacas pedidas.
     */
    public Seat getAvailableSeats(int amount) {
        for (int i = 0; i < seats.length; i++){
            if (getAvailableSeatsInRow(amount, i) != null){
                return getAvailableSeatsInRow(amount, i);
            }
        }
        return null;
    }

    /**
     * Marca como ocupadas la cantidad de butacas empezando por la que se le pasa.
     *
     * @param seat   butaca inicial de la serie.
     * @param amount la cantidad de butacas a reservar.
     */
    public void takeSeats(Seat seat, int amount) {
        for (int i = 0; i < amount; i++){
            seats[seat.getRow()][seat.getSeatNumber()+i].takeSeat();
        }
    }

    /**
     * Libera la cantidad de butacas consecutivas empezando por la que se le pasa.
     *
     * @param seat   butaca inicial de la serie.
     * @param amount la cantidad de butacas a liberar.
     */
    public void releaseSeats(Seat seat, int amount) {
        for (int i = 0; i < amount; i++){
            seats[seat.getRow()][seat.getSeatNumber()+i].releaseSeat();
        }
    }
}
