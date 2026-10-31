class Solution {
    public int uniquePaths(int[][] grid) {
        final int MOD = 1_000_000_007;
        int m = grid.length, n = grid[0].length;

        // R[i][j]: ways of attempting to enter (i,j) while moving right
        // D[i][j]: ways of attempting to enter (i,j) while moving down
        int[][] R = new int[m + 1][n + 1];
        int[][] D = new int[m + 1][n + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    long f = (i == 0 && j == 0) ? 1 : ((long) R[i][j] + D[i][j]) % MOD;
                    if (i == m - 1 && j == n - 1) {
                        return (int) f;
                    }
                    if (j + 1 < n) {
                        R[i][j + 1] = (int) ((R[i][j + 1] + f) % MOD);
                    }
                    if (i + 1 < m) {
                        D[i + 1][j] = (int) ((D[i + 1][j] + f) % MOD);
                    }
                } else {
                    // entered moving right -> turned down, lands below moving down
                    if (i + 1 < m) {
                        D[i + 1][j] = (int) (((long) D[i + 1][j] + R[i][j]) % MOD);
                    }
                    // entered moving down -> turned right, lands to the right moving right
                    if (j + 1 < n) {
                        R[i][j + 1] = (int) (((long) R[i][j + 1] + D[i][j]) % MOD);
                    }
                }
            }
        }
        return 0; // unreachable since the last cell is always empty
    }
}