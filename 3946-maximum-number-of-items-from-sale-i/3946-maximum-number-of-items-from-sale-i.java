class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] cnt = new int[n];
        for (int i = 0; i < n; i++) {
            int fi = items[i][0];
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % fi == 0) {
                    cnt[i]++;
                }
            }
        }

        int[] dp = new int[budget + 1]; // dp[b] = max copies with total spend <= b

        // 0/1 knapsack: first copy of each item type (gives free copies)
        for (int i = 0; i < n; i++) {
            int p = items[i][1];
            int v = 1 + cnt[i];
            for (int b = budget; b >= p; b--) {
                dp[b] = Math.max(dp[b], dp[b - p] + v);
            }
        }

        // Unbounded knapsack: extra plain copies (value 1 each)
        for (int i = 0; i < n; i++) {
            int p = items[i][1];
            for (int b = p; b <= budget; b++) {
                dp[b] = Math.max(dp[b], dp[b - p] + 1);
            }
        }

        return dp[budget];
    }
}