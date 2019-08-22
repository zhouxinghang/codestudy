package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhouxinghang
 * @date 2019-08-22
 */
public class _47_PermutationsII {

    /**
     * 采用回溯法，不同的是有一个重复元素的处理。需要先给数组排序，然后判断前后元素是否相同。
     * 如果相同且前面的元素没有访问过，就跳过这个元素，保证这个原始值不会访问
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> currList = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        backTrack(res, currList, visited, nums);
        return res;
    }


    public void backTrack(List<List<Integer>> res, List<Integer> currList, boolean[] visited, int[] nums) {
        if (currList.size() == nums.length) {
            res.add(new ArrayList<>(currList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (i >0 && nums[i] == nums[i-1] && !visited[i-1]) {
                    continue;
                }
                if (visited[i]) {
                    continue;
                }
                visited[i] = true;
                currList.add(nums[i]);
                backTrack(res, currList, visited, nums);
                currList.remove(currList.size() - 1);
                visited[i] = false;
            }
        }


    }
}
