package org.interview.transfer.stock;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.random.RandomGenerator;

import static org.junit.jupiter.api.Assertions.*;

class BidEngineServiceTest {
    public static final String OWNER_1 = "Owner_1";
    private List<Bid> bids;
    private BidEngineService<Bid> bidBidEngineService;
    @BeforeEach
    void setUp() {
        bids = new ArrayList<>();
        bidBidEngineService = new BidEngineService<>();
    }

    @Test
    void addBid() {
        bidBidEngineService.addBid(new Bid(1, OWNER_1, 12.0));
        bidBidEngineService.addBid(new Bid(2, OWNER_1, 13.0));

        assertEquals(2, bidBidEngineService.getAllBids().size());
    }

    @SneakyThrows
    @Test
    void addBid_withMultiThreads_200() {
        int countOfThreads = 200;
        ExecutorService executorService = Executors.newFixedThreadPool(countOfThreads);
        CountDownLatch latch = new CountDownLatch(countOfThreads);
        for (int i = 0; i < countOfThreads; i++) {
            executorService.submit(()-> {
                bidBidEngineService.addBid(new Bid(new Random().nextInt(50), Thread.currentThread().getName(), new Random().nextDouble(3.0)));
                latch.countDown();
            });
        }
        latch.await();
//        Thread.sleep(1000);
        assertEquals(0, bidBidEngineService.getAllBids().size());
        assertEquals(2, bidBidEngineService.getHistoryBids().size());

    }

    @SneakyThrows
    @Test
    void addBid_withMultiThreads_10() {
        int countOfThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(countOfThreads);
        CountDownLatch latch = new CountDownLatch(countOfThreads);
        for (int i = 0; i < countOfThreads; i++) {
            executorService.submit(()-> {
                bidBidEngineService.addBid(new Bid(new Random().nextInt(50), Thread.currentThread().getName(), new Random().nextDouble(3.0)));
                latch.countDown();
            });
        }
        latch.await();
//        Thread.sleep(1000);
        assertEquals(10, bidBidEngineService.getAllBids().size());
        assertEquals(0, bidBidEngineService.getHistoryBids().size());

    }

    private static Bid generateBidOwner1() {
        Random random = new Random();
        return new Bid(random.nextInt(), OWNER_1, random.nextDouble(1000.0, 10.0));
    }
}