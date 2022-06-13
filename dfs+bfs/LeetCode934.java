//在给定的二维二进制数组 A 中，存在两座岛。（岛是由四面相连的 1 形成的一个最大组。） 
//
// 现在，我们可以将 0 变为 1，以使两座岛连接起来，变成一座岛。 
//
// 返回必须翻转的 0 的最小数目。（可以保证答案至少是 1 。） 
//
// 
//
// 示例 1： 
//
// 
//输入：A = [[0,1],[1,0]]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：A = [[0,1,0],[0,0,0],[0,0,1]]
//输出：2
// 
//
// 示例 3： 
//
// 
//输入：A = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
//输出：1 
//
// 
//
// 提示： 
//
// 
// 2 <= A.length == A[0].length <= 100 
// A[i][j] == 0 或 A[i][j] == 1 
// 
// Related Topics 深度优先搜索 广度优先搜索 数组 矩阵 👍 252 👎 0


import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    static class Pair {
        int x;
        int y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static final int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int shortestBridge(int[][] grid) {
        // DFS找到第一座岛
        int row = grid.length;
        int col = grid[0].length;
        boolean found = false;
        Deque<Pair> deque = new LinkedList<>();
        for (int i = 0; i < row && !found; i++) {
            for (int j = 0; j < col && !found; j++) {
                if (grid[i][j] == 1) {
                    // 找到所有联通的1,即第一座岛
                    dfs(deque, grid, i, j);
                    found = true;
                }
            }
        }
        if (!found) {
            return -1;
        }
        // 使用BFS开始找第二座岛
        int level = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            // 按层进行遍历
            for (int i = 0; i < size; i++) {
                Pair cur = deque.poll();
                for (int[] direction : directions) {
                    int rowIndex = cur.x + direction[0];
                    int colIndex = cur.y + direction[1];
                    if (valid(rowIndex, colIndex, grid) && grid[rowIndex][colIndex] != 2) {
                        if (grid[rowIndex][colIndex] == 1) {
                            return level;
                        } else {
                            grid[rowIndex][colIndex] = 2;
                            deque.offer(new Pair(rowIndex, colIndex));
                        }
                    }
                }
            }
            level++;
        }
        return -1;
    }

    private void dfs(Deque<Pair> deque, int[][] grid, int i, int j) {
        if (!valid(i, j, grid) || grid[i][j] != 1) {
            return;
        }
        grid[i][j] = 2;
        deque.offer(new Pair(i, j));
        for (int[] direction : directions) {
            dfs(deque, grid, i + direction[0], j + direction[1]);
        }
    }

    private boolean valid(int row, int col, int[][] grid) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
