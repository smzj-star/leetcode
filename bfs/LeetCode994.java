/**
时间:详情1ms 击败 100.00%使用 Java 的用户
内存:详情39.38mb 击败 47.39%使用 Java 的用户
*/
class Solution {
    private int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        // 表示腐烂的橘子个数
        int rotedOranges = 0;
        // 表示新鲜的橘子个数
        int freshOranges = 0;
        Deque<int[]> deque = new LinkedList<>();
        // 注意辅助队列中初始元素不一定只有一个
        // 辅助队列中存放的是腐烂的橘子的坐标
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    freshOranges++;
                }
                if (grid[i][j] == 2) {
                    rotedOranges++;
                    deque.offer(new int[]{i, j});
                }
            }
        }
        // 所有的橘子 = 新鲜的橘子 + 腐烂的橘子
        int totalOranges = freshOranges + rotedOranges;
        // 场景三：初始化时，新鲜的橘子个数为0，返回0
        if (freshOranges == 0) {
            return 0;
        }
        int minutes = 0;
        while (!deque.isEmpty() && rotedOranges != totalOranges) {
            minutes++;
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                int[] cur = deque.poll();
                for (int[] direction : directions) {
                    int rowIndex = cur[0] + direction[0];
                    int colIndex = cur[1] + direction[1];
                    if (isValid(rowIndex, colIndex, grid)) {
                        grid[rowIndex][colIndex] = 2;
                        deque.offer(new int[]{rowIndex, colIndex});
                        rotedOranges++;
                    }
                }
            }
        }
        return rotedOranges == totalOranges ? minutes : -1;
    }

    private boolean isValid(int rowIndex, int colIndex, int[][] grid) {
        return rowIndex >= 0 && rowIndex < grid.length && colIndex >= 0 && colIndex < grid[0].length && grid[rowIndex][colIndex] == 1;
    }
}
