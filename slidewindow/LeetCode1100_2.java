/**
给你一个字符串 S，找出所有长度为 K 且不含重复字符的子串，请你返回全部满足要求的子串的 数目。

 

示例 1：

输入：S = "havefunonleetcode", K = 5
输出：6
解释：
这里有 6 个满足题意的子串，分别是：'havef','avefu','vefun','efuno','etcod','tcode'。
示例 2：

输入：S = "home", K = 5
输出：0
解释：
注意：K 可能会大于 S 的长度。在这种情况下，就无法找到任何长度为 K 的子串。
 

提示：

1 <= S.length <= 10^4
S 中的所有字符均为小写英文字母
1 <= K <= 10^4

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/find-k-length-substrings-with-no-repeated-characters
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
class Solution {
    public int numKLenSubstrNoRepeats(String s, int k) {
        char[] array = s.toCharArray();
        int len = array.length;
        if (len < k) {
            return 0;
        }
        int res = 0;
        int left = 0;
        int[] temp = new int[128];
        for (int right = 0; right < len; right++) {
            temp[array[right]]++;
            if (temp[array[right]] == 1 && right - left + 1 == k) {
                temp[array[left]]--;
                left++;
                res++;
                continue;
            }
            while (temp[array[right]] > 1) {
                temp[array[left]]--;
                left++;
            }
        }
        return res;
    }
}
