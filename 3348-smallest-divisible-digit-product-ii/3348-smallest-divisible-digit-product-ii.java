class Solution {
    public String smallestNumber(String num, long t) {
        int a = 0, b = 0, c = 0, d = 0;
        while (t % 2 == 0) { a++; t /= 2; }
        while (t % 3 == 0) { b++; t /= 3; }
        while (t % 5 == 0) { c++; t /= 5; }
        while (t % 7 == 0) { d++; t /= 7; }
        if (t != 1) return "-1";

        int[] eA = {0,0,1,0,2,0,1,0,3,0};
        int[] eB = {0,0,0,1,0,0,1,0,0,2};
        int[] eC = {0,0,0,0,0,1,0,0,0,0};
        int[] eD = {0,0,0,0,0,0,0,1,0,0};

        int[][] minAB = new int[a + 1][b + 1];
        for (int ra = 0; ra <= a; ra++) {
            for (int rb = 0; rb <= b; rb++) {
                int best = Integer.MAX_VALUE;
                int mx = Math.min(ra, rb);
                for (int x6 = 0; x6 <= mx; x6++) {
                    int val = x6 + ceilDiv(ra - x6, 3) + ceilDiv(rb - x6, 2);
                    best = Math.min(best, val);
                }
                minAB[ra][rb] = best;
            }
        }

        int n = num.length();
        char[] digits = num.toCharArray();

        int zMin = n;
        for (int i = 0; i < n; i++) if (digits[i] == '0') { zMin = i; break; }

        int[] pA = new int[zMin + 1], pB = new int[zMin + 1], pC = new int[zMin + 1], pD = new int[zMin + 1];
        for (int i = 0; i < zMin; i++) {
            int g = digits[i] - '0';
            pA[i+1] = pA[i] + eA[g];
            pB[i+1] = pB[i] + eB[g];
            pC[i+1] = pC[i] + eC[g];
            pD[i+1] = pD[i] + eD[g];
        }

        if (zMin == n && pA[n] >= a && pB[n] >= b && pC[n] >= c && pD[n] >= d) {
            return num;
        }

        int limit = Math.min(zMin, n - 1);
        for (int i = limit; i >= 0; i--) {
            int curDigit = digits[i] - '0';
            for (int dp = curDigit + 1; dp <= 9; dp++) {
                int aTot = pA[i] + eA[dp], bTot = pB[i] + eB[dp];
                int cTot = pC[i] + eC[dp], dTot = pD[i] + eD[dp];
                int ra = Math.max(a - aTot, 0), rb = Math.max(b - bTot, 0);
                int rc = Math.max(c - cTot, 0), rd = Math.max(d - dTot, 0);
                int remLen = n - 1 - i;
                if (minAB[ra][rb] + rc + rd <= remLen) {
                    char[] res = digits.clone();
                    res[i] = (char) ('0' + dp);
                    fill(res, i + 1, remLen, ra, rb, rc, rd, eA, eB, eC, eD, minAB);
                    return new String(res);
                }
            }
        }

        int minNeeded = minAB[a][b] + c + d;
        int L = Math.max(n + 1, minNeeded);
        char[] res = new char[L];
        fill(res, 0, L, a, b, c, d, eA, eB, eC, eD, minAB);
        return new String(res);
    }

    private void fill(char[] res, int start, int len, int ra, int rb, int rc, int rd,
                       int[] eA, int[] eB, int[] eC, int[] eD, int[][] minAB) {
        for (int pos = 0; pos < len; pos++) {
            int remLen = len - pos - 1;
            for (int g = 1; g <= 9; g++) {
                int na = Math.max(ra - eA[g], 0), nb = Math.max(rb - eB[g], 0);
                int nc = Math.max(rc - eC[g], 0), nd = Math.max(rd - eD[g], 0);
                if (minAB[na][nb] + nc + nd <= remLen) {
                    res[start + pos] = (char) ('0' + g);
                    ra = na; rb = nb; rc = nc; rd = nd;
                    break;
                }
            }
        }
    }

    private int ceilDiv(int x, int k) {
        return x <= 0 ? 0 : (x + k - 1) / k;
    }
}