package leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouxinghang
 * Created on 2020-06-22
 */
public class _236_二叉树的最近公共祖先 {
    Map<TreeNode, Boolean> record = new HashMap<>();
    private TreeNode ans;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.dfs(root, p, q);
        return this.ans;
    }

    private boolean dfs(TreeNode root,TreeNode p,TreeNode q) {
        if (root == null) {
            return false;
        }
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);
        // 满足这个条件，就是最近公共祖先
        if (lson && rson || ((root.val == p.val || root.val == q.val)&& (lson||rson))) {
            ans = root;
        }
        return lson || rson || root.val == p.val || root.val == q.val;
    }

    /**
     * 对上面的方法做合并
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        return left == null ? right : right == null? left: root;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
