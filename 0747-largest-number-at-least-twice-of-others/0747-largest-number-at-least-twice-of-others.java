class Solution {
    public int dominantIndex(int[] nums) {
      int max=-1;
      int secmax=-1;
      int idx=-1;
      for (int i=0;i<nums.length;i++){
        if(nums[i]>max) {
            secmax=max;
            max=nums[i];
            idx=i;
        }
        else if (nums[i]>secmax){
            secmax = nums[i];
        }
      }
      return max>= 2*secmax ? idx:-1;
    }
}