package leetcode.array;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @author zhouxinghang
 * @date 2019-08-26
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * Input: candidates = [10,1,2,7,6,1,5], target = 8,
 * A solution set is:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 *
 */
public class _40_CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        findCombinationSum(res, candidates, target, 0, new Stack<>());
        return res;
    }

    public void findCombinationSum(List<List<Integer>> res, int[] candidates, int residue, int start, Stack<Integer> pre) {
        if (residue == 0) {
            res.add(new ArrayList<>(pre));
        }
        for (int i = start; i < candidates.length && residue - candidates[i] >= 0; i++) {
            pre.add(candidates[i]);
            findCombinationSum(res, candidates, residue - candidates[i], i+1, pre);
            pre.pop();
        }
    }

    public static void main(String[] args) {
        System.out.println(new _40_CombinationSumII().combinationSum3(new int[]{10,1,2,7,6,1,5}, 8));
    }

    /**
     * 使用回溯模板解决
     * 数字有重复会产生重复结果，需要去重
     */
    public List<List<Integer>> combinationSum3(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        Arrays.sort(candidates);
        backtrack(res, path, candidates, target, 0);
        return res;
    }

    public void backtrack(List<List<Integer>> res, Deque<Integer> path, int[] candidates, int residue, int start) {
        if (residue == 0) {
            // 深拷贝
            res.add(new ArrayList<>(path));
        }
        for (int i = start; i < candidates.length; i++) {
            // 剪枝
            if (residue < candidates[i]) {
                continue;
            }
            if (i > start && candidates[i] == candidates[i-1]) {
                continue;
            }
            path.add(candidates[i]);
            backtrack(res, path, candidates, residue - candidates[i], i+1);
            path.removeLast();
        }
    }

}
