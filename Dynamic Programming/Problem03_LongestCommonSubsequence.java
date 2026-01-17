
/*
It compares two strings to find the longest shared sequence of characters.
They don't have to be consecutive, but must stay in order.
*/

public class Problem03_LongestCommonSubsequence {
    public static void main(String[] args) {
        String s1 = "STONE";
        String s2 = "LONGEST";

        int recursiceSol = recursiveLCS(s1, s2, s1.length() - 1, s2.length() - 1);
        int memoSol= memozietionLCS(s1, s2);
        int tabulationSol=tabulationLCS(s1, s2);

        System.out.println("Recursive sol = "+ recursiceSol);
        System.out.println("Memoizetion sol = "+ memoSol);
        System.out.println("Tabulation sol = "+ tabulationSol);

    }

    public static int recursiveLCS(String s1, String s2, int i, int j) {
        // Base Case: If either string is exhausted
        if (i < 0 || j < 0) {
            return 0;
        }

        // If characters match, take 1 and move both pointers back
        if (s1.charAt(i) == s2.charAt(j)) {
            return 1 + recursiveLCS(s1, s2, i - 1, j - 1);
        }
        // If they don't match, explore two possibilities and take the max:
        // 1. Skip character in s1
        // 2. Skip character in s2
        else {
            return Math.max(recursiveLCS(s1, s2, i - 1, j),
                    recursiveLCS(s1, s2, i, j - 1));
        }
    }
    
    public static int memozietionLCS(String s1, String s2) {
        int[][] memo = new int[s1.length()][s2.length()];
        for (int a = 0; a < memo.length; a++) {
            for (int b = 0; b < memo[0].length; b++) {
                memo[a][b] = -1;
            }
        }

        return memozietionLCS(s1,s2,s1.length()-1,s2.length()-1,memo);
    }
    public static int memozietionLCS(String s1, String s2, int i, int j, int[][] memo) {
        
        if (i < 0 || j < 0) {
            return 0;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            memo[i][j] = 1 + memozietionLCS(s1, s2, i - 1, j - 1,memo);

        }
        else {
            memo[i][j] = Math.max(memozietionLCS(s1, s2, i - 1, j,memo),
                    memozietionLCS(s1, s2, i, j - 1,memo));
        }
        return memo[i][j];
    }

    public static int tabulationLCS(String s1, String s2){
         int[][] table = new int[s1.length()+1][s2.length()+1];

        for (int j = 0; j < s2.length()+1; j++) {
            table[0][j] = 0;
        }
        for (int i = 1; i < s1.length()+1; i++) {
            table[i][0]=0;
        }

        for (int i = 1; i < s1.length()+1; i++) {
            for (int  j= 1; j < s2.length()+1; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)){
                    table[i][j]=1+table[i-1][j-1];
                }
                else{
                   table[i][j]= Math.max(table[i-1][j], table[i][j-1]);
                }
            }
        }


        return table[s1.length()][s2.length()];
    }

}
