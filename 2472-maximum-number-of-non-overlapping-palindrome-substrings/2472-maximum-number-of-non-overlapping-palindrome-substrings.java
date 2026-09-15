class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();

        // palindrome[i][j] = true if s[i..j] is a palindrome
        boolean[][] palindrome = new boolean[n][n];

        // Build palindrome table by increasing substring length.
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;

                if (s.charAt(i) == s.charAt(j)) {
                    if (len <= 2) {
                        palindrome[i][j] = true;
                    } else {
                        palindrome[i][j] = palindrome[i + 1][j - 1];
                    }
                }
            }
        }

        // dp[i] = maximum number of valid palindromes
        // using the first i characters.
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            // Don't use a palindrome ending at i - 1.
            dp[i] = dp[i - 1];

            // Try every palindrome s[j..i-1].
            for (int j = 0; j <= i - k; j++) {
                if (palindrome[j][i - 1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[n];
    }
}
