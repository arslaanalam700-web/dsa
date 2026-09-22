class Solution {
    static class Node {
        // Product of the whole segment modulo k
        int prod;

        // cnt[r] = number of non-empty prefixes
        // whose product % k == r
        int[] cnt;

        Node(int k) {
            cnt = new int[k];
        }
    }

    int n, k;
    int[] nums;
    Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.nums = nums;
        this.k = k;
        this.n = nums.length;

        tree = new Node[4 * n];
        build(1, 0, n - 1);

        int[] ans = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {
            int index = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            // Persistent update
            nums[index] = value;
            update(1, 0, n - 1, index, value);

            // Query nums[start..n-1]
            Node res = query(1, 0, n - 1, start, n - 1);

            ans[q] = res.cnt[x];
        }

        return ans;
    }

    // ------------------------------------------------------------
    // Build
    // ------------------------------------------------------------

    private void build(int node, int l, int r) {
        if (l == r) {
            tree[node] = makeLeaf(nums[l]);
            return;
        }

        int mid = (l + r) >>> 1;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private Node makeLeaf(int value) {
        Node res = new Node(k);

        int p = value % k;

        // The only non-empty prefix is the element itself.
        res.prod = p;
        res.cnt[p] = 1;

        return res;
    }

    // ------------------------------------------------------------
    // Merge two adjacent segments A + B
    // ------------------------------------------------------------

    private Node merge(Node A, Node B) {
        Node C = new Node(k);

        C.prod = (int) ((long) A.prod * B.prod % k);

        // Prefixes entirely inside A.
        for (int r = 0; r < k; r++) {
            C.cnt[r] += A.cnt[r];
        }

        // Prefixes consisting of all of A + a prefix of B.
        for (int s = 0; s < k; s++) {
            if (B.cnt[s] == 0) continue;

            int r = (int) ((long) A.prod * s % k);
            C.cnt[r] += B.cnt[s];
        }

        return C;
    }

    // ------------------------------------------------------------
    // Point update
    // ------------------------------------------------------------

    private void update(int node, int l, int r, int index, int value) {
        if (l == r) {
            tree[node] = makeLeaf(value);
            return;
        }

        int mid = (l + r) >>> 1;

        if (index <= mid) {
            update(node * 2, l, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, r, index, value);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // ------------------------------------------------------------
    // Range query
    // ------------------------------------------------------------

    private Node query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return tree[node];
        }

        int mid = (l + r) >>> 1;

        if (qr <= mid) {
            return query(node * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, r, ql, qr);
        }

        Node left = query(node * 2, l, mid, ql, qr);
        Node right = query(node * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }
}
