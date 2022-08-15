/**
有一些不规则的硬币。在这些硬币中，prob[i] 表示第 i 枚硬币正面朝上的概率。

请对每一枚硬币抛掷 一次，然后返回正面朝上的硬币数等于 target 的概率。

 

示例 1：

输入：prob = [0.4], target = 1
输出：0.40000
示例 2：

输入：prob = [0.5,0.5,0.5,0.5,0.5], target = 0
输出：0.03125
 

提示：

1 <= prob.length <= 1000
0 <= prob[i] <= 1
0 <= target <= prob.length
如果答案与标准答案的误差在 10^-5 内，则被视为正确答案。

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/toss-strange-coins
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
class Solution {
    public double probabilityOfHeads(double[] prob, int target) {
        int n = prob.length;
        // dp[i][j]表示抛出i个硬币之后,正面朝上的硬币个数为j的概率
        double[][] dp = new double[n + 1][target + 1];
        dp[0][0] = 1;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j <= i && j < target + 1; j++) {
                if (j == 0) {
                    // 抛出i个硬币之后,正面朝上的硬币个数为0的概率
                    // = 抛出i - 1个硬币之后,正面朝上的硬币个数为0的概率 * 当前硬币正面朝下的概率
                    dp[i][j] = dp[i - 1][j] * (1 - prob[i - 1]);
                } else {
                    // 抛出i个硬币之后,正面朝上的硬币个数为j的概率
                    // = 抛出i - 1个硬币之后,正面朝上的硬币个数为j的概率 * 当前硬币正面朝下的概率
                    // + 抛出i - 1个硬币之后,正面朝上的硬币个数为j - 1的概率 * 当前硬币正面朝上的概率
                    dp[i][j] = dp[i - 1][j] * (1 - prob[i - 1]) + dp[i - 1][j - 1] * prob[i - 1];
                }
            }
        }
        return dp[n][target];
    }
}
