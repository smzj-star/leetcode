/**
一个坐标可以从 -infinity 延伸到 +infinity 的 无限大的 棋盘上，你的 骑士 驻扎在坐标为 [0, 0] 的方格里。

骑士的走法和中国象棋中的马相似，走 “日” 字：即先向左（或右）走 1 格，再向上（或下）走 2 格；或先向左（或右）走 2 格，再向上（或下）走 1 格。

每次移动，他都可以按图示八个方向之一前进。



返回 骑士前去征服坐标为 [x, y] 的部落所需的最小移动次数 。本题确保答案是一定存在的。

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/minimum-knight-moves
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
class Solution {
    private static class Pair {
        int x;
        int y;
        Pair (int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    int[][] directions = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, 2}, {-1, -2}, {-2, -1}};
    public int minKnightMoves(int x, int y) {
        if(x == 0 && y == 0) {
            return 0;
        }
        int absX = Math.abs(x);
        int absY = Math.abs(y);
        Deque<Pair> deque = new LinkedList<>();
        Set<Pair> visited = new HashSet<>();
        deque.offer(new Pair(0, 0));
        visited.add(new Pair(0, 0));
        int step = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                Pair cur = deque.poll();
                for (int[] direction : directions) {
                    int rowIndex = cur.x + direction[0];
                    int colIndex = cur.y + direction[1];
                    if (isValid(rowIndex, colIndex)) {
                        if (rowIndex == absX && colIndex == absY) {
                            return ++step;
                        }
                        Pair newPair = new Pair(rowIndex, colIndex);
                        if (!visited.contains(newPair)) {
                            visited.add(newPair);
                            deque.offer(new Pair(rowIndex, colIndex));
                        }
                        
                    }
                }
            }
            step++;
        }
        return step;
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && Math.abs(y) >= 0;
    }
}
