
import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        // Sort logs by timestamp
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        int m = queries.length;

        // Store queries with their original indices
        int[][] q = new int[m][2];

        for (int i = 0; i < m; i++) {
            q[i][0] = queries[i];
            q[i][1] = i;
        }

        // Sort queries by time
        Arrays.sort(q, (a, b) -> Integer.compare(a[0], b[0]));

        int[] freq = new int[n + 1];
        int[] ans = new int[m];

        int left = 0;
        int right = 0;
        int active = 0;

        for (int[] query : q) {

            int t = query[0];
            int idx = query[1];

            int start = t - x;

            // Add logs with timestamp <= t
            while (right < logs.length && logs[right][1] <= t) {

                int id = logs[right][0];

                if (freq[id] == 0) {
                    active++;
                }

                freq[id]++;
                right++;
            }

            // Remove logs with timestamp < start
            while (left < right && logs[left][1] < start) {

                int id = logs[left][0];

                freq[id]--;

                if (freq[id] == 0) {
                    active--;
                }

                left++;
            }

            // Servers with zero requests
            ans[idx] = n - active;
        }

        return ans;
    }
}
