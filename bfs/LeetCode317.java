/**
给你一个 m × n 的网格，值为 0 、 1 或 2 ，其中:

每一个 0 代表一块你可以自由通过的 空地 
每一个 1 代表一个你不能通过的 建筑
每个 2 标记一个你不能通过的 障碍 
你想要在一块空地上建造一所房子，在 最短的总旅行距离 内到达所有的建筑。你只能上下左右移动。

返回到该房子的 最短旅行距离 。如果根据上述规则无法建造这样的房子，则返回 -1 。

总旅行距离 是朋友们家到聚会地点的距离之和。

使用 曼哈顿距离 计算距离，其中距离 (p1, p2) = |p2.x - p1.x | + | p2.y - p1.y | 。

 

示例  1：



输入：grid = [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
输出：7 
解析：给定三个建筑物 (0,0)、(0,4) 和 (2,2) 以及一个位于 (0,2) 的障碍物。
由于总距离之和 3+3+1=7 最优，所以位置 (1,2) 是符合要求的最优地点。
故返回7。
示例 2:

输入: grid = [[1,0]]
输出: 1
示例 3:

输入: grid = [[1]]
输出: -1
 

提示:

m == grid.length
n == grid[i].length
1 <= m, n <= 50
grid[i][j] 是 0, 1 或 2
grid 中 至少 有 一幢 建筑

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/shortest-distance-from-all-buildings
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
class Solution {
    private int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int shortestDistance(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        // 每一个空地到所有建筑物的距离之和
        int[][] distances = new int[rows][cols];
        // 每一个空地能到达的建筑物的数量
        int[][] canReaches = new int[rows][cols];
        // 所有建筑物的数量
        int totalBulidings = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    totalBulidings++;
                    if (!bfs(i, j, grid, distances, canReaches)) {
                        return -1;
                    }
                }
            }
        }
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (canReaches[i][j] == totalBulidings) {
                    minDistance = Math.min(minDistance, distances[i][j]);
                }
            }
        }
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

    private boolean bfs(int row, int col, int[][] grid, int[][] distances, int[][] canReaches) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Deque<int[]> deque = new LinkedList<>();
        deque.offer(new int[]{row, col});
        visited[row][col] = true;
        int dist = 0;
        while (!deque.isEmpty()) {
            dist++;
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                int[] cur = deque.poll();
                for (int[] direction : directions) {
                    int rowIndex = cur[0] + direction[0];
                    int colIndex = cur[1] + direction[1];
                    if (isValid(rowIndex, colIndex, grid, visited)) {
                        visited[rowIndex][colIndex] = true;
                        if (grid[rowIndex][colIndex] == 0) {
                            distances[rowIndex][colIndex] += dist;
                            canReaches[rowIndex][colIndex]++;
                            deque.offer(new int[]{rowIndex, colIndex});
                        }
                    }
                }
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 如果存在建筑物不可达,即被一群障碍物包围,剪枝
                if (grid[i][j] == 1 && !visited[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int row, int col, int[][] grid, boolean[][] visited) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length 
        && !visited[row][col] && grid[row][col] != 2;
    }
}
