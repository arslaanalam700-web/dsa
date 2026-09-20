class Solution {
    public int reverseDegree(String s) {
        int pro = 1;
        int sum =0;
        int a=0;
        int l=s.length();
        for (int i=0;i<l;i++){
            a = 'z' - s.charAt(i)+1;
            
            sum += a*(i+1) ;
        }
        return sum;
    }
}