package leetcode.array;

import java.util.Arrays;

/**
 * @author zhouxinghang
 * @date 2019/2/25
 * Given an unsorted integer array, find the smallest missing positive integer.
 * 给出首个缺失的正数
 * Example:
 * Input: [1,2,0]
 * Output: 3
 * <p>
 * Input: [3,4,-1,1]
 * Output: 2
 * <p>
 * Input: [7,8,9,11,12]
 * Output: 1
 * Note:
 * Your algorithm should run in O(n) time and uses constant extra space.
 */

public class _41_FirstMissingPositive {
    public static int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        System.out.println(Arrays.toString(nums));
        /**
         * 将值放到对应的index中，对号入座，然后查找编号，如果那个编号与其值不对应，则这个编号就是第一个未出现的正整数。
         * 如果最小的正整数都出现，那么经过对号入座后，是不会出现编号与其值不对应情况的。比如 3、2、4、1 ==> 1、2、3、4 这个就是理想对号入座情况
         */
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        // 查找每个编号是否与其值对应
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4, 5, 0, 2};
        System.out.println(firstMissingPositive(nums));
    }
}
