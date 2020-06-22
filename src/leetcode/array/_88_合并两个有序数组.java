package leetcode.array;

import java.util.Arrays;

/**
 * @author zhouxinghang
 * Created on 2020-06-20
 */
public class _88_合并两个有序数组 {
    public static void main(String[] args) {
        int nums1[] = new int[]{0};
        int nums2[] = new int[]{1};
        merge(nums1, 0, nums2,1 );
        System.out.println(nums1[0]);
    }
    /**
     * 结果放在 nums1 中，如果是正常的做法，就需要额外的空间来保存 nums1。考虑从后往前遍历就不会有问题了
     */
    public static  void merge(int[] nums1, int m, int[] nums2, int n) {
        int p = m + n - 1;
        m --;
        n --;
        while (m >= 0 && n >= 0) {
            if (nums1[m] > nums2[n]) {
                nums1[p] = nums1[m];
                m--;
                p--;
            } else {
                nums1[p] = nums2[n];
                n--;
                p--;
            }
        }
        if (n >= 0) {
            System.arraycopy(nums2, 0, nums1, 0, n+1);
        }
    }
}
