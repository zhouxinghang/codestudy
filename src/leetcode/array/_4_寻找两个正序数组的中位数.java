package leetcode.array;

/**
 * @author zhouxinghang
 * @date 2019-07-16 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * nums1 = [1, 2] nums2 = [3, 4]
 *
 * The median is (2 + 3)/2 = 2.5
 */
public class _4_寻找两个正序数组的中位数 {

    /**
     * 两个索引分别chcek 数组1 和 数组2，直到 checkLength = (m + n)/2 时间复杂度不行，且逻辑复杂
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // nums1 和 nums2 都不为空
        int checkLength = 0;
        int index1 = 0;
        int index2 = 0;
        int length = (nums1.length + nums2.length);

        while (index1 < nums1.length && index2 < nums2.length) {
            if (nums1[index1] < nums2[index2]) {
                index1++;
            } else {
                index2++;
            }
            if ((index1 + index2) >> 1 == length + 1) {
                return Math.max(nums1[index1], nums2[index2]);
            }
            if ((index1 + index2) >> 1 == length + 2) {
                return (nums1[index1] + nums2[index2]) << 1;
            }
        }
        if (index1 >= nums1.length) {

        }
        return 0;
    }

    /**
     * 采用二分查找 要找的是第 k 个数（排序后， k=(m+n)/2，暂时不考虑奇偶），将两个数组从 k/2 开始分割，舍去其中最小的一个分组，继续递归查找
     * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-2/
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        if (length % 2 == 1) {
            return findKth(nums1, 0, nums2, 0, length / 2 + 1);
        } else {
            return (findKth(nums1, 0, nums2, 0, length / 2) + findKth(nums1, 0, nums2, 0, length / 2 + 1)) / 2.0;
        }

    }

    /**
     * 找出第 k 小的数
     * @param a
     * @param aStart
     * @param b
     * @param bStart
     * @param k
     * @return
     */
    public int findKth(int[] a, int aStart, int[] b, int bStart, int k) {
        if (aStart >= a.length) {
            return b[bStart + k - 1];
        }
        if (bStart >= b.length) {
            return a[aStart + k -1];
        }
        if (k == 1) {
            return Math.min(a[aStart], b[bStart]);
        }
        // 数组a按照 k 分为两组，第一组的最大值
        int aGroup1Max = aStart + k/2 -1 < a.length ? a[aStart + k/2 -1] : Integer.MAX_VALUE;
        int bGroup1Max = bStart + k/2 -1 < b.length ? b[bStart + k/2 -1] : Integer.MAX_VALUE;
        if (aGroup1Max < bGroup1Max) {
            // a 第一组的最大值 < b 第一组的最大值，说明 要找的 k 在另外个三个分组内
            return findKth(a, aStart + k/2, b, bStart, k - k/2);
        } else {
            return findKth(a, aStart, b, bStart + k/2, k - k/2);
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2};
        int[] nums2 = new int[]{3,4};
        System.out.println(new _4_寻找两个正序数组的中位数().findMedianSortedArrays2(nums1, nums2));
        System.out.println(new _4_寻找两个正序数组的中位数().findKth(nums1, 0, nums2, 0, 3));
    }


}
