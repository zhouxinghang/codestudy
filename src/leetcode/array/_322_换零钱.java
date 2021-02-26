package leetcode.array;

import java.util.Arrays;

/**
 * @author zhouxinghang
 * Created on 2020-06-22
 * 给定不同面额的硬币 coins 和一个总金额 amount，输出凑成总金额所需的最少的硬币个数，无输出 -1
 */
public class _322_换零钱 {

    // dp[n] = min(dp[n-x] + 1, dp[n]) ； dp[n] 表示 金额 n 最少兑换硬币数
    public int coinChange(int[] coins, int amount) {
        if (coins.length == 0) {
            return -1;
        }
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] == (amount+1) ? -1 : dp[amount];
    }

    // dp[m] = min(dp[m-x] + 1, dp[n]); dp[n] 表示金额 n 最少兑换硬币数
    public int coinChange2(int[] coins, int amount) {
        if (coins.length < 1) {
            return -1;
        }
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
                }
            }
        }
        Arrays.stream(dp).forEach(System.out::println);
        return dp[amount] == (amount+1) ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = new int[]{1,2,5};
        System.out.println(new _322_换零钱().coinChange2(coins,23));
    }
}
