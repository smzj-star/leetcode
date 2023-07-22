/**
815. 公交路线
给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。

例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... 这样的车站路线行驶。
现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。

求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。

 

示例 1：

输入：routes = [[1,2,7],[3,6,7]], source = 1, target = 6
输出：2
解释：最优策略是先乘坐第一辆公交车到达车站 7 , 然后换乘第二辆公交车到车站 6 。 
示例 2：

输入：routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
输出：-1
 

提示：

1 <= routes.length <= 500.
1 <= routes[i].length <= 105
routes[i] 中的所有值 互不相同
sum(routes[i].length) <= 105
0 <= routes[i][j] < 106
0 <= source, target < 106
*/
class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        // 优化建图
        // key : 车站
        // value: 车站可以坐value公交到达
        Map<Integer, List<Integer>> graph = buildGraph(routes);
        return bfs(graph, routes, source, target);
    }

    private int bfs(Map<Integer, List<Integer>> graph, int[][] routes, int source, int target) {
        // 辅助队列中放的是车站
        Deque<Integer> deque = new LinkedList<>();
        deque.offer(source);
        // 访问数组放的是公交
        boolean[] visited = new boolean[routes.length];
        int minBuses = 0;
        while (!deque.isEmpty()) {
            minBuses++;
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                int curSite = deque.poll();
                // 接下来看这个车站都可以到达哪些车站
                // 交通手段是公交-->公交不能重复坐,用到访问数组
                // 看这个车站可以通过公交到达哪些车站
                // eg 1号车站可以到达2,7,3,6这四个车站
                List<Integer> buses = graph.get(curSite);
                for (int bus : buses) {
                    if (visited[bus]) {
                        continue;
                    }
                    visited[bus] = true;
                    for (int j = 0; j < routes[bus].length; j++) {
                        if (routes[bus][j] == target) {
                            return minBuses;
                        }
                        deque.offer(routes[bus][j]);
                    }
                }
            }
        }
        return -1;
    }

    private Map<Integer, List<Integer>> buildGraph(int[][] routes) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                List<Integer> buses = graph.getOrDefault(routes[i][j], new ArrayList<>());
                // i表示公交
                buses.add(i);
                graph.put(routes[i][j], buses);
            }
        }
        return graph;
    }
}
