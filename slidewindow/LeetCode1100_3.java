/**
执行用时：2 ms, 在所有 Java 提交中击败了98.43%的用户
内存消耗：39.6 MB, 在所有 Java 提交中击败了82.68%的用户
*/
class Solution {
    public int numKLenSubstrNoRepeats(String s, int k) { 
        int len = s.length();
        if (k > len) {
            return 0;
        }
        int[] map = new int[27];
        char[] array = s.toCharArray();
        int result = 0;
        int count = 0;
        for (int i = 0; i < k; i++) {
            map[array[i] - 'a']++;
            if (map[array[i] - 'a'] == 1) {
                count++;
            }
        }
        if (count == k) {
            result++;
        }
        for (int right = k; right < len; right++) {
            map[array[right] - 'a']++;
            if (map[array[right] - 'a'] == 1) {
                count++;
            }
            map[array[right - k] - 'a']--;
            if (map[array[right - k] - 'a'] == 0) {
                count--;
            }
            if (count == k) {
                result++;
            }
        } 
        return result;
    }
}
