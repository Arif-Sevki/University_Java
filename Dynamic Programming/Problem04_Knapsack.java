/*
At every item i, you have two choices:
Include the item: Add its value to your total, but subtract its weight from your remaining capacity. 
You then move to the next item (i-1).
Exclude the item: Keep your current value and capacity, and move to the next item (i-1).
*/

public class Problem04_Knapsack {

    public static void main(String[] args) {

        int W = 55;
        int[] weights = { 5, 4, 3, 9, 7, 10, 15, 40, 20, 6, 3, 7 };
        int[] values = { 9, 5, 1, 2, 9, 8, 5, 30, 11, 3, 2, 7 };
        int n = weights.length;

        int recursive = knapsackRecursive(W, weights, values, n);
        int memo = memoizetionKnapsack(W, weights, values, n);
        int tabu = tabulationKnapsnack(W, weights, values, n);

        System.out.println("Recursive: " + recursive);
        System.out.println("Memo: " + memo);
        System.out.println("Tabu: " + tabu);
    }

    public static int knapsackRecursive(int W, int[] weights, int[] values, int n) {
        // Base Case: No items left or no capacity left
        if (n == 0 || W == 0) {
            return 0;
        }

        // If weight of the current item is more than the capacity W,
        // this item cannot be included
        if (weights[n - 1] > W) {
            return knapsackRecursive(W, weights, values, n - 1);
        }

        // Return the maximum of two cases:
        // 1. Current item included
        // 2. Current item not included
        int include = values[n - 1] + knapsackRecursive(W - weights[n - 1], weights, values, n - 1);
        int exclude = knapsackRecursive(W, weights, values, n - 1);

        return Math.max(include, exclude);
    }

    public static int memoizetionKnapsack(int W, int[] weights, int[] values, int n) {
        int[][] memo = new int[n + 1][W + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < W + 1; j++) {
                memo[i][j] = -1;
            }
        }

        return memoizetionKnapsack(W, weights, values, n, memo);
    }

    public static int memoizetionKnapsack(int W, int[] weights, int[] values, int n, int[][] memo) {

        if (memo[n][W] != -1) {
            return memo[n][W];
        }

        if (n == 0 || W == 0) {
            memo[n][W] = 0;
            return memo[n][W];
        }

        if (weights[n - 1] > W) {
            memo[n][W] = memoizetionKnapsack(W, weights, values, n - 1, memo);
            return memo[n][W];
        }

        int include = values[n - 1] + memoizetionKnapsack(W - weights[n - 1], weights, values, n - 1, memo);
        int exclude = memoizetionKnapsack(W, weights, values, n - 1, memo);

        memo[n][W] = Math.max(include, exclude);
        return memo[n][W];
    }

    public static int tabulationKnapsnack(int W, int[] weights, int[] values, int n) {
        int[][] table = new int[n + 1][W + 1];
        for (int i = 0; i < n + 1; i++) {
            table[i][0] = 0;
        }
        for (int j = 1; j < W + 1; j++) {
            table[0][j] = 0;
        }

        for (int i = 1; i < table.length; i++) {
            for (int j = 1; j < table[0].length; j++) {
                if (weights[i - 1] > j) {
                    table[i][j] = table[i - 1][j];
                } else {
                    int include = values[i - 1] + table[i - 1][j - weights[i - 1]];
                    int exclude = table[i - 1][j];
                    table[i][j] = Math.max(include, exclude);
                }
            }
        }
        return table[n][W];
    }

}
