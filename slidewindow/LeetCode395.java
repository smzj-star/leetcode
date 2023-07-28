class Solution {
    public int longestSubstring(String s, int k) {
        int len = s.length();
        int result = 0;
        int maxLetterCnt = Math.min(len / k, 26);
        for (int diffLetters = 1; diffLetters <= maxLetterCnt; diffLetters++) {
            int[] letterWithCntArray = new int[26];
            int left = 0;
            int right = 0;
            int totalLetters = 0;
            int lessThanKLetterCnt = 0;
            while (right < len) {
                letterWithCntArray[s.charAt(right) - 'a']++;
                if (letterWithCntArray[s.charAt(right) - 'a'] == 1) {
                    totalLetters++;
                    lessThanKLetterCnt++;
                }
                if (letterWithCntArray[s.charAt(right) - 'a'] == k) {
                    lessThanKLetterCnt--;
                }
                while (totalLetters > diffLetters) {
                    letterWithCntArray[s.charAt(left) - 'a']--;
                    if (letterWithCntArray[s.charAt(left) - 'a'] == 0) {
                        totalLetters--;
                        lessThanKLetterCnt--;
                    }
                    if (letterWithCntArray[s.charAt(left) - 'a'] == k - 1) {
                        lessThanKLetterCnt++;
                    }
                    left++;
                }
                if (lessThanKLetterCnt == 0) {
                    result = Math.max(result, right - left + 1);
                }
                right++;
            }
        }
        return result;
    }
}
