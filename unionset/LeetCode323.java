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
    class UnionFind {
        int[] parent;
        int count;
        public UnionFind(int n) {
            parent = new int[n];
            count = n;
            init(n);
        }

        public void init(int n) {
            for (int i = 0; i < n; i++) {
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

        public void union(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);
            if (parentX != parentY) {
                parent[parentY] = parentX;
                count--;
            }
        }
    }
    
    public int countComponents(int n, int[][] edges) {
        UnionFind unionFind = new UnionFind(n);
        for (int[] edge : edges) {
            unionFind.union(edge[0], edge[1]);
        }
        return unionFind.count;
    }
}
