class Solution {
    public int missingInteger(int[] nums) {
        int n = nums.length;
        
        // Find length of longest sequential prefix
        int i = 1;
        while (i < n && nums[i] == nums[i - 1] + 1) {
            i++;
        }
        
        // Sum of that prefix (indices 0..i-1)
        int sum = 0;
        for (int k = 0; k < i; k++) {
            sum += nums[k];
        }
        
        // Put all values in a set for O(1) lookup
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        
        // Find smallest x >= sum not present in nums
        while (set.contains(sum)) {
            sum++;
        }
        
        return sum;
    }
}