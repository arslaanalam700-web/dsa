class Solution {
    public int minOperations(String s) {
        int n = s.length();
        char mn = 'z', mx = 'a';
        boolean sorted = true;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c < mn) mn = c;
            if (c > mx) mx = c;
            if (i > 0 && s.charAt(i - 1) > c) sorted = false;
        }

        if (sorted) return 0;
        if (n == 2) return -1;                 // only length-1 substrings are allowed, which do nothing

        // first char is the global min, or last char is the global max
        if (s.charAt(0) == mn || s.charAt(n - 1) == mx) return 1;

        // a global min or max sits strictly inside the string
        for (int i = 1; i < n - 1; i++) {
            char c = s.charAt(i);
            if (c == mn || c == mx) return 2;
        }

        return 3;
    }
}