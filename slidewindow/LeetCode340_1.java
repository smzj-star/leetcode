class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        char[] array = s.toCharArray();
        int len = array.length;
        int diffCharCount = 0;
        int[] hash = new int[128];
        int left = 0;
        int result = 0;
        for (int right = 0; right < len; right++) {
            hash[array[right]]++;
            if (hash[array[right]] == 1) {
                diffCharCount++;
            }
            while (diffCharCount > k) {
                hash[array[left]]--;
                if (hash[array[left]] == 0) {
                    diffCharCount--;
                }
                left++;
            }
            result = Math.max(result, right - left + 1);
        }
        return result;
    }
}
