package org.interview.transfer.concurent;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SequenceThreadFrame {

    public List<String> sequence() throws InterruptedException {
        CopyOnWriteArrayList<String> out = new CopyOnWriteArrayList<>();

        Thread first = new Thread(() -> {
            out.add("first");
            out.add("first");
            out.add("first");
        });

        Thread second = new Thread(() -> {
            out.add("second");
            out.add("second");
            out.add("second");
        });

        first.start();
        second.start();
        first.join();
        second.join();

        return out;
    }

    public static void main(String[] args) throws InterruptedException {
        List<String> sequence = new SequenceThreadFrame().sequence();
        System.out.println("sequence = " + sequence);
    }
}
