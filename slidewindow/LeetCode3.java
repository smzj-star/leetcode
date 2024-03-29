/**
给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。

 

示例 1:

输入: s = "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
示例 2:

输入: s = "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
示例 3:

输入: s = "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 

提示：

0 <= s.length <= 5 * 104
s 由英文字母、数字、符号和空格组成

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/longest-substring-without-repeating-characters
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        char[] array = s.toCharArray();
        int len = array.length;
        int[] hash = new int[128];
        int left = 0;
        int result = 0;
        for (int right = 0; right < len; right++) {
            hash[array[right]]++;
            while (left < right && hash[array[right]] > 1) {
                hash[array[left++]]--;
            }
            result = Math.max(result, right - left + 1);
        }
        return result;
    }
}
