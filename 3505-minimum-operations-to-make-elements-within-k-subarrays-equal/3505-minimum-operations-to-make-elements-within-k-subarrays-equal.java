
import java.util.*;

class Solution {

    TreeMap<Integer, Integer> left = new TreeMap<>();
    TreeMap<Integer, Integer> right = new TreeMap<>();

    long sumLeft = 0;
    long sumRight = 0;

    int sizeLeft = 0;
    int sizeRight = 0;

    void addLeft(int num) {
        left.put(num, left.getOrDefault(num, 0) + 1);
        sumLeft += num;
        sizeLeft++;
    }

    void addRight(int num) {
        right.put(num, right.getOrDefault(num, 0) + 1);
        sumRight += num;
        sizeRight++;
    }

    void removeLeft(int num) {
        int count = left.get(num);

        if (count == 1) {
            left.remove(num);
        } else {
            left.put(num, count - 1);
        }

        sumLeft -= num;
        sizeLeft--;
    }

    void removeRight(int num) {
        int count = right.get(num);

        if (count == 1) {
            right.remove(num);
        } else {
            right.put(num, count - 1);
        }

        sumRight -= num;
        sizeRight--;
    }

    void add(int num) {
        if (left.isEmpty() || num <= left.lastKey()) {
            addLeft(num);
        } else {
            addRight(num);
        }

        balance();
    }

    void remove(int num) {
        if (left.containsKey(num)) {
            removeLeft(num);
        } else {
            removeRight(num);
        }

        balance();
    }

    void balance() {
        int target = (sizeLeft + sizeRight + 1) / 2;

        while (sizeLeft > target) {
            int num = left.lastKey();
            removeLeft(num);
            addRight(num);
        }

        while (sizeLeft < target) {
            int num = right.firstKey();
            removeRight(num);
            addLeft(num);
        }
    }

    long getCost() {
        long median = left.lastKey();

        return median * sizeLeft - sumLeft
             + sumRight - median * sizeRight;
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        long[] cost = new long[n - x + 1];

        // Step 1: Calculate cost of every window.

        for (int i = 0; i < x; i++) {
            add(nums[i]);
        }

        cost[0] = getCost();

        for (int i = x; i < n; i++) {

            remove(nums[i - x]);
            add(nums[i]);

            cost[i - x + 1] = getCost();
        }

        // Step 2: Dynamic Programming.

        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[k + 1][n + 1];

        for (int j = 1; j <= k; j++) {
            Arrays.fill(dp[j], INF);
        }

        // dp[0][i] = 0 for all i.

        for (int j = 1; j <= k; j++) {

            for (int i = 1; i <= n; i++) {

                // Option 1: Skip current element.
                dp[j][i] = dp[j][i - 1];

                // Option 2: Select a window of size x.
                if (i >= x &&
                    dp[j - 1][i - x] != INF) {

                    long take = dp[j - 1][i - x]
                              + cost[i - x];

                    dp[j][i] = Math.min(
                        dp[j][i],
                        take
                    );
                }
            }
        }

        return dp[k][n];
    }
}
