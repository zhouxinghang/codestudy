package leetcode.array;

/**
 * @author zhouxinghang
 * @date 2019-07-09
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 * 给定数组，第 i 个元素是第 i 天的股票价格。可多笔交易（买入和卖出一支股票为一笔交易），计算最大利润。只能先买后卖
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）
 *
 * Input: [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 *              Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 * Input: [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 *              Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
 *              engaging multiple transactions at the same time. You must sell before buying again.
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
public class _122_BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int maxProfit = 0;
        int prev = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prev) {
                maxProfit += prices[i] - prev;
            }
            prev = prices[i];
        }
        return maxProfit;
    }
}
