class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;
            if (check(nums, n, k)) ans += k;
        }
        return ans;
    }

    private boolean check(int[] a, int n, int k) {
        int prevMax = Integer.MIN_VALUE;
        for (int s = 0; s < n; s += k) {
            int e = s + k - 1;
            int cnt = 0;
            int mn = a[s], mx = a[s];
            for (int i = s; i < e; i++) {
                if (a[i] > a[i + 1]) cnt++;
                mn = Math.min(mn, a[i + 1]);
                mx = Math.max(mx, a[i + 1]);
            }
            // cyclic wrap-around
            if (a[e] > a[s]) cnt++;
            if (cnt > 1) return false;
            // after rotating, this block is sorted: min first, max last
            if (prevMax > mn) return false;
            prevMax = mx;
        }
        return true;
    }
}