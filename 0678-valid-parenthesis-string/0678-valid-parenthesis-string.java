class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0; // Minimum possible unmatched '('
        int maxOpen = 0; // Maximum possible unmatched '('

        for (char c : s.toCharArray()) {
            if (c == '(') {
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
                minOpen--;
                maxOpen--;
            } else { // '*'
                minOpen--; // '*' can be ')'
                maxOpen++; // '*' can be '('
            }

            // Too many ')' even in the best case
            if (maxOpen < 0) {
                return false;
            }

            // Minimum cannot be negative
            minOpen = Math.max(0, minOpen);
        }

        // Valid only if we can have exactly 0 unmatched '('
        return minOpen == 0;
    }
}
