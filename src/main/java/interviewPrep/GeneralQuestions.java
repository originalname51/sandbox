package interviewPrep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

public class GeneralQuestions {

    public GeneralQuestions() {
    }

	
/*	Find the most frequent integer in an array naive n^2 solution*/

    public int frequentInt(int[] arr) {
        int freqInt;
        int maxIndex = -1;
        int numIntsInArray = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            freqInt = 0;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] == arr[i]) {
                    freqInt++;
                }
            }

            if (freqInt > numIntsInArray) {
                numIntsInArray = freqInt;
                maxIndex = i;
            }
        }
        return arr[maxIndex];
    }

    /*
     * nlogn solution because sorting.
     * */
    public int pairsSorted(int[] arr) {
        ArrayList<Integer> numList = new ArrayList<Integer>();

        for (int i = 0; i < arr.length; i++) {
            numList.add(arr[i]);
        }
        Collections.sort(numList);
        int pair = 0;

        for (int i = 0; i < numList.size(); i++) {
            arr[i] = numList.get(i);
        }
        //binary search on sorted array.
        for (int i = 0; i < numList.size(); i++) {
            if (binarySearch(arr, 10 - numList.get(i))) ;
            {
                pair++;
            }
        }
        return pair / 2;
    }

    /*
     * Hashing makes this operation O(n). Mind. Blown.
     * */
    public int pairSortedHash(int[] arr) {
        int pair = 0;

        Hashtable<Integer, Integer> hashArray = new Hashtable<Integer, Integer>();

        for (int i = 0; i < arr.length; i++) {
            hashArray.put(arr[i], i);
        }

        for (int i = 0; i < arr.length; i++) {
            if (hashArray.containsKey(10 - arr[i])) {
                pair++;
            }
        }
        return pair / 2;
    }

    /*
     * http://stackoverflow.com/questions/31000591/check-if-a-list-is-a-rotation-of-another-list-that-works-with-duplicates
     *
     * N^2 time complexity, (1) space.
     *
     * Copied code from above. Explanation of algorithm is as follows:
     *
     * 1) set k to 1.
     * 2) while numbers in array match increment k (this is done through the mod to get a positive index)
     * 3)
     *
     * */
    public boolean twoIntegerArray(int[] arrone, int[] arrtwo) {
        int i, j, k;
        int n = arrone.length;
        if (n != arrtwo.length)
            return false;

        i = j = -1;
        while (i < n - 1 && j < n - 1) {
            k = 1;
            while (k <= n && arrone[(i + k) % n] == arrtwo[(j + k) % n]) {
                k += 1;
            }
            if (k > n) {
                return true;
            }
            if (arrone[(i + k) % n] > arrtwo[(j + k) % n]) // the % is a slick way to handle negative numbers.
            {
                i += k;
            } else {
                j += k;
            }
        }
        return false;
    }

    /*
      solved using dp. better solution is to use temp variables but I wanted to explicitly use dp.
     * */
    public int fibnums(int n) {
        int[] arr = new int[n + 1];

        arr[0] = 0;
        arr[1] = 1;

        for (int i = 2; i < n + 1; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n];
    }

    /*
     * Memoization solution.
     * */
    public int fibMemo(int n, int arr[]) {
        if (n == 0) {
            arr[n] = 0;
            return 0;
        }
        if (n == 1) {
            arr[n] = 1;
            return 1;
        }
        if (arr[n] != 0) {
            return arr[n];
        } else {
            arr[n] = fibMemo(n - 2, arr) + fibMemo(n - 1, arr);
            return arr[n];
        }
    }

    /*
     * nlog in through a sort and iterate. Would take no extra space.
     *
     * I opted for a O(n) time / O(n) space solution because a) Hashtables are really neat and I wanted practice
     * and B) I like the reduced time complexity.
     *
     * */
    public int elementInArrayOnce(int[] arr) {
        Hashtable<Integer, Integer> hashArray = new Hashtable<Integer, Integer>();
        Hashtable<Integer, Integer> memoized = new Hashtable<Integer, Integer>();


        for (int i = 0; i < arr.length; i++) {
            if (hashArray.containsKey(arr[i])) {
                memoized.put(arr[i], i);
            } else {
                hashArray.put(arr[i], i);
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (!(memoized.containsKey(arr[i]))) {
                return arr[i];
            }
        }
        return -1; // should never return.
    }

/* Find the common elements of 2 int arrays 
 * 
 * Solved in O(n) time O(n) space
 * 
 * can be solved in OlogN time through a sort first.
 * 
 * Figured this one out on my own :)
 * 
 * */

    public int[] two_elements_of_int_array(int[] arr1, int[] arr2) {
        int[] commonEls = new int[arr1.length];
        Hashtable<Integer, Integer> arr1hash = new Hashtable<Integer, Integer>();

        for (int i = 0; i < arr1.length; i++) {
            if (!arr1hash.containsKey(arr1[i])) {
                arr1hash.put(arr1[i], i);
            }
        }

        int commonIndex = 0;
        for (int i = 0; i < arr2.length; i++) {

            if (arr1hash.containsKey(arr2[i])) {
                commonEls[commonIndex] = arr2[i];
                commonIndex++;
                arr1hash.remove(arr2[i]);
            }
        }
        return commonEls;
    }


    /*
     * Rotated index would require an off-set.
     * */
    public boolean binarySearch(int arr[], int num) {
        return _recursiveSearch(arr, 0, arr.length - 1, num);
    }

    /*
     * Binary Tree Search.
     * */
    private boolean _recursiveSearch(int[] arr, int min, int max, int num) {

        if (min > max) {
            return false;
        }
        int index = (min + max) / 2;

        if (arr[index] < num) {
            return _recursiveSearch(arr, index + 1, max, num);
        } else if (arr[index] > num) {
            return _recursiveSearch(arr, min, index - 1, num);
        } else {
            return true;
        }
    }

    public int multipleInts(int num1, int num2) {
        int answer = 0;
        for (int i = 0; i < num2; i++) {
            answer += num1;
        }

        return answer;
    }


    //up to 100 say fizz on divisible by 5 buzz on 7 fizzbuzz if both. Pretty easy one.
    public void fizzbuzz(int num) {
        boolean fizzbuzz;
        for (int i = 0; i < num; i++) {
            fizzbuzz = false;
            if (i % 5 == 0) {
                System.out.print("fizz");
                fizzbuzz = true;
            }
            if (i % 7 == 0) {
                System.out.print("buzz");
                fizzbuzz = true;
            }
            if (fizzbuzz) System.out.print(" ");
            else System.out.print(i + " ");
        }


    }

}