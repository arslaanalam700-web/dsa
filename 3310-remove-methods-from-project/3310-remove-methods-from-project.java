import java.util.*;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : invocations) {
            graph.get(edge[0]).add(edge[1]);
        }

        boolean[] suspicious = new boolean[n];

        // DFS to mark all suspicious methods
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(k);
        suspicious[k] = true;

        while (!stack.isEmpty()) {
            int u = stack.pop();
            for (int v : graph.get(u)) {
                if (!suspicious[v]) {
                    suspicious[v] = true;
                    stack.push(v);
                }
            }
        }

        // Check if any non-suspicious method invokes a suspicious method
        for (int[] edge : invocations) {
            int u = edge[0], v = edge[1];
            if (!suspicious[u] && suspicious[v]) {
                List<Integer> ans = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    ans.add(i);
                }
                return ans;
            }
        }

        // Return all remaining methods
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) {
                ans.add(i);
            }
        }

        return ans;
    }
}