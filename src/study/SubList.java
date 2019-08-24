package study;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouxinghang
 * @date 2019-08-22
 * 输出含有n个元素集合的所有子集。例如，三个元素{a,b,c}的所有子集是：{},{a},{b},{c},{a,c},{ac},{b,c},{a,b,c}.
 */
public class SubList {
    public static List<List<String>> subList(List<String> list) {
        List<List<String>> res = new ArrayList<>();
        if (list == null || list.size() == 0) {
            res.add(list);
            return res;
        }
        String first = list.get(0);
        list.remove(0);
        List<List<String>> subList = subList(list);
        List<String> temp;
        for (List<String> sub : subList) {
            temp = new ArrayList<>(sub);
            temp.add(first);
            res.add(temp);
        }
        // 合并两个结果
        res.addAll(subList);
        return res;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        System.out.println(subList(list));
        System.out.println("abc".substring(1));
    }
}
