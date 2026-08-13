class Solution {
    char[] leftChar, rightChar, s;
    int[] pre, suf, best, size;
    int n;

    public int[] longestRepeating(String sInput, String queryCharacters, int[] queryIndices) {
        s = sInput.toCharArray();
        n = s.length;
        leftChar = new char[4 * n];
        rightChar = new char[4 * n];
        pre = new int[4 * n];
        suf = new int[4 * n];
        best = new int[4 * n];
        size = new int[4 * n];

        build(1, 0, n - 1);

        int k = queryCharacters.length();
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char c = queryCharacters.charAt(i);
            s[idx] = c;
            update(1, 0, n - 1, idx, c);
            ans[i] = best[1];
        }
        return ans;
    }

    private void build(int node, int l, int r) {
        if (l == r) {
            leftChar[node] = rightChar[node] = s[l];
            pre[node] = suf[node] = best[node] = 1;
            size[node] = 1;
            return;
        }
        int mid = (l + r) / 2;
        build(2 * node, l, mid);
        build(2 * node + 1, mid + 1, r);
        merge(node, 2 * node, 2 * node + 1);
    }

    private void update(int node, int l, int r, int idx, char c) {
        if (l == r) {
            leftChar[node] = rightChar[node] = c;
            pre[node] = suf[node] = best[node] = 1;
            return;
        }
        int mid = (l + r) / 2;
        if (idx <= mid) update(2 * node, l, mid, idx, c);
        else update(2 * node + 1, mid + 1, r, idx, c);
        merge(node, 2 * node, 2 * node + 1);
    }

    private void merge(int node, int left, int right) {
        size[node] = size[left] + size[right];
        leftChar[node] = leftChar[left];
        rightChar[node] = rightChar[right];

        pre[node] = pre[left];
        if (pre[left] == size[left] && rightChar[left] == leftChar[right]) {
            pre[node] += pre[right];
        }

        suf[node] = suf[right];
        if (suf[right] == size[right] && rightChar[left] == leftChar[right]) {
            suf[node] += suf[left];
        }

        best[node] = Math.max(best[left], best[right]);
        if (rightChar[left] == leftChar[right]) {
            best[node] = Math.max(best[node], suf[left] + pre[right]);
        }
    }
}