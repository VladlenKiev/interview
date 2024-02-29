package org.interview.transfer.notify;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class Waiter implements Runnable{
    private Message msg;


    @SneakyThrows
    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        synchronized (msg) {
            System.out.println("name = " + name + " waiting to get notified at time " + System.currentTimeMillis());
            msg.wait();

            System.out.println(name+" waiter thread got notified at time:"+System.currentTimeMillis());
            //process the message now
            System.out.println(name+" processed: "+msg.getMsg());
        }
    }
}
