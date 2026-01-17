/*
A knight starts at top-left of a grid and must reach bottom-right.
Cells contain integers (negative = damage, positive = health orb). 
Health must never drop to 0 or below. 
Find minimum initial health required. 
*/

public class Problem05_DungeonGame {

    public static void main(String[] args) {

        int[][] dungeon = {{ -2, -3, 3 },{ -5, -10, 1 },{ 10, 7, -5 }};
        int rec = recursiveSol(dungeon);

        System.out.println("Recursive = "+rec);


    }

    public static int recursiveSol(int[][] dungeon) {
        return recursiveSol(dungeon, 0, 0);
    }

    private static int recursiveSol(int[][] dungeon, int r, int c) {
        int rows = dungeon.length;
        int cols = dungeon[0].length;

        // Base Case: Reached the bottom-right (the goal)
        if (r == rows - 1 && c == cols - 1) {
            // If the cell has a health orb (positive), you only need 1 health to enter.
            // If it has damage (negative), you need 1 + the absolute damage.
            return Math.max(1, 1 - dungeon[r][c]);
        }

        // Base Case: Out of bounds
        if (r >= rows || c >= cols) {
            return Integer.MAX_VALUE;
        }

        // Recursive Step: Look at the health needed for the next steps
        int nextHealthDown = recursiveSol(dungeon, r + 1, c);
        int nextHealthRight = recursiveSol(dungeon, r, c + 1);

        // Find the minimum health needed to survive the rest of the path
        int minHealthToExit = Math.min(nextHealthDown, nextHealthRight);

        // Health needed before entering current cell:
        // (Minimum health needed after) - (current cell's value)
        int healthNeededBefore = minHealthToExit - dungeon[r][c];

        // You must always have at least 1 health to stay alive
        return Math.max(1, healthNeededBefore);
    }
}
