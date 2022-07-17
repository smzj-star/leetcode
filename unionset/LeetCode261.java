/**
给定编号从 0 到 n - 1 的 n 个结点。给定一个整数 n 和一个 edges 列表，其中 edges[i] = [ai, bi] 表示图中节点 ai 和 bi 之间存在一条无向边。

如果这些边能够形成一个合法有效的树结构，则返回 true ，否则返回 false 。

 

示例 1：



输入: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
输出: true
示例 2:



输入: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
输出: false
 

提示：

1 <= n <= 2000
0 <= edges.length <= 5000
edges[i].length == 2
0 <= ai, bi < n
ai != bi
不存在自循环或重复的边

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/graph-valid-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
class Solution {
    class UnionFind {
        // 记录每个节点的父节点
        int[] parent;
        int vertexCount;
        public UnionFind (int n) {
            this.vertexCount = n;
            parent = new int[n];
            init();
        }

        public void init() {
            for (int i = 0; i < vertexCount; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] == x) {
                return x;
            }
            parent[x] = find(parent[x]);
            return parent[x];
        }

        public boolean union(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);
            if (parentX != parentY) {
                parent[parentY] = parentX;
                return true;
            }
            return false;
        }
    }
    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) {
            return false;
        }
        UnionFind unionFind = new UnionFind(n);
        for (int[] edge : edges) {
            if (!unionFind.union(edge[0], edge[1])) {
                return false;
            }
        }
        return true;
    }
}
