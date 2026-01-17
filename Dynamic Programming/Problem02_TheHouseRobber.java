/*
The House Robber Problem: You are a robber planning to rob houses along a street.
Each house has a certain amount of money.
The only constraint is that you cannot rob two adjacent houses (the alarm will go off).
*/

public class Problem02_TheHouseRobber {
    public static void main(String[] args) {

        int[] houses = { 3, 7, 9, 5, 6, 4, 2, 8, 1 };

        int recursiveSol = robRecursive(houses);
        int memoSol = robMemoizetion(houses);
        int tabulationSol = robTabulation(houses);
        int greedySol = robGreedy(houses);

        System.out.println("Solution of recursive " + recursiveSol);
        System.out.println("Solution of memoizetion " + memoSol);
        System.out.println("Solution of tabulation " + tabulationSol);
        System.out.println("Solution of greedy " + greedySol);
    }

    public static int robRecursive(int[] houses) {
        return robRecursiveHelper(houses, houses.length - 1);
    }

    private static int robRecursiveHelper(int[] houses, int i) {
        // Base case: No more houses to rob
        if (i < 0) {
            return 0;
        }

        // Base case: Only one house left (at index 0)
        if (i == 0) {
            return houses[0];
        }

        // Choice 1: Rob current house i + solve for i-2
        int robCurrent = houses[i] + robRecursiveHelper(houses, i - 2);

        // Choice 2: Skip current house + solve for i-1
        int skipCurrent = robRecursiveHelper(houses, i - 1);

        // Return the maximum of the two choices
        return Math.max(robCurrent, skipCurrent);
    }

    public static int robMemoizetion(int[] houses) {

        int[] memo = new int[houses.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = -1;
        }

        return robMemoizetionHelper(houses, houses.length - 1, memo);
    }

    public static int robMemoizetionHelper(int[] houses, int i, int[] memo) {

        if (i < 0) {
            return 0;
        }
        if (i == 0) {
            return houses[0];
        }
        if (memo[i] != -1)
            return memo[i];

        int notSkip = robMemoizetionHelper(houses, i - 1, memo);
        int skip = houses[i] + robMemoizetionHelper(houses, i - 2, memo);

        memo[i] = Math.max(notSkip, skip);
        return memo[i];
    }

    public static int robTabulation(int[] houses) {
        if (houses.length < 1) {
            return 0;
        } else if (houses.length == 1) {
            return houses[0];
        } else if (houses.length == 2) {
            return Math.max(houses[0], houses[1]);
        } else {
            int[] table = new int[houses.length];

            for (int i = 0; i < table.length; i++) {
                if (i == 0) {
                    table[i] = houses[i];
                } else if (i == 1) {
                    table[i] = Math.max(table[0], houses[i]);
                } else {
                    table[i] = Math.max(houses[i] + table[i - 2], table[i - 1]);
                }

            }
            return table[table.length - 1];
        }

    }

    public static int robGreedy(int[] houses) {
        if (houses.length < 1) {
            return 0;
        } else if (houses.length == 1) {
            return houses[0];
        } else if (houses.length == 2) {
            return Math.max(houses[0], houses[1]);
        } else {
            int i = 0, sum = 0;
            while (i < houses.length) {

                if (i == houses.length-1) {
                    sum = sum + houses[i];
                    i++;
                } else if (houses[i] > houses[i + 1]) {
                    sum += houses[i];
                    i = i + 2;
                } else {
                    sum += houses[i + 1];
                    i = i + 3;
                }
            }
            return sum;
        }
    }

}
