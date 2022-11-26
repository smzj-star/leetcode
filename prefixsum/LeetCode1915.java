/**
如果某个字符串中 至多一个 字母出现 奇数 次，则称其为 最美 字符串。

例如，"ccjjc" 和 "abab" 都是最美字符串，但 "ab" 不是。
给你一个字符串 word ，该字符串由前十个小写英文字母组成（'a' 到 'j'）。请你返回 word 中 最美非空子字符串 的数目。如果同样的子字符串在 word 中出现多次，那么应当对 每次出现 分别计数。

子字符串 是字符串中的一个连续字符序列。

 

示例 1：

输入：word = "aba"
输出：4
解释：4 个最美子字符串如下所示：
- "aba" -> "a"
- "aba" -> "b"
- "aba" -> "a"
- "aba" -> "aba"
示例 2：

输入：word = "aabb"
输出：9
解释：9 个最美子字符串如下所示：
- "aabb" -> "a"
- "aabb" -> "aa"
- "aabb" -> "aab"
- "aabb" -> "aabb"
- "aabb" -> "a"
- "aabb" -> "abb"
- "aabb" -> "b"
- "aabb" -> "bb"
- "aabb" -> "b"
示例 3：

输入：word = "he"
输出：2
解释：2 个最美子字符串如下所示：
- "he" -> "h"
- "he" -> "e"
 

提示：

1 <= word.length <= 105
word 由从 'a' 到 'j' 的小写英文字母组成

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/number-of-wonderful-substrings
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
class Solution {
    public long wonderfulSubstrings(String word) {
        // hash[i] 表示前缀和出现的次数
        int[] hash = new int[1024];
        // 全量word字符串是一个【只有一个奇数字符】的最美字符串
        hash[0] = 1;
        char[] array = word.toCharArray();
        int len = array.length;
        long result = 0;
        int pre = 0;
        for (int i = 0; i < len; i++) {
           pre ^= (1 << (array[i] - 'a')); 
           // 情况一:子字符串只有偶数个字符 
           // 当前缀和再次出现的时候，说明两次前缀和之间出现了子字符串为偶数个字符
           result += hash[pre];
           hash[pre]++;
            // 情况二:子字符串只有一个奇数个字符
            // 该前缀和 和 另一个前缀和 之间的子字符串只有一个奇数个字符
            // 不知哪一个为奇数个字符(a-j均有可能,因此需要遍历)
            for (int j = 0; j < 10; j++) {
                result += hash[pre ^ (1 << j)];
            }
        }
        return result;
    }
}
