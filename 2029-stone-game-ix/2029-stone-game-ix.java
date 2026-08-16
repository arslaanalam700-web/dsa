class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] cnt = new int[3];

        for (int x : stones) {
            cnt[x % 3]++;
        }

        // Stones divisible by 3 do not change the sum modulo 3.
        // If there are no stones with remainder 1 or 2, Alice loses.
        if (cnt[1] == 0 && cnt[2] == 0) {
            return false;
        }

        /*
         * If cnt[0] is even:
         * Alice wins when both cnt[1] and cnt[2] are non-zero.
         *
         * If cnt[0] is odd:
         * Alice needs a difference of at least 3 between cnt[1] and cnt[2].
         */
        if (cnt[0] % 2 == 0) {
            return cnt[1] > 0 && cnt[2] > 0;
        }

        return Math.abs(cnt[1] - cnt[2]) > 2;
    }
}
