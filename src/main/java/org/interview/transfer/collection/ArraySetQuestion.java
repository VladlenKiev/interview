package org.interview.transfer.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class ArraySetQuestion {

    public int findClosesMinMemberInArray(int[] sortedArray, int checkInt) {
//        sortedArray[0]

        return 0;
    }

    static class Student {
        private int id;
        private String name;
        private List<String> lessons;

        public Student(int id, String name, List<String> lessons) {
            this.id = id;
            this.name = name;
            this.lessons = lessons;
        }
    }
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Student studentOne = new Student(1, "John", Arrays.asList("Math", "Science"));
        Student studentTwo = new Student(2, "Jane", Arrays.asList("English", "History"));
        Student studentThree = new Student(3, "Bob", Arrays.asList("Math", "Science", "English"));

        List<Student> students = Arrays.asList(studentOne, studentTwo, studentThree);

        //get all students wwith id 1 and 3
        List<Student> studentsWithId1And3 = students.stream()
                .filter(student -> student.id == 1 || student.id == 3)
                .collect(Collectors.toList());

        System.out.println("studentsWithId1And3 = " + studentsWithId1And3);
    }

    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }
}
