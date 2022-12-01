package com.mangoo.javaconcurrency.ex1_race_condition;

public class Bus {
    private final int minOccupancy = 10;
    private int reservation = 0;

    public void getBusTicket() {
        try {
            Thread.sleep(100);
            reservation++;
            if (reservation < minOccupancy) {
                Thread.sleep(10);
                System.out.println("인원 부족으로 버스 운행이 취소될 수 있습니다. 현재 예약 인원: " + reservation);
            }
        } catch (InterruptedException e) {
            System.out.println("ERROR!");
        }
    }

    public int getReservation() {
        return reservation;
    }
}