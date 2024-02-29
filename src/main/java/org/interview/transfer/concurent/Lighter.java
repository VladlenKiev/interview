package org.interview.transfer.concurent;

import lombok.SneakyThrows;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HexFormat;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

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

        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6);
//        Stream<Integer> integerStream1 = integerStream.map(x -> x + 10);
        long integerStream2 = integerStream.map(x -> x + 33).count();
//        integerStream.forEach(System.out::println);


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
    @SneakyThrows
    public String hash(String password) {
        MessageDigest md = MessageDigest.getInstance("MD5");

        md.update(password.getBytes());

        byte[] digest = md.digest();
        System.out.println("Lighter.hash = " + digest);

        HexFormat hexFormat = HexFormat.of();
        return hexFormat.formatHex(digest);
    }

    public List<String> grades (List<Integer> scores) {
        int ranges[] = {70,80,90, Integer.MAX_VALUE};
        String[] grades = {"Fail", "C", "B", "A"};

        List<String> result = new ArrayList<>();
        for (Integer score : scores) {
            int i = 0;
            while(ranges[i] < score) i++;
            result.add(grades[i]);
        }
        return result;
    }
}
