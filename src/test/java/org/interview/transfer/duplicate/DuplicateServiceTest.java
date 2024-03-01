package org.interview.transfer.duplicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DuplicateServiceTest {

    private int[] testArr_0 = new int[]{1,1,1};
    private int[] testArr_1 = new int[]{1,2,3,1};
    private DuplicateService duplicateService;
    @BeforeEach
    void setUp() {
        duplicateService = new DuplicateService();
    }

    private static Stream<Arguments> testArrays() {
        return Stream.of(
                Arguments.of(new int[]{1,1,1}, 3, false),
                Arguments.of(new int[]{1,2,3,1}, 3, true),
                Arguments.of(new int[]{1,2,3,1}, 2, false),
                Arguments.of(new int[]{1,2,3,1}, 4, false),
                Arguments.of(new int[]{1,2,3,4,5,6}, 2, false),

                Arguments.of(new int[]{1,1,1,1}, 2, true),
                Arguments.of(new int[]{1,1,1,1}, 0, false),

                Arguments.of(new int[]{1,3,2,2}, 1, true),
                Arguments.of(new int[]{1,2,3,4,5,5}, 1, true)

        );
    }

    @ParameterizedTest
    @MethodSource("testArrays")
    void hasDuplicate_parameterized(int[] inArr, int distinct, boolean hasDuplicateWithDistinct) {
        assertEquals(hasDuplicateWithDistinct, duplicateService.hasDuplicateWithDistinct(inArr, distinct));
    }

    @ParameterizedTest
    @MethodSource("testArrays")
    void hasDuplicateWithDistinctOnCordage_parameterized(int[] inArr, int distinct, boolean hasDuplicateWithDistinct) {
        assertEquals(hasDuplicateWithDistinct, duplicateService.hasDuplicateWithDistinctOnCordage(inArr, distinct));
    }

    @Test
    void hasDuplicate_withDistinctGtArrLength_returnFalse() {
        int distinct = 3;
        boolean hasDuplicate = duplicateService.hasDuplicateWithDistinct(testArr_0, distinct);

        assertFalse(hasDuplicate);
    }
    @Test
    void hasDuplicate_returnTrue() {
        int distinct = 3;
        boolean hasDuplicate = duplicateService.hasDuplicateWithDistinct(testArr_1, distinct);

        assertTrue(hasDuplicate);
    }

    @Test
    void hasDuplicate_returnFalse() {
        int distinct = 2;
        boolean hasDuplicate = duplicateService.hasDuplicateWithDistinct(testArr_1, distinct);

        assertFalse(hasDuplicate);
    }
}