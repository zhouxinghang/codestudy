package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author zhouxinghang
 * @date 2019-08-24
 *
 * 组合总和
 *
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取
 *
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *
 */
public class _39_CombinationSum {

    /**
     * 采用递归回溯方法
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        // 先做排序，可以提前退出
        Arrays.sort(candidates);
        findCombinationSum(res, candidates, target, 0, new Stack<>());
        return res;
    }

    public void findCombinationSum(List<List<Integer>> res, int[] candidates, int residue, int start, Stack<Integer> pre) {
        if (residue == 0) {
            res.add(new ArrayList<>(pre));
        }
        // 整个for循环表示遍历的每一层，每一层都有 candidates.length 个选择，
        for (int i = start; i < candidates.length && residue - candidates[i] >=0; i++) {
            pre.add(candidates[i]);
            findCombinationSum(res, candidates, residue - candidates[i], i, pre);
            pre.pop();
        }
    }


    public static void main(String[] args) {
        System.out.println(new _39_CombinationSum().combinationSum(new int[]{2,3,5}, 8));
    }
}
