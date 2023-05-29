/**
你有一个包含 n 个节点的图。给定一个整数 n 和一个数组 edges ，其中 edges[i] = [ai, bi] 表示图中 ai 和 bi 之间有一条边。

返回 图中已连接分量的数目 。

 

示例 1:



输入: n = 5, edges = [[0, 1], [1, 2], [3, 4]]
输出: 2
示例 2:



输入: n = 5, edges = [[0,1], [1,2], [2,3], [3,4]]
输出:  1
 

提示：

1 <= n <= 2000
1 <= edges.length <= 5000
edges[i].length == 2
0 <= ai <= bi < n
ai != bi
edges 中不会出现重复的边

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/number-of-connected-components-in-an-undirected-graph
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
class Solution {
    public int countComponents(int n, int[][] edges) {
        List<Integer>[] adj = buildGraph(n, edges);
        boolean[] visited = new boolean[n];
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bfs(i, visited, adj);
                result++;
            }
        }
        return result;
    }

    private void bfs(int node, boolean[] visited, List<Integer>[] adj) {
        Deque<Integer> deque = new LinkedList<>();
        deque.offer(node);
        visited[node] = true;
        while (!deque.isEmpty()) {
            int cur = deque.poll();
            List<Integer> neighbors = adj[cur];
            for (int neighbor : neighbors) {
                if (!visited[neighbor]) {
                    deque.offer(neighbor);
                    visited[neighbor] = true;
                }
            }
        }
    }

    private List<Integer>[] buildGraph(int n, int[][] edges) {
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            // 顶点节点添加边表节点
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        return adj;
    }    
}
