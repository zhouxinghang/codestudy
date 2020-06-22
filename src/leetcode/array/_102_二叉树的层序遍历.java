package leetcode.array;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author zhouxinghang
 * Created on 2020-06-22
 */
public class _102_二叉树的层序遍历 {

    // 时间复杂度： O(n) 空间复杂度：O(n)
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root==null) {
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        TreeNode tempNode;
        ArrayList<Integer> tempList;
        while (!queue.isEmpty()) {
            int size = queue.size();
            tempList = new ArrayList<Integer>();
            for (int i = 0; i < size; i++) {
                tempNode = queue.remove();
                tempList.add(tempNode.val);
                if (tempNode.left != null) {
                    queue.add(tempNode.left);
                }
                if (tempNode.right != null) {
                    queue.add(tempNode.right);
                }
            }
            res.add(tempList);
        }
        return res;
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
