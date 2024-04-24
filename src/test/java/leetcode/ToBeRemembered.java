package leetcode;

import java.util.ArrayList;
import java.util.List;

public class ToBeRemembered {

    /**
     * top93: 复制IP地址  https://leetcode.cn/problems/restore-ip-addresses/solutions/15658/jian-dan-yi-yu-li-jie-de-hui-su-fa-java-by-caipeng/
     *
     * 题解： 回溯算法
     *
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        backtracking(s, 0, new ArrayList<>(), ans);
        return ans;
    }


    private void backtracking(String s, int pos, List<String> cur, List<String> ans) {
        if (cur.size() >= 4) {
            if (pos == s.length()) {
                ans.add(String.join(".", cur));
                System.out.println("========= cur:" + cur);

            }
            return;
        }
        // 分割得到ip地址的一段后，下一段只能在长度1-3范围内选择
        for (int i = 1; i <= 3; i++) {
            if (pos + i > s.length()) break;
            String segment = s.substring(pos, pos + i);
            System.out.println("pos=" + pos + " i=" + i + " " + "segment: " + segment);
            // 剪枝条件：不能以0开头，不能大于255
            if (segment.startsWith("0") && segment.length() > 1 || (i == 3 && Integer.parseInt(segment) > 255)) break;
            // 符合要求就加入到 tmp 中
            cur.add(segment);
            System.out.println("list cur: " + cur);
            // 继续递归遍历下一个位置
            backtracking(s, pos + i, cur, ans);
            // 回退算法对不符合条件的元素进行剪枝
            cur.remove(cur.size() - 1);
            System.out.println("after remove list cur: " + cur);


        }
        System.out.println(" for end..");
    }

}
