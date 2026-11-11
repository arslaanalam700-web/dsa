
class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth,
                            int need1, int need2) {

        long both = Math.min(need1, need2);
        long remaining1 = need1 - both;
        long remaining2 = need2 - both;

        // Option 1: Use type 3 for common requirements
        long costA = both * Math.min(costBoth, cost1 + (long) cost2)
                   + remaining1 * (long) cost1
                   + remaining2 * (long) cost2;

        // Option 2: Use type 3 for all requirements
        long costB = Math.max(need1, need2) * (long) costBoth;

        return Math.min(costA, costB);
    }
}
