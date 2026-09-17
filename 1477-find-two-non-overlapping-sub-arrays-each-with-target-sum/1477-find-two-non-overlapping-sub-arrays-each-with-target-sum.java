class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int INF = Integer.MAX_VALUE / 2;

        // best[i] = minimum length of a target-sum subarray
        // completely inside arr[0..i]
        int[] best = new int[n];
        java.util.Arrays.fill(best, INF);

        int left = 0;
        long sum = 0;
        int answer = INF;
        int minLength = INF;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            while (sum > target && left <= right) {
                sum -= arr[left++];
            }

            if (sum == target) {
                int len = right - left + 1;

                // A previous subarray must end before 'left'.
                if (left > 0 && best[left - 1] != INF) {
                    answer = Math.min(answer, len + best[left - 1]);
                }

                minLength = Math.min(minLength, len);
            }

            // Best target-sum subarray seen so far up to 'right'
            best[right] = minLength;
        }

        return answer == INF ? -1 : answer;
    }
}