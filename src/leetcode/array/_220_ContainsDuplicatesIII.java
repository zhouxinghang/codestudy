package leetcode.array;

import java.util.TreeSet;

/**
 * @author zhouxinghang
 * @date 2019-07-15
 * 给定一个整数数组，判断数组中是否有两个不同的索引 i 和 j，使得 nums [i] 和 nums [j] 的差的绝对值最大为 t，并且 i 和 j 之间的差的绝对值最大为 ķ。
 *
 * Input: nums = [1,0,1,1], k = 1, t = 2
 * Output: true
 * Input: nums = [1,5,9,1,5,9], k = 2, t = 3
 * Output: false
 *  [-1,2147483647]
 * 1
 * 2147483647
 *
 */
public class _220_ContainsDuplicatesIII {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // 内部通过红黑树实现
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            // > num[i] 的最小数
            Integer s = set.ceiling(nums[i]);
            // 使用 s -num[i] <= t 会出现int越界情况
            if (s != null &&  s <= t + nums[i]) {
                return true;
            }
            // < num[i] 的最大数
            Integer f = set.floor(nums[i]);
            if (f != null && nums[i] <= f + t) {
                return true;
            }
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i -k]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new _220_ContainsDuplicatesIII().containsNearbyAlmostDuplicate(new int[]{-1,2147483647}, 1, 2147483647));
    }
}
