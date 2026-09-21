class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {
            int val = num % k;
            long[] next = new long[k];

            // Start a new subarray with nums[i]
            next[val]++;

            // Extend all subarrays ending at the previous index
            for (int r = 0; r < k; r++) {
                int newR = (r * val) % k;
                next[newR] += dp[r];
            }

            dp = next;

            // Add all subarrays ending at this index to the answer
            for (int r = 0; r < k; r++) {
                ans[r] += dp[r];
            }
        }

        return ans;
    }
}
