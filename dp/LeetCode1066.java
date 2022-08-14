/**
在由 2D 网格表示的校园里有 n 位工人（worker）和 m 辆自行车（bike），n <= m。所有工人和自行车的位置都用网格上的 2D 坐标表示。

我们为每一位工人分配一辆专属自行车，使每个工人与其分配到的自行车之间的 曼哈顿距离 最小化。

返回 每个工人与分配到的自行车之间的曼哈顿距离的最小可能总和 。

p1 和 p2 之间的 曼哈顿距离 为 Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|。

 

示例 1：



输入：workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
输出：6
解释：
自行车 0 分配给工人 0，自行车 1 分配给工人 1 。分配得到的曼哈顿距离都是 3, 所以输出为 6 。
示例 2：



输入：workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
输出：4
解释：
先将自行车 0 分配给工人 0，再将自行车 1 分配给工人 1（或工人 2），自行车 2 给工人 2（或工人 1）。如此分配使得曼哈顿距离的总和为 4。
示例 3:

输入：workers = [[0,0],[1,0],[2,0],[3,0],[4,0]], bikes = [[0,999],[1,999],[2,999],[3,999],[4,999]]
输出：4995
 

提示：

n == workers.length
m == bikes.length
1 <= n <= m <= 10
workers[i].length == 2
bikes[i].length == 2
0 <= workers[i][0], workers[i][1], bikes[i][0], bikes[i][1] < 1000
所有的工人和自行车的位置都是 不同 的。

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/campus-bikes-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
class Solution {
    int[][] workers;
    int[][] bikes;
    public int assignBikes(int[][] workers, int[][] bikes) {
        int n = bikes.length;
        this.workers = workers;
        this.bikes = bikes;
        // 当前没有被分配的自行车可以得到的曼哈顿距离最小值数组
        int[] dp = new int[1 << n];
        // 第二个参数是二进制n个0,
        // eg:n = 4 0000
        return dfs(0, 0, dp);
    }

    /**
     *
     * @param workerIndex, 还没有自行车的工人,也是递归的结束条件,所有的工人都有自行车之后,递归终止
     * @param state 表示自行车的状态,虽为十进制的值,但转为2进制更好理解
     * @param dp 当前没有被分配的自行车可以得到的曼哈顿距离最小值数组
     * @return 当前没有被分配的自行车可以得到的曼哈顿距离最小值
     */
    public int dfs(int workerIndex, int state, int[] dp) {
        if (workerIndex == workers.length) {
            return 0;
        }
        if (dp[state] != 0) {
            return dp[state];
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < bikes.length; i++) {
            // 表示第i辆自行车没有被使用
            // 二进制数字上第i位为0
            if ((state & (1 << i)) == 0) {
                min = Math.min(min, distance(workerIndex, i) + dfs(workerIndex + 1, state | (1 << i), dp));
            }
        }
        dp[state] = min;
        return min;
    }

    public int distance(int workerIndex, int bikeIndex) {
        return Math.abs(workers[workerIndex][0] - bikes[bikeIndex][0]) + Math.abs(workers[workerIndex][1] - bikes[bikeIndex][1]);
    }
}
