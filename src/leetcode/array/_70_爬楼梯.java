package leetcode.array;

/**
 * @author zhouxinghang
 * Created on 2021-02-25
 * 一次爬1，2阶，给定 n 阶，输出方案数
 * n=2，两种 （1，1）（2）
 */
public class _70_爬楼梯 {

    // p[n] = p[n-1] + p[n-2]; p[n]表示n阶的方案数
    public int climbStairs(int n) {
        if (n == 0) {
            return 0;
        }
        int[] p = new int[n+1];
        p[1] = 1;
        p[0] = 1;
        for (int i=2; i <= n; i++) {
            p[i] = p[i-1] + p[i-2];
        }
        return p[n];
    }

    public static void main(String[] args) {
        System.out.println(new _70_爬楼梯().climbStairs(5));
    }
}
