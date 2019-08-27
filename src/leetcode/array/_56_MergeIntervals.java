package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author zhouxinghang
 * @date 2019-08-26
 * 合并区间
 * 给出一个区间的集合，请合并所有重叠的区间。
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 */
public class _56_MergeIntervals {

    /**
     * 先排序，然后一个个判断
     * 时间复杂度：一个sort + 线性扫描 O(nlogn)
     * 空间复杂度：O(1) (or O(n)O(n))。如果我们可以原地排序 intervals ，就不需要额外的存储空间；否则，我们就需要一个线性大小的空间去存储 intervals 的备份，来完成排序过程
     *
     * Runtime: 41 ms, faster than 7.75% of Java online submissions for Merge Intervals.
     * Memory Usage: 45.5 MB, less than 23.19% of Java online submissions for Merge Intervals.
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        if (intervals.length == 0 || intervals == null) {
            return res.toArray(new int[0][]);
        }

        Arrays.sort(intervals, Comparator.comparingInt(left -> left[0]));
        int left = intervals[0][0];
        int right = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (right >= intervals[i][0]) {
                right = Math.max(right, intervals[i][1]);
            } else {
                res.add(new int[]{left, right});
                left = intervals[i][0];
                right = intervals[i][1];
            }
        }
        res.add(new int[]{left, right});

        return res.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{new int[]{8,10}, new int[]{1,3}};
        new _56_MergeIntervals().merge(intervals);
    }
}
