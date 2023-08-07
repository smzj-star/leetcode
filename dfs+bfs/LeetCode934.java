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

class Solution {
    private int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    
    public int shortestBridge(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Deque<int[]> deque = new LinkedList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    dfs(deque, grid, i, j);
                    return bfs(grid, deque);
                }
            }
        }
        return 0;
    }

    private int bfs(int[][] grid, Deque<int[]> deque) {
        int step = 0;
        while (!deque.isEmpty()) {
            step++;
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                int[] cur = deque.poll();
                for (int[] direction : directions) {
                    int rowIndex = cur[0] + direction[0];
                    int colIndex = cur[1] + direction[1];
                    if (isValid(grid, rowIndex, colIndex)) {
                        if (grid[rowIndex][colIndex] == 1) {
                            return step - 1;
                        }
                        deque.offer(new int[]{rowIndex, colIndex});
                        grid[rowIndex][colIndex] = 2;
                    }
                }
            }
        }
        return step;
    }

    private boolean isValid(int[][] grid, int rowIndex, int colIndex) {
        return rowIndex >= 0 && rowIndex < grid.length 
                && colIndex >= 0 && colIndex < grid[0].length && grid[rowIndex][colIndex] != 2;
    }

    private void dfs(Deque<int[]> deque, int[][] grid, int row, int col) {
        if (row < 0 || row == grid.length || col < 0 || col == grid[0].length || grid[row][col] != 1) {
            return;
        }
        deque.offer(new int[]{row, col});
        grid[row][col] = 2;
        dfs(deque, grid, row, col + 1);
        dfs(deque, grid, row + 1, col);
        dfs(deque, grid, row, col - 1);
        dfs(deque, grid, row - 1, col);
    }
}
