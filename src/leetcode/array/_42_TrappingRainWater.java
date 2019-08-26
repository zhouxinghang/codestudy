package leetcode.array;

/**
 * @author zhouxinghang
 * @date 2019-08-26
 * 接水滴
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 */
public class _42_TrappingRainWater {

    /**
     * 直观想法：直接按问题描述进行。对于数组中的每个元素，我们找出下雨后水能达到的最高位置，等于两边最大高度的较小值减去当前高度的值
     * 找到数组中从下标 i 到最左端最高的条形块高度 left_max。
     * 找到数组中从下标 i 到最右端最高的条形块高度 right_max。
     * 扫描数组 height 并更新答案：
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        leftMax[0] = height[0];
        rightMax[height.length-1] = height[height.length-1];
        int res = 0;
        int minWeight;
        for (int j = height.length-2; j >= 0; j--) {
            rightMax[j] = Math.max(height[j], rightMax[j+1]);
        }
        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i-1]);
            minWeight = Math.min(leftMax[i], rightMax[i]);
            res += minWeight > height[i] ? minWeight - height[i] : 0;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new _42_TrappingRainWater().trap(new int[]{2,0,2}));
    }
}
