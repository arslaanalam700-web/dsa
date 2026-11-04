class Solution {
    public int minMoves(int[] nums) {
        Arrays.sort(nums);
        int n=nums.length;
        int count =0;
        int max=nums[n-1];
        for (int i=0;i<n;i++){
            if(nums[i]<max){
                count = count + (max-nums[i]);
            }
        }
        return count;
    }
}