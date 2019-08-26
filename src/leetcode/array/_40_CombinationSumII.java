package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        System.out.println(new _40_CombinationSumII().combinationSum2(new int[]{2,3,5}, 8));
    }
}
