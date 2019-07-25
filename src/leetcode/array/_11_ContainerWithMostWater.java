package leetcode.array;

/**
 * @author zhouxinghang
 * @date 2019-07-25
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * https://leetcode.com/problems/container-with-most-water/
 */
public class _11_ContainerWithMostWater {

    /**
     * 暴力法
     * Runtime: 207 ms, faster than 14.58% of Java online submissions for Container With Most Water.
     * Memory Usage: 37.8 MB, less than 100.00% of Java online submissions for Container With Most Water.
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int maxArea = Integer.MIN_VALUE;
        int areaTemp;
        for (int i = 0; i< height.length; i++) {
            for (int j = i+1; j < height.length; j++) {
                areaTemp = (j - i) * Math.min(height[i], height[j]);
                maxArea = Math.max(areaTemp, maxArea);
            }
        }
        return maxArea;
    }

    /**
     * 采用双指针，向内逼近，长度低的指针向内逼近
     *
     *  Runtime: 2 ms, faster than 94.39% of Java online submissions for Container With Most Water.
     *  Memory Usage: 39.9 MB, less than 94.11% of Java online submissions for Container With Most Water.
     * @param height
     * @return
     */
    public int maxArea2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int maxArea = Integer.MIN_VALUE;
        int areaTemp;
        int left = 0, right = height.length - 1;
        while (left < right) {
            areaTemp = (right - left) * Math.min(height[left], height[right]);
            maxArea = Math.max(areaTemp, maxArea);
            if (height[left] < height[right]) {
                left ++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println(new _11_ContainerWithMostWater().maxArea2(new int[]{1,8,6,2,5,4,8,3,7}));
    }
}
