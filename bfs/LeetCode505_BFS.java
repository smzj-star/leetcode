/**
迷宫中有一个球，它有空地 (表示为 0) 和墙 (表示为 1)。球可以向上、向下、向左或向右滚过空地，但直到撞上墙之前它都不会停止滚动。当球停止时，它才可以选择下一个滚动方向。

给定 m × n 的迷宫(maze)，球的起始位置 (start = [startrow, startcol]) 和目的地 (destination = [destinationrow, destinationcol])，返回球在目的地 (destination) 停止的最短距离。如果球不能在目的地 (destination) 停止，返回 -1。

距离是指球从起始位置 ( 不包括 ) 到终点 ( 包括 ) 所经过的空地数。

你可以假设迷宫的边界都是墙 ( 见例子 )。

 

示例 1:



输入: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
输出: 12
解析: 一条最短路径 : left -> down -> left -> down -> right -> down -> right。
             总距离为 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12。

示例 2:



输入: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [3,2]
输出: -1
解析: 球不可能在目的地停下来。注意，你可以经过目的地，但不能在那里停下来。
示例 3:

输入: maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], start = [4,3], destination = [0,1]
输出: -1
 

注意:

m == maze.length
n == maze[i].length
1 <= m, n <= 100
maze[i][j] 是 0 或 1.
start.length == 2
destination.length == 2
0 <= startrow, destinationrow < m
0 <= startcol, destinationcol < n
球和目的地都存在于一个空地中，它们最初不会处于相同的位置。
迷宫至少包含两个空地。

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/the-maze-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
class Solution {
    private int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int rows = maze.length;
        int cols = maze[0].length;
        int[][] distances = new int[rows][cols];
        for (int[] distance : distances) {
            Arrays.fill(distance, Integer.MAX_VALUE);
        }
        distances[start[0]][start[1]] = 0;
        Deque<int[]> deque = new LinkedList<>();
        deque.offer(new int[]{start[0], start[1]});
        while (!deque.isEmpty()) {
            int[] cur = deque.poll();
            for (int[] direction : directions) {
                int rowIndex = cur[0] + direction[0];
                int colIndex = cur[1] + direction[1];
                // 如果是空地,球就一直往下滚,直到碰墙为止
                int count = 0;
                while (isValid(rowIndex, colIndex, maze)) {
                    count++;
                    rowIndex += direction[0];
                    colIndex += direction[1];
                }
                if (distances[cur[0]][cur[1]] + count < 
                distances[rowIndex - direction[0]][colIndex - direction[1]]) {
                    distances[rowIndex - direction[0]][colIndex - direction[1]] = 
                    distances[cur[0]][cur[1]] + count;
                    deque.offer(new int[]{rowIndex - direction[0], colIndex - direction[1]});
                }
            }
        }
        return distances[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : distances[destination[0]][destination[1]];
    }

    private boolean isValid(int row, int col, int[][] maze) {
        return row >= 0 && row < maze.length && col >= 0 && col < maze[0].length && maze[row][col] == 0;
    }
}
