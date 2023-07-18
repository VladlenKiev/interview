package org.interview.transfer.concurent;

import java.util.concurrent.CopyOnWriteArrayList;

public class Lighter {

    static class Foo {
        final CopyOnWriteArrayList<String> out;

        Foo(CopyOnWriteArrayList<String> out) {
            this.out = out;
        }

        void first() {
            out.add("first");
        }
        void second() {
            out.add("second");
        }
        void third() {
            out.add("third");
        }
    }

    public static void main(String[] args) {
        var out = new CopyOnWriteArrayList<String>();

        Foo foo = new Foo(out);
        Thread third = new Thread(foo::third);
        Thread second = new Thread(()-> {
            try {
                third.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            foo.second();
        });
        Thread first = new Thread(()-> {
            try {
                second.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            foo.first();
        });

        first.start();
        second.start();
        third.start();


        System.out.println("out = " + out);
    }
}
