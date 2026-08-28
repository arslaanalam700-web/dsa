class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) count[c - 'a']++;
        
        int oddCount = 0, oddChar = -1;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 == 1) {
                oddCount++;
                oddChar = i;
            }
        }
        if (oddCount > 1) return "";
        
        int half = n / 2;
        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) halfCount[i] = count[i] / 2;
        
        char[] chosen = new char[half];
        String result = backtrackHelper(0, half, n, oddChar, halfCount, true, chosen, target);
        return result == null ? "" : result;   // <-- fix: convert null to ""
    }
    
    private String backtrackHelper(int pos, int half, int n, int oddChar, int[] cnt, boolean tight, char[] chosen, String target) {
        if (pos == half) {
            String candidate = buildPalindrome(chosen, half, n, oddChar);
            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
            return null;
        }
        
        char targetChar = pos < target.length() ? target.charAt(pos) : 'a';
        int startC = tight ? (targetChar - 'a') : 0;
        
        for (int c = startC; c < 26; c++) {
            if (cnt[c] <= 0) continue;
            boolean newTight = tight && (c == targetChar - 'a');
            
            chosen[pos] = (char) ('a' + c);
            cnt[c]--;
            
            String res = backtrackHelper(pos + 1, half, n, oddChar, cnt, newTight, chosen, target);
            
            cnt[c]++;
            
            if (res != null) return res;
        }
        
        return null;
    }
    
    private String buildPalindrome(char[] chosen, int half, int n, int oddChar) {
        char[] result = new char[n];
        for (int i = 0; i < half; i++) {
            result[i] = chosen[i];
            result[n - 1 - i] = chosen[i];
        }
        if (n % 2 == 1) {
            result[half] = (char) ('a' + oddChar);
        }
        return new String(result);
    }
}