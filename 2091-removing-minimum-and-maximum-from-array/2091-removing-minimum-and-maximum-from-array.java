class Solution {
    public int minimumDeletions(int[] nums) {
        int n= nums.length;
        int max=0;
        int min=0;
        for (int i=0;i<n;i++){
            if (nums[i]<nums[min]){
                min = i;
            }
            if (nums[i]>nums[max]){
                max=i;
            }
        }
        int left = Math.min(max,min);
        int right = Math.max(min,max);

        int fromfront = right+1;
        int fromback = n - left;
        int fromboth = (left + 1)+(n - right);
         return Math.min(fromfront , Math.min(fromback,fromboth));
    }
}
