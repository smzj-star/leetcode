/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> path = new LinkedList<>();
        dfs(root, targetSum, result, path);
        return result;
    }

    private void dfs(TreeNode root, int targetSum, List<List<Integer>> result, Deque<Integer> path) {
        if (root != null) {
            path.offerLast(root.val);
            if (root.left == null && root.right == null) {
                if (path.stream().reduce(Integer::sum).orElse(0) == targetSum) {
                    result.add(new ArrayList<>(path));
                }
                path.pollLast();
                return;
            }
            dfs(root.left, targetSum, result, path);
            dfs(root.right, targetSum, result, path);
            path.pollLast();
        }
    }
}
