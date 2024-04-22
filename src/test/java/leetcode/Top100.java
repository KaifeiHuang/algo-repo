package leetcode;

import org.junit.Test;

import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Top100 {


    @Test
    public void testmultiply() {
        String num1 = "123";
        String num2 = "45";
        multiply1(num1, num2);

    }


    public String multiply1(String s1, String s2) {
        // 临界条件
        if (s1.equals("0") || s2.equals("0"))
            return "0";
        // 计算
        int m = s1.length();
        int n = s2.length();
        int[] ans = new int[m + n];

        for (int i = m - 1; i >= 0; i--) {
            int a = s1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int b = s2.charAt(j) - '0';
                int sum = ans[i + j + 1] + a * b;
                // 求尾数
                ans[i + j + 1] = sum % 10;
                // 求进位
                ans[i + j] += sum / 10;
            }
        }
        // 求最终结果
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ans.length; i++) {
            // 消除第一位0
            if (i == 0 && ans[i] == 0) {
                continue;
            }
            sb.append(ans[i]);
        }
        return sb.toString();

    }

    /**
     * top43： 43. 字符串相乘  https://leetcode.cn/problems/multiply-strings/description/
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        BigInteger ba = new BigInteger(num1);
        BigInteger bb = new BigInteger(num2);
        BigInteger multiply = ba.multiply(bb);
        System.out.println(multiply.intValue());
        return multiply.intValue() + "";
    }


    @Test
    public void testlengthOfLastWord() {
        String s = "Hello World";
        int i = lengthOfLastWord(s);
        System.out.println(i);
    }

    /**
     * length-of-last-word: https://leetcode.cn/problems/length-of-last-word/
     * <p>
     * top: 58
     * 题解：
     * 1、按空格正则分割
     * 2、倒序遍历，找到第一个不为空的字符串返回
     *
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {

        String[] split = s.split("\\s+");
        int len = split.length;
        if (len == 1) {
            return split[0].length();
        }
        for (int i = len - 1; i > 0; i--) {
            if (!split[i].isEmpty()) {
                return split[i].length();
            }
        }
        return 0;
    }


    @Test
    public void testremoveDuplicates() {
        int[] nums = {1, 1, 2};
        int i = removeDuplicates(nums);
        System.out.println(2);
    }


    /**
     * top26：https://leetcode.cn/problems/remove-duplicates-from-sorted-array/description/
     * <p>
     * 题目： 删除有序数组中的重复项
     * <p>
     * 1、创建一个慢指针 iii，指向数组第一位数字，再创建一个快指针 jjj，指向数组第二位。
     * 2、若 nums[j]nums[j]nums[j] 和 nums[i]nums[i]nums[i] 不等，则先将 iii 递增 111，然后把 nums[i]nums[i]nums[i] 改为 nums[j]nums[j]nums[j]。
     * 3、因为最初 iii 等于 000 时的数字未统计，所以最终返回结果需要 +1+1+1。
     * <p>
     * 作者：御三五 🥇
     * 链接：https://leetcode.cn/problems/remove-duplicates-from-sorted-array/solutions/683841/shuang-zhi-zhen-shan-chu-you-xu-shu-zu-z-3pi4/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            //等于时，j往后移动
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }


    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 处理临界条件
        if (list1 == null && list2 == null) return list1;
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        ListNode res = new ListNode(-1);
        ListNode curr = res;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }
        curr.next = list1 != null ? list1 : list2;
        return res.next;

    }

    public ListNode addAfter(ListNode baseNode, ListNode newNode) {
        ListNode resNode = new ListNode(-1);
        ListNode curr = baseNode;

        while (curr != null) {
            // 插入元素比链表第一个元素小
            if (newNode.val <= curr.val) {
                newNode.next = curr;
                return newNode;
            }
            // 插入元素在链表中间位置
            ListNode curNext = curr.next;
            if (curNext != null && newNode.val > curr.val && newNode.val < curNext.val) {
                newNode.next = curNext;
                curr.next = newNode;
                return baseNode;
            }
            // 插入元素比所有元素都大
            if (curNext == null && curr.val < newNode.val) {
                curr.next = newNode;
                return baseNode;
            }
            curr = curr.next;
        }

        return resNode.next;
    }

    @Test
    public void testgetMaxNodeVal() {
        ListNode node1 = new ListNode(4, null);
        ListNode node2 = new ListNode(2, node1);
        ListNode node3 = new ListNode(1, node2);

        int maxNodeVal = getMaxNodeVal(node3);
        System.out.println(maxNodeVal);
    }

    public int getMaxNodeVal(ListNode node) {
        ListNode tmp = node;
        while (true) {
            if (tmp.next == null) {
                break;
            }
            tmp = tmp.next;

        }
        return tmp.val;
    }

    public int getNodeLen(ListNode node) {
        int len = 0;
        while (node.next != null) {
            len++;
        }
        return len;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    @Test
    public void testlongestCommonPrefix() {
        String[] strs = new String[]{"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix(strs));
    }


    /**
     * 最长公共前缀： https://leetcode.cn/problems/longest-common-prefix/
     * <p>
     * 题解： 找出最短子串，用最短子串和其他子串比较
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        // 循环比较字符串里的每个字符
        int len = strs.length;
        String minLenStr = strs[0];
        // 用set存储数组元素，并取得最小长度的元素，然后用最小元素的字符去比较
        Set<String> set = new HashSet<>();
        for (int i = 0; i < len; i++) {
            String str = strs[i];
            if (str.isEmpty()) {
                return "";
            }
            set.add(str);
            if (str.length() < minLenStr.length()) {
                minLenStr = strs[i];
            }

        }
        set.remove(minLenStr);
        StringBuilder ans = new StringBuilder();
        // 排序，用最小的长度的字符串开始便利
        for (int i = 0; i < minLenStr.length(); i++) {
            char c = minLenStr.charAt(i);
            Iterator<String> iterator = set.iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();
                if (next.charAt(i) != c) {
                    return ans.toString();
                }
            }
            // 符合条件的然后加以
            ans.append(String.valueOf(c));
        }

        return ans.toString();
    }

    /**
     * top: 13  https://leetcode.cn/problems/roman-to-integer/
     *
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        int sum = 0;
        Map<Character, Integer> romanMap = initRomMap();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int value = romanMap.get(s.charAt(i));
            if (i < n - 1 && value < romanMap.get(s.charAt(i + 1))) {
                sum -= value;
            } else {
                sum += value;

            }
        }
        return sum;
    }

    private Map<Character, Integer> initRomMap() {
        return new HashMap<Character, Integer>() {{
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }};
    }


    @Test
    public void testmyAtoi() {
        String str = "4193 with words";
        System.out.println(myAtoi(str));

    }

    public int myAtoi2(String str) {
        // ^\s*[+-]?\d+
        String regx = "^\\s*[+-]?\\d+";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(str);
        if (!matcher.find()) return 0;

        BigInteger ans = new BigInteger(matcher.group(1));
        if (ans.compareTo(new BigInteger(String.valueOf(Integer.MAX_VALUE))) > 0) {
            return Integer.MAX_VALUE;
        }
        if (ans.compareTo(new BigInteger(String.valueOf(Integer.MIN_VALUE))) < 0) {
            return Integer.MIN_VALUE;
        }
        return ans.intValue();


    }

    /**
     * top: 7
     * https://leetcode.cn/problems/string-to-integer-atoi/
     *
     * @param
     * @return
     */
    public int myAtoi(String str) {
        // 匹配以空格开始出现0次或多次，符号出现0次或多次，数字出现1次或多次

        String pattern = "^\\s*([+-]?\\d+)";
        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(str);
        if (!m.find()) return 0;
        System.out.println(m.group());
        BigInteger ans = new BigInteger(m.group(1));
        if (ans.compareTo(new BigInteger(String.valueOf(Integer.MIN_VALUE))) < 0) {
            return Integer.MIN_VALUE;
        }
        if (ans.compareTo(new BigInteger(String.valueOf(Integer.MAX_VALUE))) > 0) {
            return Integer.MAX_VALUE;
        }
        return ans.intValue();

    }

    public boolean checkIsNumber(String str) {
        String regex = "[0-9]+[\\.]?[0-9]*";
        return Pattern.matches(regex, str);
    }


}
