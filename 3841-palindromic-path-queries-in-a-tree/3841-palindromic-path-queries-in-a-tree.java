import java.util.*;

class Solution {
    int n, LOG = 17;
    int[] tin, tout, depth, base, bit;
    int[][] up;

    void upd(int i, int v) { for (; i <= n + 1; i += i & -i) bit[i] ^= v; }
    int qry(int i) { int r = 0; for (; i > 0; i -= i & -i) r ^= bit[i]; return r; }

    int lca(int a, int b) {
        if (depth[a] < depth[b]) { int t = a; a = b; b = t; }
        int diff = depth[a] - depth[b];
        for (int k = 0; k < LOG; k++) if (((diff >> k) & 1) != 0) a = up[k][a];
        if (a == b) return a;
        for (int k = LOG - 1; k >= 0; k--) {
            if (up[k][a] != up[k][b]) { a = up[k][a]; b = up[k][b]; }
        }
        return up[0][a];
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        char[] cs = s.toCharArray();

        int[] head = new int[n], nxt = new int[2 * (n - 1)], to = new int[2 * (n - 1)];
        Arrays.fill(head, -1);
        int ec = 0;
        for (int[] e : edges) {
            to[ec] = e[1]; nxt[ec] = head[e[0]]; head[e[0]] = ec++;
            to[ec] = e[0]; nxt[ec] = head[e[1]]; head[e[1]] = ec++;
        }

        tin = new int[n]; tout = new int[n]; depth = new int[n]; base = new int[n];
        bit = new int[n + 3];
        up = new int[LOG][n];

        int[] stack = new int[n], it = new int[n];
        int sp = 0, timer = 0;
        stack[sp++] = 0;
        up[0][0] = 0;
        tin[0] = ++timer;
        base[0] = 1 << (cs[0] - 'a');
        for (int i = 0; i < n; i++) it[i] = head[i];

        while (sp > 0) {
            int v = stack[sp - 1];
            if (it[v] != -1) {
                int e = it[v];
                it[v] = nxt[e];
                int w = to[e];
                if (v != 0 && w == up[0][v]) continue;
                up[0][w] = v;
                depth[w] = depth[v] + 1;
                base[w] = base[v] ^ (1 << (cs[w] - 'a'));
                tin[w] = ++timer;
                stack[sp++] = w;
            } else {
                tout[v] = timer;
                sp--;
            }
        }

        for (int k = 1; k < LOG; k++)
            for (int v = 0; v < n; v++)
                up[k][v] = up[k - 1][up[k - 1][v]];

        List<Boolean> res = new ArrayList<>();
        for (String q : queries) {
            String[] p = q.split(" ");
            if (p[0].equals("update")) {
                int u = Integer.parseInt(p[1]);
                char c = p[2].charAt(0);
                if (cs[u] != c) {
                    int delta = (1 << (cs[u] - 'a')) ^ (1 << (c - 'a'));
                    upd(tin[u], delta);
                    upd(tout[u] + 1, delta);
                    cs[u] = c;
                }
            } else {
                int u = Integer.parseInt(p[1]), v = Integer.parseInt(p[2]);
                int l = lca(u, v);
                int pu = base[u] ^ qry(tin[u]);
                int pv = base[v] ^ qry(tin[v]);
                int mask = pu ^ pv ^ (1 << (cs[l] - 'a'));
                res.add((mask & (mask - 1)) == 0);
            }
        }
        return res;
    }
}