class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + stoneValue[i];

        int[][] dp = new int[n][n]; // dp[i][i] = 0 by default

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                int best = 0;
                for (int k = i; k < j; k++) {
                    int leftSum = prefix[k + 1] - prefix[i];
                    int rightSum = prefix[j + 1] - prefix[k + 1];
                    int val;
                    if (leftSum < rightSum) {
                        val = leftSum + dp[i][k];
                    } else if (leftSum > rightSum) {
                        val = rightSum + dp[k + 1][j];
                    } else {
                        val = leftSum + Math.max(dp[i][k], dp[k + 1][j]);
                    }
                    best = Math.max(best, val);
                }
                dp[i][j] = best;
            }
        }

        return dp[0][n - 1];
    }
}