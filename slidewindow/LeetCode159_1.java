/**
执行用时：4 ms, 在所有 Java 提交中击败了96.95%的用户
内存消耗：41.7 MB, 在所有 Java 提交中击败了94.20%的用户
*/
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int[] map = new int[128];
        char[] array = s.toCharArray();
        int len = s.length();
        int left = 0;
        int count = 0;
        int result = 0;
        for (int right = 0; right < len; right++) {
            map[array[right] - '0']++;
            if (map[array[right] - '0'] == 1) {
                count++;
            }
            while (count > 2) {
                map[array[left] - '0']--;
                if (map[array[left] - '0'] == 0) {
                    count--;
                }
                left++;
            }
            result = Math.max(result, right - left + 1);
        }
        return result;
    }
}
