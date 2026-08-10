class Solution {
    public int maxArea(int[] height) {
        int left =0;
        int max=0;
        int right=height.length-1;
       while (left<right){
        int width=right-left;
        int h=Math.min(height[left],height[right]);
            int area=h*width;
            max= Math.max(max,area);
            if (height[left]>height[right]){
                right--;
            }
            else {
                left++;
            }
       }
       return max;
    }
}