
import java.util.*;

class Solution {
    List<Integer>[] children;
    int[] last;
    int[] newParent;
    int[] ans;
    int[] parent;
    char[] s;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        this.parent = parent;
        this.s = s.toCharArray();

        children = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }

        // Build original tree
        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }

        last = new int[26];
        Arrays.fill(last, -1);

        newParent = new int[n];
        ans = new int[n];

        // Find new parent for every node
        dfs(0);

        // Build modified tree
        for (int i = 0; i < n; i++) {
            children[i].clear();
        }

        for (int i = 1; i < n; i++) {
            children[newParent[i]].add(i);
        }

        // Calculate subtree sizes
        calculateSize(0);

        return ans;
    }

    private void dfs(int node) {
        int c = s[node] - 'a';

        int previous = last[c];

        // Closest matching ancestor, or original parent
        newParent[node] = (previous == -1)
                ? parent[node]
                : previous;

        last[c] = node;

        for (int child : children[node]) {
            dfs(child);
        }

        // Backtrack
        last[c] = previous;
    }

    private int calculateSize(int node) {
        int size = 1;

        for (int child : children[node]) {
            size += calculateSize(child);
        }

        return ans[node] = size;
    }
}
