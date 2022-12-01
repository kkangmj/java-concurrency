package com.mangoo.javaconcurrency.ex5_collection;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.*;

import static org.assertj.core.api.Assertions.assertThat;

public class SynchronizedCollectionTest {

    private final ExecutorService THREAD_POOL = Executors.newFixedThreadPool(10);

    @Test
    void list에_원소를_추가한다() throws InterruptedException {

        int N = 30;
        List<Integer> list = new ArrayList<>();
        List<Integer> addElements = Arrays.asList(1, 2, 3);

        CountDownLatch latch = new CountDownLatch(N);

        for (int i = 0; i < N; i++) {
            THREAD_POOL.execute(() -> {
                list.addAll(addElements);
                latch.countDown();
            });
        }

        latch.await();

        System.out.println(list.size());
        assertThat(list.size()).isNotEqualTo(N * 3);
    }

    @Test
    void syncrhonized_collection에_원소를_추가한다() throws InterruptedException {

        int N = 30;
        Collection<Integer> syncCollection = Collections.synchronizedCollection(new ArrayList<>());
        List<Integer> addElements = Arrays.asList(1, 2, 3);

        CountDownLatch latch = new CountDownLatch(N);

        for (int i = 0; i < N; i++) {
            THREAD_POOL.execute(() -> {
                syncCollection.addAll(addElements);
                latch.countDown();
            });
        }

        latch.await();

        System.out.println(syncCollection.size());
        assertThat(syncCollection.size()).isEqualTo(N * 3);
    }

    @Test
    void concurrent_collection에_원소를_추가한다() throws InterruptedException {

        int N = 30;
        Collection<Integer> concurrentCollection = new CopyOnWriteArrayList<>();
        List<Integer> addElements = Arrays.asList(1, 2, 3);

        CountDownLatch latch = new CountDownLatch(N);

        for (int i = 0; i < N; i++) {
            THREAD_POOL.execute(() -> {
                concurrentCollection.addAll(addElements);
                latch.countDown();
            });
        }

        latch.await();

        System.out.println(concurrentCollection.size());
        assertThat(concurrentCollection.size()).isEqualTo(N * 3);
    }
}
