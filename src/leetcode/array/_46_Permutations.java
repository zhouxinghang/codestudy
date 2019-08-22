package leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouxinghang
 * @date 2019-08-22
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 */
public class _46_Permutations {

    /**
     * 采用回溯法，添加一个list记录新的排列结果，一个数组记录哪些元素被选择了，防止重复选择
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> currList = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        backTrack(res, currList, visited, nums);
        return res;
    }

    public void backTrack(List<List<Integer>> res, List<Integer> currList, boolean[] visited, int[] nums) {
        if (currList.size() == nums.length) {
            // 创建一个新的list添加到结果集中
            res.add(new ArrayList<>(currList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (visited[i]) {
                    continue;
                }
                visited[i] = true;
                currList.add(nums[i]);
                backTrack(res, currList, visited, nums);
                // 不选这个数字，留着下次选择
                currList.remove(currList.size() - 1);
                visited[i] = false;
            }
        }
    }
}
