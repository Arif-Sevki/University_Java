/*
The Problem: The Minimum Path Sum
Imagine you have a grid filled with non-negative numbers.
You start at the top-left corner and want to reach the bottom-right corner. 
You can only move down or right at any point. 
Your goal is to find a path that minimizes the sum of all numbers along its way.
*/

class Problem01_TheMinimumPathSum {
    public static void main(String[] args) {

        int[][] grid = { { 3, 5, 9 }, { 8, 5, 7 }, { 1, 5, 3 }, { 4, 2, 9 } };

        int recursiveSolution = minPathSumRecursive(grid);
        System.out.println("Solution of recursive " + recursiveSolution);

        int greedySolotion = minPathSumGreedy(grid);
        System.out.println("Solution of greedy " + greedySolotion);

        int solutionMemoizetion = minPathSumMemoizetion(grid);
        System.out.println("Solution of memoizetion " + solutionMemoizetion);

        int solutionTabulation = minPathSumTabulation(grid);
        System.out.println("Solution of tabulation " + solutionTabulation);

    }

    public static int minPathSumRecursive(int[][] grid) {
        return helper(grid, 0, 0);
    }

    private static int helper(int[][] grid, int i, int j) {

        // Grid dışına çıkarsak
        if (i >= grid.length || j >= grid[0].length)
            return Integer.MAX_VALUE;

        // Hedef hücre
        if (i == grid.length - 1 && j == grid[0].length - 1)
            return grid[i][j];

        int down = helper(grid, i + 1, j);
        int right = helper(grid, i, j + 1);

        return grid[i][j] + Math.min(down, right);
    }

    public static int minPathSumGreedy(int[][] grid) {
        int i = 0, j = 0;
        int sum = grid[0][0];

        while (i < grid.length - 1 || j < grid[0].length - 1) {

            if (i == grid.length - 1) {
                j++;
            } else if (j == grid[0].length - 1) {
                i++;
            } else if (grid[i + 1][j] < grid[i][j + 1]) {
                i++;
            } else {
                j++;
            }

            sum += grid[i][j];
        }

        return sum;
    }

    public static int minPathSumMemoizetion(int[][] grid) {
        int[][] memo = new int[grid.length][grid[0].length];
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[0].length; j++) {
                memo[i][j] = -1;
            }

        }
        return memoizetionHelper(grid, memo.length - 1, memo[0].length - 1, memo);

    }

    public static int memoizetionHelper(int[][] grid, int r, int c, int[][] memo) {
        if (r == 0 && c == 0)
            return grid[0][0];
        if (r < 0 || c < 0)
            return Integer.MAX_VALUE;
        if (memo[r][c] != -1)
            return memo[r][c];
        int costFromAbove = memoizetionHelper(grid, r - 1, c, memo);
        int costFromLeft = memoizetionHelper(grid, r, c - 1, memo);

        memo[r][c] = grid[r][c] + Math.min(costFromAbove, costFromLeft);

        return memo[r][c];
    }

    public static int minPathSumTabulation(int[][] grid) {
        int[][] table = new int[grid.length][grid[0].length];
        table[0][0] = grid[0][0];

        for (int i = 1; i < table.length; i++) {
            table[i][0] = grid[i][0] + table[i - 1][0];
        }

        for (int j = 1; j < table[0].length; j++) {
            table[0][j] = grid[0][j] + table[0][j - 1];
        }

        for (int i = 1; i < table.length; i++) {
            for (int j = 1; j < table[0].length; j++) {
                table[i][j] = grid[i][j] + Math.min(table[i-1][j],table[i][j-1]);
            }
        }

        return table[table.length-1][table[0].length-1];
    }

}
