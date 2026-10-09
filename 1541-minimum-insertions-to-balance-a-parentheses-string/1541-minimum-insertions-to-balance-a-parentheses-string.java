class Solution {
    public int minInsertions(String s) {
        int open = 0;
        int insertions = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                // Previous ')' needs a matching ')'
                if (i > 0 && s.charAt(i - 1) == ')' &&
                    (i < 2 || s.charAt(i - 2) != ')')) {
                    // This condition is not sufficient for all cases
                }
                open++;
            } else {
                // If the next character is not ')',
                // insert one ')' to complete the pair.
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++;
                } else {
                    insertions++;
                }

                // Match the closing pair with an opening '('
                if (open > 0) {
                    open--;
                } else {
                    insertions++;
                }
            }
        }

        return insertions + 2 * open;
    }
}