class Solution {
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] ans = new int[n];

        if (k == 0) {
            return ans; // already filled with 0s
        }

        // Sliding window of k elements.
        int sum = 0;

        if (k > 0) {
            // Initial window: elements 1 ... k
            for (int i = 1; i <= k; i++) {
                sum += code[i % n];
            }

            for (int i = 0; i < n; i++) {
                ans[i] = sum;

                // Remove the element leaving the window
                sum -= code[(i + 1) % n];

                // Add the next element entering the window
                sum += code[(i + k + 1) % n];
            }
        } else {
            k = -k;

            // Initial window: previous k elements of index 0
            for (int i = 1; i <= k; i++) {
                sum += code[(n - i) % n];
            }

            for (int i = 0; i < n; i++) {
                ans[i] = sum;

                // Remove the oldest previous element
                sum -= code[(i - k + n) % n];

                // Add the next previous element
                sum += code[i];
            }
        }

        return ans;
    }
}