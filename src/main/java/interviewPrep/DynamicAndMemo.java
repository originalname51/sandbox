package interviewPrep;


public class DynamicAndMemo {

    DynamicAndMemo() {
    }

    public int coins(int coinValue[], int money) {
        int[] minCoinCount = new int[money + 1];
        minCoinCount[0] = 0;
        int minCoin;
        for (int i = 0; i <= money; i++) {
            minCoin = Integer.MAX_VALUE;
            for (int j = 0; j < coinValue.length; j++) {
                if (i - coinValue[j] >= 0) {
                    minCoin = Math.min(minCoin, minCoinCount[i - coinValue[j]] + 1);
                    minCoinCount[i] = minCoin;
                }
            }
        }
        return minCoinCount[money];
    }

    /*
     * http://www.geeksforgeeks.org/dynamic-programming-set-13-cutting-a-rod/
     * */
    public int cutRod(int[] lengthPrice, int length) {
        int[] bestPrice = new int[length + 1];
        bestPrice[0] = 0;
        int mostPrice;
        for (int i = 1; i <= length; i++) {
            mostPrice = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++) {
                mostPrice = Math.max(mostPrice, lengthPrice[j] + bestPrice[i - j - 1]);
                bestPrice[i] = mostPrice;
            }
        }
        return bestPrice[length];
    }

    /*
     * http://www.geeksforgeeks.org/dynamic-programming-set-10-0-1-knapsack-problem/
     * */
    public int oneZeroKnapSack(int[] itemVal, int[] weight, int num) {
        int i, w;
        int n = weight.length;
        int W = num;
        int K[][] = new int[n + 1][W + 1];

// Build table K[][] in bottom up manner
        for (i = 0; i <= n; i++) {
            for (w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    K[i][w] = 0;
                else if (weight[i - 1] <= w)
                    K[i][w] = Math.max(itemVal[i - 1] + K[i - 1][w - weight[i - 1]], K[i - 1][w]);
                else
                    K[i][w] = K[i - 1][w];
            }
        }

        return K[n][W];
    }

    public int memoFib(int n, int[] arr) {
        int fib;
        if (n == 0) {
            arr[0] = 0;
            return 0;
        }
        if (n == 1) {
            arr[1] = 1;
            return 1;
        }
        if (arr[n] != 0) {
            return arr[n];
        }
        fib = memoFib(n - 2, arr) + memoFib(n - 1, arr);
        arr[n] = fib;
        return fib;
    }


}
