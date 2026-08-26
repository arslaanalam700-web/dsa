class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int left = 0;
        int ones = 0;
        int minLen = Integer.MAX_VALUE;
        String ans = "";

        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') {
                ones++;
            }

            // Shrink until the window has at most k ones.
            while (left <= right && ones > k) {
                if (s.charAt(left) == '1') {
                    ones--;
                }
                left++;
            }

            // If we have exactly k ones, remove leading zeroes
            // to make this window as short as possible.
            if (ones == k) {
                while (left <= right && s.charAt(left) == '0') {
                    left++;
                }

                int len = right - left + 1;
                String cur = s.substring(left, right + 1);

                if (len < minLen) {
                    minLen = len;
                    ans = cur;
                } else if (len == minLen && cur.compareTo(ans) < 0) {
                    ans = cur;
                }
            }
        }

        return ans;
    }
}
