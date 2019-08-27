package leetcode.array;

import java.util.Arrays;

/**
 * @author zhouxinghang
 * @date 2019-08-27
 * 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation:
 * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down
 * 2. Right -> Down -> Right
 * 3. Down -> Right -> Right
 */
public class _62_UniquePaths {

    /**
     * 排列组合
     * 步骤：m个right + n个down。
     * 结果：(m+n)! / (m! + n!)
     * 时间复杂度 O(N)
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths.
     * Memory Usage: 32.9 MB, less than 5.10% of Java online submissions for Unique Paths.
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        m--;
        n--;
        long res = 1;
        long vid = 1;
        int max, min;
        if (m < n) {
            min = m;
            max = n;
        } else {
            min = n;
            max = m;
        }
        for (long i = m+n; i > max; i--) {
            res *= i;
        }
        for (long i = 2; i <= min; i++) {
            vid *= i;
        }
        return (int) (res / vid);
    }

    /**
     * 采用动态规划的方式
     * dp[i][j]表示到达(i,j)的路径数量。dp[i][j] = dp[i-1][j] + dp[i][j-1]
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths2(int m, int n) {
        int[] cur = new int[n];
        Arrays.fill(cur, 1);
        for (int i = 1; i < m;i++){
            for (int j = 1; j < n; j++){
                // dp[i][j] = dp[i-1][j] + dp[i][j-1]
                cur[j] += cur[j-1] ;
            }
        }
        return cur[n-1];
    }

    public static void main(String[] args) {
        System.out.println(new _62_UniquePaths().uniquePaths2(3, 3));
    }
}
