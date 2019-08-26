package leetcode.array;

/**
 * @author zhouxinghang
 * @date 2019-08-26
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置。
 * Input: [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 */
public class _55_JumpGame {

    /**
     * 采用回溯法，用一个max记录当前能达到的最大索引，逐个遍历数组去更新这个索引
     * 这个是贪心算法
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     *
     * Runtime: 1 ms, faster than 99.26% of Java online submissions for Jump Game.
     * Memory Usage: 40.8 MB, less than 52.99% of Java online submissions for Jump Game.
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (max < i) {
                return false;
            }
            max = Math.max(max, nums[i] + i);
        }
        return max >= nums.length - 1;
    }

    enum Index {
        GOOD, BAD, UNKNOWN
    }

    /**
     * 采用动态规划，自低向上遍历
     * 时间复杂度 O(n^2)
     * 空间复杂度 O(n)
     * Runtime: 188 ms, faster than 24.01% of Java online submissions for Jump Game.
     * Memory Usage: 41 MB, less than 50.43% of Java online submissions for Jump Game.
     * @param nums
     * @return
     */
    public boolean canJump2(int[] nums) {
        Index[] memo = new Index[nums.length];
        for (int i = 0; i < nums.length-1; i++) {
            memo[i] = Index.UNKNOWN;
        }
        memo[memo.length - 1] = Index.GOOD;
        for (int i = nums.length - 2; i >=0; i--) {
            int furthestJump = Math.min(i + nums[i], nums.length - 1);
            for (int j = i+1; j <= furthestJump; j ++) {
                if (memo[j] == Index.GOOD) {
                    memo[i] = Index.GOOD;
                    break;
                }
            }
        }
        return memo[0] == Index.GOOD;
    }

    public static void main(String[] args) {
        System.out.println(new _55_JumpGame().canJump2(new int[]{2,3,1,1,4}));
    }
}
