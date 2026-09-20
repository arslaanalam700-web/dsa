class Solution {
    public int reverseDegree(String s) {
        int sum =0;
       
        int l=s.length();
        for (int i=0;i<l;i++){
           int a = 'z' - s.charAt(i)+1;
            sum += a*(i+1) ;
        }
        return sum;
    }
}