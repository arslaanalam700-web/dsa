class Solution {
    public void reverseString(char[] s) {
     
     int n=s.length;
     int p1=0;
     int p2=n-1;
    
     while (p2>=p1)
     {
        char ch=s[p1];
        s[p1]=s[p2];
        s[p2]= ch;
        p1++;
        p2--;
     }
    }
}