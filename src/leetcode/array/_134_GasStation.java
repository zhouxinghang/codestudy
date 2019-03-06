package leetcode.array;


/**
 * @author zhouxinghang
 * @date 2019/3/5
 * 给定两个数组，gas 和 cost，
 * gas[i] 表示 第 i+1 个 加油站能加的油，
 * cost[i] 表示 第 i +1 到第 i+2 个加油站消耗的油
 * 有 i 个加油站围成一个圆，选择从哪个点出发能够走完全程，
 * 能的话返回索引 i，否则返回 -1
 *
 * Note:
    If there exists a solution, it is guaranteed to be unique.
    Both input arrays are non-empty and have the same length.
    Each element in the input arrays is a non-negative integer.
 *
 * Example 1:
    Input:
    gas  = [1,2,3,4,5]
    cost = [3,4,5,1,2]

    Output: 3

    Explanation:
    Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
    Travel to station 4. Your tank = 4 - 1 + 5 = 8
    Travel to station 0. Your tank = 8 - 2 + 1 = 7
    Travel to station 1. Your tank = 7 - 3 + 2 = 6
    Travel to station 2. Your tank = 6 - 4 + 3 = 5
    Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
    Therefore, return 3 as the starting index.
 *
 *
 * 解法：
 * https://www.cnblogs.com/boring09/p/4248482.html
 * 基于一个数学定理：
    如果一个数组的总和非负，那么一定可以找到一个起始位置，从他开始绕数组一圈，累加和一直都是非负的
    （证明貌似不难，以后有时间再补）

    有了这个定理，判断到底是否存在这样的解非常容易，只需要把全部的油耗情况计算出来看看是否大于等于0即可。

    那么如何求开始位置在哪？

    注意到这样一个现象：

    1. 假如从位置i开始，i+1，i+2...，一路开过来一路油箱都没有空。说明什么？说明从i到i+1，i+2，...肯定是正积累。
    2. 现在突然发现开往位置j时油箱空了。这说明什么？说明从位置i开始没法走完全程(废话)。那么，我们要从位置i+1开始重新尝试吗？
        不需要！为什么？因为前面已经知道，位置i肯定是正积累，那么，如果从位置i+1开始走更加没法走完全程了，因为没有位置i的正积累了。
        同理，也不用从i+2，i+3，...开始尝试。所以我们可以放心地从位置j+1开始尝试。
 *
 */
public class _134_GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // note 提示，下面的判断其实不需要
        if (gas.length == 0 || cost.length == 0 || gas.length != cost.length) {
            return -1;
        }
        int total = 0, sum = 0, start = 0;
        for (int i = 0; i< gas.length; i++) {
            total += gas[i] - cost[i];
            if (sum < 0) {
                sum = gas[i] - cost[i];
                start = i;
            } else {
                sum += gas[i] - cost[i];;
            }
        }
        // gas - cost < 0 表示不存在这样的方案
        if (total < 0) {
            return -1;
        } else {
            return start;
        }
    }

    public static void main(String[] args) {
        System.out.println(new _134_GasStation().canCompleteCircuit(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2}));
    }
}
