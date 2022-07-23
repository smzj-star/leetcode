/**
给定一个字符串 s ，找出 至多 包含 k 个不同字符的最长子串 T。

 

示例 1:

输入: s = "eceba", k = 2
输出: 3
解释: 则 T 为 "ece"，所以长度为 3。
示例 2:

输入: s = "aa", k = 1
输出: 2
解释: 则 T 为 "aa"，所以长度为 2。
 

提示：

1 <= s.length <= 5 * 104
0 <= k <= 50

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/longest-substring-with-at-most-k-distinct-characters
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0) {
            return 0;
        }
        char[] array = s.toCharArray();
        int len = array.length;
        if (len == 1) {
            return 1;
        }
        int index = 0;
        List<Integer> list = new ArrayList<>();
        Set<Character> set = new HashSet<>();
        while (index < len) {
            int count = 0;
            set.clear();
            for (int i = index; i < len; i++) {
                if (!set.contains(array[i])) {
                    if (set.size() < k) {
                        set.add(array[i]);
                        count++;
                    } else {
                        index++;
                        break;
                    }
                } else {
                    count++;
                }
                if (i == len - 1) {
                    index = i + 1;
                }
            }
            list.add(count);
        }
        Collections.sort(list);
        return list.get(list.size() - 1);
    }
}
