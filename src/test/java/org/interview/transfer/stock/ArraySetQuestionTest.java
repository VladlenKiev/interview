package org.interview.transfer.stock;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ArraySetQuestionTest {

    private static Stream<Arguments> provideArray() {
        int[] q1 = new int[] {2,4,6,8,10,12};
        int[] q2 = new int[] {2,4,6,8};
        int[] q3 = new int[] {2,4,6,8};
        int[] q4 = new int[] {2,4,6,8};

        return Stream.of(
                Arguments.of(q1, 0, -1),
                Arguments.of(q1, 100, -1),
                Arguments.of(q1, -1, -1),
                Arguments.of(q1, 2, 2),
                Arguments.of(q1, 3, 2),
                Arguments.of(q1, 8, 8),
                Arguments.of(q1, 9, 8));
    }

    @ParameterizedTest
    @MethodSource("provideArray")
    void findClosesMinMemberInArray_parameterized(int[] arr, int checkInt, int expectedInt) {
        ArraySetQuestion arraySetQuestion = new ArraySetQuestion();
        assertEquals(expectedInt, arraySetQuestion.findClosesMinMemberInArray(arr, checkInt));
    }
}