class Solution {
    public int distinctSubseqII(String s) {
        final long MOD = 1_000_000_007L;

        // end[c] = number of distinct non-empty subsequences
        // whose last character is c
        long[] end = new long[26];

        for (char ch : s.toCharArray()) {
            int c = ch - 'a';

            // Every existing distinct subsequence can be extended
            // with ch, plus the subsequence consisting only of ch.
            long total = 1;

            for (int i = 0; i < 26; i++) {
                total = (total + end[i]) % MOD;
            }

            // Replace rather than add: this removes duplicates
            // caused by previous occurrences of the same character.
            end[c] = total;
        }

        long answer = 0;

        for (long count : end) {
            answer = (answer + count) % MOD;
        }

        return (int) answer;
    }
}
