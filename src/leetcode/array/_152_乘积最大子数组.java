package leetcode.array;

/**
 * @author zhouxinghang
 * Created on 2020-06-22
 * 给你一个整数数组 nums，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积
 */
public class _152_乘积最大子数组 {

    public static void main(String[] args) {
        System.out.println(new _152_乘积最大子数组().maxProduct2(new int[]{-4,-3,-2}));
    }

    // 考虑最大正数，最小负数
    // dp[i][j] 表示位置 i-j 的最大乘积子序列
    // 考虑空间优化

    public int maxProduct1(int[] nums) {
        int[][] max = new int[nums.length][nums.length];
        int[][] min = new int[nums.length][nums.length];

        // 状态转移方程
        return -1;
    }

    public int maxProduct2(int[] nums) {
        int res = nums[0];
        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int maxI = max;
            int minI = min;
            max = maxRes(nums[i], maxI*nums[i], minI*nums[i]);
            min = minRes(nums[i], maxI*nums[i], minI*nums[i]);
            res = Math.max(res, max);
        }
        return res;
    }

    public int maxRes(int a, int b, int c) {
        return Math.max(a,Math.max(b,c));
    }

    public int minRes(int a, int b, int c) {
        return Math.min(a,Math.min(b,c));
    }

    // 考虑正负，需要记录最小值，最大值
    public int maxProduct3(int[] nums) {
        int res = nums[0];
        int max = nums[0];// 前 i 个的最大乘积，必须包含 i
        int min = nums[0];
        for (int i = 0; i < nums.length; i++) {
            int maxI = max;
            int minI = min;
            max = maxRes(nums[i], maxI*nums[i], minI*nums[i]);
            min = minRes(nums[i], maxI*nums[i], minI*nums[i]);
            res = Math.max(res, max);
        }
        return res;
    }
}
