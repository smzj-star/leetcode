/**
「推箱子」是一款风靡全球的益智小游戏，玩家需要将箱子推到仓库中的目标位置。

游戏地图用大小为 m x n 的网格 grid 表示，其中每个元素可以是墙、地板或者是箱子。

现在你将作为玩家参与游戏，按规则将箱子 'B' 移动到目标位置 'T' ：

玩家用字符 'S' 表示，只要他在地板上，就可以在网格中向上、下、左、右四个方向移动。
地板用字符 '.' 表示，意味着可以自由行走。
墙用字符 '#' 表示，意味着障碍物，不能通行。 
箱子仅有一个，用字符 'B' 表示。相应地，网格上有一个目标位置 'T'。
玩家需要站在箱子旁边，然后沿着箱子的方向进行移动，此时箱子会被移动到相邻的地板单元格。记作一次「推动」。
玩家无法越过箱子。
返回将箱子推到目标位置的最小 推动 次数，如果无法做到，请返回 -1。

 

示例 1：



输入：grid = [["#","#","#","#","#","#"],
             ["#","T","#","#","#","#"],
             ["#",".",".","B",".","#"],
             ["#",".","#","#",".","#"],
             ["#",".",".",".","S","#"],
             ["#","#","#","#","#","#"]]
输出：3
解释：我们只需要返回推箱子的次数。
示例 2：

输入：grid = [["#","#","#","#","#","#"],
             ["#","T","#","#","#","#"],
             ["#",".",".","B",".","#"],
             ["#","#","#","#",".","#"],
             ["#",".",".",".","S","#"],
             ["#","#","#","#","#","#"]]
输出：-1
示例 3：

输入：grid = [["#","#","#","#","#","#"],
             ["#","T",".",".","#","#"],
             ["#",".","#","B",".","#"],
             ["#",".",".",".",".","#"],
             ["#",".",".",".","S","#"],
             ["#","#","#","#","#","#"]]
输出：5
解释：向下、向左、向左、向上再向上。
 

提示：

m == grid.length
n == grid[i].length
1 <= m, n <= 20
grid 仅包含字符 '.', '#',  'S' , 'T', 以及 'B'。
grid 中 'S', 'B' 和 'T' 各只能出现一个。

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/minimum-moves-to-move-a-box-to-their-target-location
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
class Solution {
    private int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private int[] targetLocation = new int[2];
    public int minPushBox(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[] start = new int[4];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 'B') {
                    // 盒子的横坐标
                    start[0] = i;
                    // 盒子的纵坐标
                    start[1] = j;
                } else if (grid[i][j] == 'S') {
                    // 人的横坐标
                    start[2] = i;
                    // 人的纵坐标
                    start[3] = j;
                } else if (grid[i][j] == 'T') {
                    targetLocation[0] = i;
                    targetLocation[1] = j;
                }
            }
        }
        return bfs(start, grid);
    }

    private int bfs(int[] start, char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Deque<int[]> deque = new LinkedList<>();
        deque.offer(start);
        boolean[][][][] visited = new boolean[rows][cols][rows][cols];
        visited[start[0]][start[1]][start[2]][start[3]] = true;
        int step = 0;
        while (!deque.isEmpty()) {
            step++;
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                int[] cur = deque.poll();
                // 把箱子当前所在的位置变成箱子
                grid[cur[0]][cur[1]] = 'B';
                for (int[] direction : directions) {
                    int rowIndex = cur[0] + direction[0];
                    int colIndex = cur[1] + direction[1];
                    // 初步判断箱子能否推动
                    if (rowIndex < 0 || rowIndex == rows || colIndex < 0 || colIndex == cols || visited[rowIndex][colIndex][cur[0]][cur[1]] || grid[rowIndex][colIndex] == '#') {
                        continue;
                    }
                    // 考虑人能否到达箱子背后进行推动箱子
                    if (!canReachBoxBehind(grid, cur[2], cur[3], cur[0] - direction[0], cur[1] - direction[1])) {
                        continue;
                    }
                    if (rowIndex == targetLocation[0] && colIndex == targetLocation[1]) {
                        return step;
                    }
                    deque.offer(new int[]{rowIndex, colIndex, cur[0], cur[1]});
                    visited[rowIndex][colIndex][cur[0]][cur[1]] = true;
                }
                // 箱子挪动之后,箱子变成地板
                grid[cur[0]][cur[1]] = '.';
            }
        }
        return -1;
    }

    private boolean canReachBoxBehind(char[][] grid, int peopleX, int peopleY, int boxBehindX, int boxBehindY) {
        // 拆分点1：箱子背后必须是人能够站的地方
        if (boxBehindX < 0 || boxBehindX == grid.length || boxBehindY < 0 || boxBehindY == grid[0].length || grid[boxBehindX][boxBehindY] == '#') {
            return false;
        }
        Deque<int[]> deque = new LinkedList<>();
        deque.offer(new int[]{peopleX, peopleY});
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        visited[peopleX][peopleY] = true;
        while (!deque.isEmpty()) {
            int[] cur = deque.poll();
            // 满足了人本身就在箱子背后的特殊情况
            if (cur[0] == boxBehindX && cur[1] == boxBehindY) {
                return true;
            }
            for (int[] direction : directions) {
                int rowIndex = cur[0] + direction[0];
                int colIndex = cur[1] + direction[1];
		    // 拆分点2：人能否到达箱子背后
                if (rowIndex < 0 || rowIndex == grid.length || colIndex < 0 || colIndex == grid[0].length || visited[rowIndex][colIndex] || grid[rowIndex][colIndex] == '#' || grid[rowIndex][colIndex] == 'B') {
                    continue;
                }
                deque.offer(new int[]{rowIndex, colIndex});
                visited[rowIndex][colIndex] = true;
            }
        }
        return false;
    }
}
