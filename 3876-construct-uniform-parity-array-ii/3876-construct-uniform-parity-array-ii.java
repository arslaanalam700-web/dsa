class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;
        int minEven = Integer.MAX_VALUE;

        for (int x : nums1) {
            if (x % 2 == 0) {
                minEven = Math.min(minEven, x);
            } else {
                minOdd = Math.min(minOdd, x);
            }
        }

        // Saare numbers even hain
        if (minOdd == Integer.MAX_VALUE) {
            return true;
        }

        // Har even number ko smallest odd se subtract
        // karke odd banaya ja sakta hai
        return minOdd < minEven;
    }
}
