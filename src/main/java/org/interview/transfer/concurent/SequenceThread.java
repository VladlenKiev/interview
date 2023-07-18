package org.interview.transfer.concurent;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SequenceThread {

    static class DoubleLock {
        boolean first = true;
        boolean second = false;


        synchronized void firstWait() throws InterruptedException {
            while (second) {
                wait();
            }
        }

        synchronized void firstDone() {
            first = false;
            second = true;
            notify();
        }

        synchronized void secondWait() throws InterruptedException {
            while (first) {
                wait();
            }
        }

        synchronized void secondDone() {
            first = true;
            second = false;
            notify();
        }
    }

    public List<String> sequence() throws InterruptedException {
        CopyOnWriteArrayList<String> out = new CopyOnWriteArrayList<>();
        DoubleLock lock = new DoubleLock();
        Thread first = new Thread(() -> {
            try {
                lock.firstWait();
                out.add("first");
                lock.firstDone();

                lock.firstWait();
                out.add("first");
                lock.firstDone();

                lock.firstWait();
                out.add("first");
                lock.firstDone();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread second = new Thread(() -> {
            try {
                lock.secondWait();
                out.add("second");
                lock.secondDone();

                lock.secondWait();
                out.add("second");
                lock.secondDone();

                lock.secondWait();
                out.add("second");
                lock.secondDone();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        first.start();
        second.start();
        first.join();
        second.join();

        return out;
    }

    public static void main(String[] args) throws InterruptedException {
        List<String> sequence = new SequenceThread().sequence();
        System.out.println("sequence = " + sequence);
    }
}
