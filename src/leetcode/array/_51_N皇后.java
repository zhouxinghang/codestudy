package leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouxinghang
 * Created on 2020-06-22
 * 返回所有的解法
 *
 * 回溯的核心算法框架
 *     result = []
 *     def backtrack(路径, 选择列表):
 *     if 满足结束条件:
 *         result.add(路径)
 *         return
 *
 *     for 选择 in 选择列表:
 *         做选择
 *         backtrack(路径, 选择列表)
 *          撤销选择
 *
 */
public class _51_N皇后 {
    // 遍历 剪枝
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        return null;
    }

    /**
     * 回溯
     * @param board 路径
     * @param row   选择
     */
    public void backtrack(List<String>[] board, int row) {
        if (row == board.length) {
            res.add(convertRes(board));
        }
        int n = board[row].size();
        for (int col = 0; col < n; col++) {
            // 排除不合法选择
            if (!isValid(board, row, col))
                continue;
            // 做选择
//            board[row][col] = 'Q';
//            // 进入下一行决策
//            backtrack(board, row + 1);
//            // 撤销选择
//            board[row][col] = '.';
        }

    }

    public boolean isValid(List<String>[] board, int row, int col) {
        return false;
    }

    public List<String> convertRes(List<String>[] board) {
        return new ArrayList<>();
    }
}
