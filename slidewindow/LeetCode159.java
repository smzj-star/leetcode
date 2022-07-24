/**
给定一个字符串 s ，找出 至多 包含两个不同字符的最长子串 t ，并返回该子串的长度。

示例 1:

输入: "eceba"
输出: 3
解释: t 是 "ece"，长度为3。
示例 2:

输入: "ccaabbb"
输出: 5
解释: t 是 "aabbb"，长度为5。

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/longest-substring-with-at-most-two-distinct-characters
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        char[] array = s.toCharArray();
        int len = array.length;
        int[] temp = new int[128];
        int res = 0;
        int left = 0;
        int count = 0;
        for (int right = left; right < len; right++) {
            if (temp[array[right]] == 0) {
                count++;
            }
            temp[array[right]]++;
            while (count > 2) {
                temp[array[left]]--;
                if (temp[array[left]] == 0) {
                    count--;
                }
                left++;
            }
            res = Math.max(res, right - left + 1);
        } 
        return res;
    }
}
