class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
       List <Integer> list = new ArrayList <>();
       boolean[] used = new boolean[nums2.length];

        for (int num1 : nums1) {
            for (int j = 0; j < nums2.length; j++) {
                if (!used[j] && num1 == nums2[j]) {
                    list.add(num1);
                    used[j] = true;   // Mark as used
                    break;            // Stop after first match
                }
            }
        }
       int [] ans = new int[list.size()];
       int i=0;
       for (int num : list){
        ans[i++]=num;
       } 
       return ans;
    }
}