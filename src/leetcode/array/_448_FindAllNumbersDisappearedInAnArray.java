package leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhouxinghang
 * @date 2019-07-15
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 *
 * Input:
 * [4,3,2,7,8,2,3,1]
 *
 * Output:
 * [5,6]
 */
public class _448_FindAllNumbersDisappearedInAnArray {

    /**
     * Runtime: 28 ms, faster than 12.29% of Java online submissions for Find All Numbers Disappeared in an Array.
     * Memory Usage: 54.3 MB, less than 6.03% of Java online submissions for Find All Numbers Disappeared in an Array.
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i= 0; i < nums.length; i++) {
            map.put(nums[i], 1);
        }
        for (int i = 1; i < nums.length + 1; i++) {
            if (map.get(i) == null) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * map 完全可以用原始的数组来替代，这样空间复杂度为 O(1)，时间复杂度同上 O(2n)。
     * 存在的数，将其对应位置设置为负数，最后统计正数表示不存在的数
     *
     * Runtime: 6 ms, faster than 80.18% of Java online submissions for Find All Numbers Disappeared in an Array.
     * Memory Usage: 48.1 MB, less than 53.78% of Java online submissions for Find All Numbers Disappeared in an Array.
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers2(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        int index;
        for (int i= 0; i < nums.length; i++) {
            index = Math.abs(nums[i]) -1;
            nums[index] = -Math.abs(nums[index]);
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }
        return result;
    }
}
