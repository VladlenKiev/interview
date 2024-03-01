package org.interview.transfer.duplicate;

public class DuplicateService {

    boolean hasDuplicateWithDistinct(int[] inArr, int distinct) {
        if (distinct >= inArr.length || distinct == 0)
            return false;

        for (int index_i = 0; index_i < inArr.length - distinct; index_i++) {
            int i = inArr[index_i];
            for (int index_j = index_i + distinct; index_i < index_j; index_j--) {
                if (i == inArr[index_j]) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean hasDuplicateWithDistinctOnCordage(int[] inArr, int distinct) {
        if (distinct >= inArr.length || distinct == 0)
            return false;

        for (int index_i = 0; index_i < inArr.length - 1; index_i++) {
            int i = inArr[index_i];
            for (int index_j = index_i + 1; (index_j < index_i + distinct) && (index_j < inArr.length-1); index_j++) {
                if (i == inArr[index_j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
