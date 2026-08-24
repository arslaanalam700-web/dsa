class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        long[] prefixSum = new long[n];
        prefixSum[0] = stones[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + stones[i];
        }
        
        // dp represents dp[i+1] as we iterate i downward, starting from dp[n-1]
        long dp = prefixSum[n - 1];
        for (int i = n - 2; i >= 1; i--) {
            dp = Math.max(prefixSum[i] - dp, dp);
        }
        
        return (int) dp;
    }
}