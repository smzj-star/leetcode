/**
执行用时：6 ms, 在所有 Java 提交中击败了82.37%的用户
内存消耗：41.2 MB, 在所有 Java 提交中击败了92.29%的用户
通过测试用例：142 / 142
*/
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        char[] array = s.toCharArray();
        int len = s.length();
        int count = 0;
        int result = 0;
        for (int right = 0; right < len; right++) {
            map.put(array[right], map.getOrDefault(array[right], 0) + 1);
            if (map.get(array[right]) == 1) {
                count++;
            }
            while (count > k) {
                map.put(array[left], map.get(array[left]) - 1);
                if (map.get(array[left]) == 0) {
                    count--;
                }
                left++;
            }
            result = Math.max(result, right - left + 1);
        }
        return result;
    }
}
