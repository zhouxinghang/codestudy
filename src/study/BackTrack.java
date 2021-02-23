package study;

/**
 * @author zhouxinghang
 * Created on 2021-02-22
 * 回溯法，
 * https://zhuanlan.zhihu.com/p/112926891
 * 套路模板：
 * res = []    # 全局变量，保存最终结果
 * state = []  # 状态变量，保存当前状态
 * p,q,r       # 条件变量（一般条件变量就是题目直接给的参数）
 *
 * def back(状态，条件1，条件2，……):
 *     if # 不满足合法条件（可以说是剪枝）
 *         return
 *     elif # 状态满足最终要求
 *         res.append(state)   # 加入结果
 *         return
 *     # 主要递归过程，一般是带有 循环体 或者 条件体
 *     for # 满足执行条件
 *     if  # 满足执行条件
 *         back(状态，条件1，条件2，……)
 *
 * back(状态，条件1，条件2，……)
 *
 * return res
 *
 * 二维数组下的 DFS 搜索。排列组合需要排除前面使用的元素
 *
 * 例题：
 *  131 分割回文串 66.6%
 *  40 组合总数II 60.6%
 */
public class BackTrack {
}
