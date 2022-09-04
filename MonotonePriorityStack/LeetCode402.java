/**
给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。请你以字符串形式返回这个最小的数字。

 
示例 1 ：

输入：num = "1432219", k = 3
输出："1219"
解释：移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219 。
示例 2 ：

输入：num = "10200", k = 1
输出："200"
解释：移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
示例 3 ：

输入：num = "10", k = 2
输出："0"
解释：从原数字移除所有的数字，剩余为空就是 0 。
 

提示：

1 <= k <= num.length <= 105
num 仅由若干位数字（0 - 9）组成
除了 0 本身之外，num 不含任何前导零

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/remove-k-digits
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
class Solution {
    public String removeKdigits(String num, int k) {
        Deque<Character> stack = new LinkedList<>();
        int length = num.length();
        // 结果字符串的字符个数
        int len = length - k;
        for (int i = 0; i < length; i++) {
            char c = num.charAt(i);
            while (!stack.isEmpty() && c < stack.peek() && k > 0) {
                k--;
                stack.pop();
            }
            stack.push(c);
        }
        StringBuilder result = new StringBuilder();
        // 该循环主要是适配k>0的场景
        // eg1: num = 1173; k = 2
        // eg2: num = 10001; k = 4
        for (int i = 0; i < len; i++) {
            // 去除前导0
            // eg: num = 10200, k = 1
            if (result.length() == 0 && stack.peekLast() == '0') {
                stack.removeLast();
                continue;
            }
            result.append(stack.removeLast());
        }
        return result.length() == 0 ? "0" : result.toString();
    }
}
