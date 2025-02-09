class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        Integer[][] dp = new Integer[m + 1][n + 1];

        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        return ed(dp, word1, word2, m, n);
    }

    public Integer ed(Integer[][] dp, String s1, String s2, int i1, int i2) {
        System.out.println("i1 - " + i1 + ", i2 - " + i2);

        if(i1 == 0) 
            dp[i1][i2] = i2; // Insert all remaining s2 characters in s1 to match it to s2
        else if(i2 == 0)
            dp[i1][i2] = i1; //Delete all remaining characters in s1 to match it to s2
        
        if (dp[i1][i2] != -1)
            return  dp[i1][i2];
        
        if(s1.charAt(i1 - 1) == s2.charAt(i2- 1))
            dp[i1][i2] =  ed(dp, s1, s2, i1-1, i2-1);    // Chars match. move on
        else {
            // Else 3 choices: Insert, Delete, Replace
            int e1 = 1 + ed(dp, s1, s2, i1, i2-1);    // Insert a char from s2 to s1, so the problem becomes from s1(0..i1), s2(0..i2-1)
            int e2 = 1 + ed(dp, s1, s2, i1-1, i2);    // Delete a char from s1. This reduces the problem to s1(0..i1-1), s2(0..i2)
            int e3 = 1 + ed(dp, s1, s1, i1-1, i2-1);  // Replace characters from s2 to s1, equal to delete from s1 and insert in s1

            dp[i1][i2] =  Math.min(e1, Math.min(e2, e3));
        }

        return dp[i1][i2];

    }
}