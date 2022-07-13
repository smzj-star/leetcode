/**
给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。

岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。

此外，你可以假设该网格的四条边均被水包围。

 

示例 1：

输入：grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
输出：1
示例 2：

输入：grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
输出：3
 

提示：

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] 的值为 '0' 或 '1'

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/number-of-islands
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
class Solution {
    private static class Pair {
        int x;
        int y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int numIslands(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        boolean[][] visited = new boolean[row][col];
        Deque<Pair> deque = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    deque.offer(new Pair(i, j));
                    visited[i][j] = true;
                    while (!deque.isEmpty()) {
                        int size = deque.size();
                        for (int k = 0; k < size; k++) {
                            Pair cur = deque.poll();
                            for (int[] direction : directions) {
                                int rowIndex = cur.x + direction[0];
                                int colIndex = cur.y + direction[1];
                                if (isValid(rowIndex, colIndex, grid) && !visited[rowIndex][colIndex] && grid[rowIndex][colIndex] == '1') {
                                    deque.offer(new Pair(rowIndex, colIndex));
                                    visited[rowIndex][colIndex] = true; 
                                }
                            }
                        }
                    }
                    count++;
                }
            }
        }
        return count; 
    }

    private boolean isValid(int r, int c, char[][] grid) {
        return r >=0 && r < grid.length && c >= 0 && c < grid[0].length;
    }
}
