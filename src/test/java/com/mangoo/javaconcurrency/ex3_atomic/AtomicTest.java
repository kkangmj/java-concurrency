package com.mangoo.javaconcurrency.ex3_atomic;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

public class AtomicTest {
    private final ExecutorService THREAD_POOL = Executors.newFixedThreadPool(10);

    @Test
    void 삼십명의_사람들이_동시에_버스티켓을_구매한다() throws InterruptedException {

        int N = 30;
        CountDownLatch latch = new CountDownLatch(N);
        BusAtomic bus = new BusAtomic();

        for (int i = 0; i < N; i++) {
            THREAD_POOL.execute(() -> {
                bus.getBusTicket();
                latch.countDown();
            });
        }

        latch.await();

        int busReservation = bus.getReservation();
        System.out.println("======= Total reservation: " + busReservation + " =======");
        assertThat(busReservation).isEqualTo(N);
    }

}
