class Solution {
    private String s;
    private int idx;

    public List<String> braceExpansionII(String expression) {
        s = expression;
        idx = 0;

        return new ArrayList<>(parseExpression());
    }

    private Set<String> parseExpression() {
        Set<String> result = new TreeSet<>();
        result.addAll(parseTerm());

        while (idx < s.length() && s.charAt(idx) == ',') {
            idx++;
            result.addAll(parseTerm());
        }

        return result;
    }

    private Set<String> parseTerm() {
        Set<String> result = new TreeSet<>();
        result.add("");

        while (idx < s.length()
                && s.charAt(idx) != ','
                && s.charAt(idx) != '}') {

            Set<String> next = parseFactor();
            result = multiply(result, next);
        }

        return result;
    }

    private Set<String> parseFactor() {
        Set<String> result = new TreeSet<>();

        if (s.charAt(idx) == '{') {
            idx++; // '{'
            result = parseExpression();
            idx++; // '}'
        } else {
            result.add(String.valueOf(s.charAt(idx)));
            idx++;
        }

        return result;
    }

    private Set<String> multiply(Set<String> a, Set<String> b) {
        Set<String> result = new TreeSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}
