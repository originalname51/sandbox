package interviewPrep;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by rob on 6/14/17.
 * Implement binary search of a sorted array of integers
 */
public class BinarySearch {

    private static final int RANDOM_TEST_AMOUNT = 2500;

    public static void main(String args[]) {


        int arr[] = {1, 3, 4, 5, 6};
        assertTrue(binarySearch(arr, 0, arr.length - 1, 4));
        assertFalse(binarySearch(arr, 0, arr.length - 1, 23));

        testForBinarySearchTree();
    }

    private static void testForBinarySearchTree() {
        Tuple tuple = makeArray();
        Arrays.sort(tuple.arr);
        Random random = getRandom();
        for (int i : tuple.arr) {
            assertTrue(binarySearch(tuple.arr, 0, tuple.arr.length - 1, i));
        }
        for (int i = RANDOM_TEST_AMOUNT; i > 0; i--) {
            int number = random.nextInt();
            if (i % 2 == 0) {
                number = tuple.arr[i - 1];
            }
            Boolean exist = tuple.existInArr.get(number);
            if (exist != null)
                assertTrue(binarySearch(tuple.arr, 0, tuple.arr.length - 1, number));
            else
                assertFalse(binarySearch(tuple.arr, 0, tuple.arr.length - 1, number));
        }
    }


    private static Tuple makeArray() {
        Random random = getRandom();
        Tuple tuple = new Tuple();
        tuple.arr = new int[RANDOM_TEST_AMOUNT];
        for (int i = 0; i < tuple.arr.length; i++) {
            int randomNumber = random.nextInt();
            tuple.arr[i] = randomNumber;
            tuple.existInArr.put(randomNumber, true);
        }

        return tuple;
    }

    private static Random getRandom() {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        return random;
    }


    private static Boolean binarySearch(int arr[], int left, int right, int number) {

        if (right >= left) {
            int middle = left + (right - left) / 2;
            if (arr[middle] == number) {
                return true;
            } else if (arr[middle] > number) {
                return binarySearch(arr, left, middle - 1, number);
            } else {
                return binarySearch(arr, middle + 1, right, number);
            }
        }
        return false;

    }

    private static class Tuple {
        int[] arr;
        Map<Integer, Boolean> existInArr;

        Tuple() {
            existInArr = new HashMap<>();
        }

    }

}
