class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int half = n / 2;

        int diff = 0;       // left sum - right sum
        int leftQ = 0;
        int rightQ = 0;

        for (int i = 0; i < half; i++) {
            if (num.charAt(i) == '?') {
                leftQ++;
            } else {
                diff += num.charAt(i) - '0';
            }
        }

        for (int i = half; i < n; i++) {
            if (num.charAt(i) == '?') {
                rightQ++;
            } else {
                diff -= num.charAt(i) - '0';
            }
        }

        // Alice wins if the number of '?' differs between halves
        // by an odd amount, or if the fixed sums cannot be balanced.
        if ((leftQ + rightQ) % 2 == 1) {
            return true;
        }

        int qDiff = leftQ - rightQ;

        /*
         * Bob can force equality iff:
         * diff == 9 * (rightQ - leftQ) / 2
         *
         * Since Alice can choose a '?' and Bob responds optimally,
         * equality is possible only at this exact balance.
         */
        return diff * 2 != 9 * (rightQ - leftQ);
    }
}