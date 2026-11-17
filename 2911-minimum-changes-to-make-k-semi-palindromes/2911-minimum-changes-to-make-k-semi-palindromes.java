class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        final int INF = 1_000_000_000;

        // cost[i][j]: min changes to make s[i..j] a semi-palindrome
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int len = j - i + 1;
                if (len < 2) {
                    cost[i][j] = INF;
                    continue;
                }
                int best = INF;
                for (int d = 1; d < len; d++) {
                    if (len % d != 0) continue;
                    int m = len / d;          // size of each group
                    int c = 0;
                    for (int g = 0; g < d; g++) {
                        for (int t = 0; t < m / 2; t++) {
                            if (s.charAt(i + g + t * d) != s.charAt(i + g + (m - 1 - t) * d)) {
                                c++;
                            }
                        }
                    }
                    best = Math.min(best, c);
                }
                cost[i][j] = best;
            }
        }

        // dp[j][i]: min cost to split first i chars into j parts
        int[][] dp = new int[k + 1][n + 1];
        for (int[] row : dp) java.util.Arrays.fill(row, INF);
        dp[0][0] = 0;

        for (int j = 1; j <= k; j++) {
            for (int i = 2 * j; i <= n; i++) {
                for (int p = 2 * (j - 1); p <= i - 2; p++) {
                    if (dp[j - 1][p] >= INF) continue;
                    int c = cost[p][i - 1];
                    if (c >= INF) continue;
                    dp[j][i] = Math.min(dp[j][i], dp[j - 1][p] + c);
                }
            }
        }
        return dp[k][n];
    }
}