package com.mangoo.javaconcurrency.ex3_atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class BusAtomic {
    private final int minOccupancy = 10;
    private final AtomicInteger reservation = new AtomicInteger();

    public void getBusTicket() {
        try {
            Thread.sleep(100);
            int newReservation = reservation.incrementAndGet();
            if (newReservation < minOccupancy) {
                Thread.sleep(1);
                System.out.println("인원 부족으로 버스 운행이 취소될 수 있습니다. 현재 예약 인원: " + newReservation);
            }
        } catch (InterruptedException e) {
            System.out.println("ERROR!");
        }
    }

    public int getReservation() {
        return reservation.get();
    }
}
