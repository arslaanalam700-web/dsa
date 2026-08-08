class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        char[] a = word1.toCharArray();
        char[] b = word2.toCharArray();

        /*
         * dp[i] = maximum number of characters from the remaining
         * word2 suffix that can be matched exactly using word1[i..n-1].
         *
         * We compute it from right to left.
         */
        int[] dp = new int[n + 1];

        int j = m - 1;

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = dp[i + 1];

            if (j >= 0 && a[i] == b[j]) {
                dp[i] = dp[i + 1] + 1;
                j--;
            }
        }

        int[] ans = new int[m];

        int i = 0;
        j = 0;

        /*
         * First phase:
         * Greedily choose the smallest possible indices.
         *
         * If a[i] == b[j], we use it directly.
         *
         * Otherwise, we may use i as our one mismatch, but only if
         * all remaining characters of word2 can be matched exactly.
         */
        while (i < n && j < m) {

            if (a[i] == b[j]) {
                // Exact match: always take the smallest possible index.
                ans[j] = i;
                j++;
            } else {
                /*
                 * Use the one allowed mismatch here.
                 *
                 * We still need (m - j - 1) characters after this one.
                 * dp[i + 1] tells us how many characters can be matched
                 * exactly from word1[i + 1...].
                 */
                if (dp[i + 1] >= m - j - 1) {
                    ans[j] = i;
                    j++;
                    i++;

                    // The mismatch is now consumed.
                    break;
                }
            }

            i++;
        }

        // We couldn't even select enough indices.
        if (j < m && i == n) {
            return new int[0];
        }

        /*
         * Second phase:
         * The mismatch has already been used.
         * Therefore, every remaining character must match exactly.
         */
        while (i < n && j < m) {
            if (a[i] == b[j]) {
                ans[j] = i;
                j++;
            }
            i++;
        }

        // If we couldn't match all of word2, no valid sequence exists.
        if (j < m) {
            return new int[0];
        }

        return ans;
    }
}