class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();

        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Try each position from right to left.
        for (int i = n - 1; i >= 0; i--) {

            // Reconstruct frequency of characters available
            // for positions [i ... n-1].
            int[] available = freq.clone();

            // Remove target[0 ... i-1] from available.
            boolean possiblePrefix = true;

            for (int j = 0; j < i; j++) {
                int c = target.charAt(j) - 'a';

                if (available[c] == 0) {
                    possiblePrefix = false;
                    break;
                }

                available[c]--;
            }

            if (!possiblePrefix) {
                continue;
            }

            int current = target.charAt(i) - 'a';

            // Find the smallest character > target[i].
            for (int c = current + 1; c < 26; c++) {
                if (available[c] > 0) {

                    char[] ans = new char[n];

                    // Same prefix as target.
                    for (int j = 0; j < i; j++) {
                        ans[j] = target.charAt(j);
                    }

                    // First position where we become greater.
                    ans[i] = (char) ('a' + c);
                    available[c]--;

                    // Smallest possible suffix.
                    int pos = i + 1;

                    for (int x = 0; x < 26; x++) {
                        while (available[x] > 0) {
                            ans[pos++] = (char) ('a' + x);
                            available[x]--;
                        }
                    }

                    return new String(ans);
                }
            }
        }

        return "";
    }
}