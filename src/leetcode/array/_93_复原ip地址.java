package leetcode.array;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author zhouxinghang
 * Created on 2021-02-22
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式
 *
 * 25525511135 :  [255.255.11.135,255.255.111.35]
 */
public class _93_复原ip地址 {

    public static void main(String[] args) {
        System.out.println("abc".charAt(0));
        System.out.println(new _93_复原ip地址().restoreIpAddresses("25525511135"));
        System.out.println(new _93_复原ip地址().restoreIpAddresses("010010"));
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        Deque<String> path = new ArrayDeque<>();
        backtrack(res, path, s, 0);
        return res;
    }

    private void backtrack(List<String> res, Deque<String> path, String s, int start) {
        if (path.size() == 4) {
            if (start == s.length()) {
                res.add(toIp(path));
            }
            return;
        }

        for (int i = start; i < s.length() && i < start + 3; i++) {
            String s0 = s.substring(start, i+1);
            if (!valid(s0)) {
                continue;
            }
            path.add(s0);
            backtrack(res, path, s, i+1);
            path.removeLast();
        }
    }

    private String toIp(Deque<String> path) {
        StringBuilder sb = new StringBuilder();
        for (String item : path) {
            sb.append(item).append(".");
        }
        String ip = sb.toString();
        return ip.substring(0, ip.length()-1);
    }

    private boolean valid(String var) {
        int n = Integer.parseInt(var);
        if (var.charAt(0) == '0') {
            return var.length() == 1;
        }
        return  n <= 255;
    }
}
