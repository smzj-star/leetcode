//在本问题中，有根树指满足以下条件的 有向 图。该树只有一个根节点，所有其他节点都是该根节点的后继。该树除了根节点之外的每一个节点都有且只有一个父节点，而根节
//点没有父节点。 
//
// 输入一个有向图，该图由一个有着 n 个节点（节点值不重复，从 1 到 n）的树及一条附加的有向边构成。附加的边包含在 1 到 n 中的两个不同顶点间，这条
//附加的边不属于树中已存在的边。 
//
// 结果图是一个以边组成的二维数组 edges 。 每个元素是一对 [ui, vi]，用以表示 有向 图中连接顶点 ui 和顶点 vi 的边，其中 ui 是 
//vi 的一个父节点。 
//
// 返回一条能删除的边，使得剩下的图是有 n 个节点的有根树。若有多个答案，返回最后出现在给定二维数组的答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：edges = [[1,2],[1,3],[2,3]]
//输出：[2,3]
// 
//
// 示例 2： 
//
// 
//输入：edges = [[1,2],[2,3],[3,4],[4,1],[1,5]]
//输出：[4,1]
// 
//
// 
//
// 提示： 
//
// 
// n == edges.length 
// 3 <= n <= 1000 
// edges[i].length == 2 
// 1 <= ui, vi <= n 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 图 👍 307 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        // O(n = 1000) from last to first, check if remove valid
        // Nested: check O(V + E = 2000)
        // So total is O(2e6)
        // 1. build Graph with whole
        Map<Integer, Set<Integer>> graph = buildGraph(edges);
        int[] indegree = buildIndegree(edges);

        // 2. remove current edge and check
        int n = edges.length;
        for(int i = n - 1; i >= 0; i--){
            // remove current one
            int u = edges[i][0];
            int v = edges[i][1];

            graph.get(u).remove(v);
            indegree[v]--;

            if(ok(graph, indegree)) return edges[i];

            indegree[v]++;
            graph.get(u).add(v);
        }

        return new int[]{-1, -1};
    }

    // build the graph by input edges
    private Map<Integer, Set<Integer>> buildGraph(int[][] edges){
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            graph.putIfAbsent(u, new HashSet<>());
            graph.putIfAbsent(v, new HashSet<>());

            graph.get(u).add(v);
        }

        return graph;
    }

    private int[] buildIndegree(int[][] edges){
        int n = edges.length;
        int[] indegree = new int[n + 1];
        indegree[0] = -100;

        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            indegree[v]++;
        }
        return indegree;
    }

    // check current graph is valid tree of not
    private boolean ok(Map<Integer, Set<Integer>> graph, int[] indegree) {
        // valid tree: one start root and no cycle
        List<Integer> indegreeEqualsZero = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        // indegree is 0
        for (int i = 1; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                indegreeEqualsZero.add(i);
                visited.add(i);
            }
        }

        if (indegreeEqualsZero.size() != 1) return false;

        int u = indegreeEqualsZero.get(0);

        if (!dfs(graph, u, visited)) {
            return false;
        } else {
            int n = indegree.length - 1;
            return visited.size() == n;
        }
    }

    private boolean dfs(Map<Integer, Set<Integer>> graph, int u, Set<Integer> visited) {
        visited.add(u);
        for(int v : graph.get(u)){
            if (visited.contains(v)) {
                return false;
            } else {
                dfs(graph, v, visited);
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
