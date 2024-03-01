package org.interview.transfer.stock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class BidEngineService<T> {
    private final List<T> bids = new ArrayList<>();
    private final ConcurrentHashMap<String, List<T>> historyBids = new ConcurrentHashMap<String, List<T>>();

    public synchronized void addBid(T t) {
        bids.add(t);
        System.out.println("BidEngineService.addBid" + t + " by thread: " + Thread.currentThread().getName());
        packageBids();
    }

    private synchronized void packageBids() {
        if (bids.size() >= 100) {
            historyBids.put(String.valueOf(System.currentTimeMillis()), new ArrayList<>(bids));
            bids.clear();
            System.out.println("BidEngineService.clearBidsIfExceedsLimit: Bid list cleared due to exceeding limit");
        }
    }

    public List<T> getAllBids() {
        return bids;
    }

    public ConcurrentHashMap<String, List<T>> getHistoryBids() {
        return historyBids;
    }
}
