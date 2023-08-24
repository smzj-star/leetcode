class Solution {
    private int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    
    public int countServers(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    int right = dfs(grid, i, j + 1, 0, 0);
                    int down = dfs(grid, i + 1, j, 1, 0);
                    int left = dfs(grid, i, j - 1, 2, 0);
                    int up = dfs(grid, i - 1, j, 3, 0);
                    if (right + down + left + up > 0) {
                        grid[i][j] = 2;
                    }
                }
            }
        }
        int result = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    result++;
                }
            }
        }
        return result;
    }

    private int dfs(int[][] grid, int row, int col, int direction, int count) {
        if (row < 0 || row == grid.length || col < 0 || col == grid[0].length) {
            return count;
        }
        if (grid[row][col] != 0) {
            count++;
            grid[row][col] = 2;
        }
        return dfs(grid, row + directions[direction][0], col + directions[direction][1], 
                direction, count);
    }
}
