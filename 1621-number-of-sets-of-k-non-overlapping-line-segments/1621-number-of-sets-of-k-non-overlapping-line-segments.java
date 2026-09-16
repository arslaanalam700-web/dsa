class Solution {
    private static final int MOD = 1_000_000_007;

    public int numberOfSets(int n, int k) {
        long[][] dp = new long[k + 1][n];
        long[][] sum = new long[k + 1][n];

        // 0 segments -> exactly 1 way
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
            sum[0][i] = i + 1;
        }

        for (int j = 1; j <= k; j++) {
            for (int i = 1; i < n; i++) {

                // Don't use point i.
                dp[j][i] = dp[j][i - 1];

                // Use point i as part of the last segment.
                //
                // The last segment can:
                // 1. start at i-1
                // 2. start even earlier
                //
                // sum[j-1][i-1] counts all possibilities.
                dp[j][i] += sum[j - 1][i - 1];
                dp[j][i] %= MOD;

                // Prefix sum for the current j.
                sum[j][i] = sum[j][i - 1] + dp[j][i];
                sum[j][i] %= MOD;
            }
        }

        return (int) dp[k][n - 1];
    }
}
