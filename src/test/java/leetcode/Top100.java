package leetcode;

import com.kaifei.algo.Utils.PrintUtils;
import com.kaifei.algo.sort.ListNode;
import org.junit.Test;

import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Top100 {


    @Test
    public void test() {
        int a = 10;
        System.out.println(-a);
        System.out.println(1 % 2); // 1
        System.out.println(0 % 2); // 0
        System.out.println(3 % 2); // 1
        System.out.println(2 % 2); // 0

        //进位
        System.out.println(1 / 2); // 0
        System.out.println(0 / 2); // 0
        System.out.println(3 / 2); // 1
        System.out.println(2 / 2); // 1

    }

    @Test
    public void test1asteroidCollision() {
        int[] nums = new int[]{1, -6, -3, -5, -80, -3, 3};
        System.out.println(asteroidCollision(nums));

    }

    /**
     * top739： 行星相撞 https://leetcode.cn/problems/asteroid-collision/
     *
     * 时间复杂度： 0（N)
     * int[] asteroids = [1, -6, -3, -5, -80, -3, 3]
     *
     * @param asteroids
     * @return
     */
    public int[] asteroidCollision(int[] asteroids) {
        // 定义栈
        Deque<Integer> stack = new ArrayDeque<>();
        for (int ast: asteroids) {
            if (ast >0) { // 为正表示，同向，直接往栈里push
                stack.push(ast);
            }
            else // 不同方向，行星相撞
            {
                // 相撞
                // 栈顶元素的行星比当前行星小，将栈顶弹出， 比如-8 与栈顶5
                while (!stack.isEmpty() && stack.peek()>0 && stack.peek()<-ast){
                    stack.pop();
                }
                // 对于两个行星大小一样，方向相反，将栈顶弹出
                if (!stack.isEmpty() && stack.peek() == -ast) {
                    stack.pop();
                }
                // 栈顶元素为负的情况，且当前元素, 例如当前元素-10， 栈顶元素-5
                else if(stack.isEmpty() || stack.peek()<0){
                    stack.push(ast);
                }
            }
        }
        // 遍历结果
        int[] res = new int[stack.size()];
        for (int i = res.length-1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }


    @Test
    public void test1dailyTemperatures() {
        int[] nums = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(dailyTemperatures(nums));

    }

    /**
     * 从右到左进行遍历温度数组，用栈来存储每个温度在数组的索引，
     * 每次遍历中，如果arr[i] > t[栈顶元素]， 则将栈顶元素pop， 因为arr[栈里所用]比arr[i]小的，根据题意没有任何意义
     * 每次遍历完，如果stack为空，代表没有比arr[i]大的数， 不为空，栈顶与i的距离则为答案
     * 最后，每次把比i位置 push到栈里
     *
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            // 定义ar[i]左侧所有比arr[i]大的数
            // 栈顶元素： 下一个比arr[i]大的数
            while (!stack.isEmpty() && temperatures[i] >= temperatures[stack.peek()]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                ans[i] = 0;
            } else {
                ans[i] = stack.peek() - i;
            }
            stack.push(i);
        }
        return ans;
    }

    public int[] dailyTemperatures1(int[] temperatures) {
        int[] t = temperatures;
        int n = temperatures.length;
        int[] ans = new int[n];

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = n - 1; i > 0; i--) {
            while (!stack.isEmpty() && t[i] > t[stack.peek()]) stack.pop();

            if (stack.isEmpty()) ans[i] = 0;
            else ans[i] = stack.peek() - i;
            stack.push(i);
        }

        return ans;

    }


    /**
     * https://leetcode.cn/problems/reverse-linked-list-ii/solutions/37247/bu-bu-chai-jie-ru-he-di-gui-di-fan-zhuan-lian-biao/
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // left =1 前N个节点反转
        if (left == 1) return reverseN(head, right);
        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
    }

    ListNode successor = null;

    public ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            // 记录第n+1个节点
            successor = head.next;
            return head;
        }

        ListNode last = reverseN(head.next, n - 1);
        head.next.next = head;
        // 让反转之后的 head 节点和后面的节点连起来
        head.next = successor;
        return last;

    }

    @Test
    public void test1subarraySum() {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(subarraySum(nums, 3));

    }


    /**
     * top206反转链表：https://leetcode.cn/problems/reverse-linked-list/
     * 题解： 递归
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode rerverse_node = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return rerverse_node;
    }


    /**
     * 思路：
     * 1、定义两个指针i， j
     * 2、将j先移动到K的位置
     * 3、i，j用相同速度移动，j要出界时，i的位置就是倒数K个节点，因为i与j相隔k个位置
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode findKthNode(ListNode head, int k) {
        ListNode i = head, j = head;
        for (int c = 0; c < k; c++) {
            j = j.next;
        }
        while (j != null) {
            i = j.next;
            j = j.next;
        }
        return i;
    }

    /**
     * 1、两个指针，同向而行
     * 2、i每次移动一个，j每次移动2个，j是i的2倍，当j到n的最后位置，i就是中间位置
     *
     * @param head
     * @return
     */
    public ListNode linkedlistMiddleNodeMidNode(ListNode head) {
        ListNode i = head, j = head;
        while (j != null && j.next != null) {
            i = j.next;
            j = j.next.next;
        }
        return i;
    }


    /**
     * 题解1： 双指针
     * <p>
     * 题解2： 最小堆解法， 将node数组放入最小heap中，然后heap poll，用一个node接收即可，时间复杂度O(LogK)
     */
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> (a.val - b.val));
        // 初始化heap
        for (ListNode node : lists) {
            if (node != null) {
                heap.offer(node);
            }
        }
        // poll， 用新node接收
        ListNode res = new ListNode(0), cur = res;
        while (!heap.isEmpty()) {
            ListNode top = heap.poll();
            cur.next = top;
            cur = cur.next;
            if (top.next != null) {
                // 再把top的next放入堆里面， 让堆来处理
                heap.offer(top.next);
            }
        }
        return res.next;

    }


    public int findKthLargest1(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int x : nums) {
            if (heap.size() < k || x > heap.peek()) {
                heap.offer(x);
            }
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.peek();

    }


    /**
     * 题解： 先排序，然后nums.length-k就是第K个最大的元素
     */
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];

    }

    public int subarraySum(int[] nums, int k) {
        // 用map来存储， key表示子数组的和，value表示出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        // 初始化
        map.put(0, 1);
        int ans = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {

            sum = sum + nums[i];
            if (map.containsKey(sum - k)) {
                ans += map.get(sum - k);
                //   map.put(nums[i], ans);

            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return ans;

    }

    @Test
    public void test1fourSum() {
        int[] nums = new int[]{1, 0, -1, 0, -2, 2};

        int target = 0;
        List<List<Integer>> lists = fourSum1(nums, target);
        System.out.println(lists);
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        // biz code
        for (int i = 0; i < n; i++) {
            // 正整数，if nums[i] > target，代表后面无条件满足
            if (nums[i] > 0 && nums[i] > target) return res;
            // 去重
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1; j < n; j++) {
                // 去重
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                int left = j + 1;
                int right = n - 1;
                while (right > left) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum > target) {
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else // sum==target 添加
                    {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (right > left && nums[right] == nums[right - 1]) right--;
                        while (right > left && nums[left] == nums[left + 1]) left++;
                        left++;
                        right--;

                    }
                }
            }
        }
        return res;
    }


    public List<List<Integer>> fourSum1(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        // biz code
        for (int i = 0; i < n; i++) {
            // 去重
            if (nums[i] > 0 && nums[i] > target) return res;

            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1; j < n; j++) {

                // 去重
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                int left = j + 1;
                int right = n - 1;
                while (right > left) {
                    // 注意四数相加，有可能溢出，所以用long
                    long sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum < target) {
                        left++;
                    } else if (sum > target) {
                        right--;
                    } else {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        while (right > left && nums[right] == nums[right - 1]) right--;
                        while (right > left && nums[left] == nums[left + 1]) left++;

                        left++;
                        right--;
                    }
                }
            }
        }

        return res;
    }


    @Test
    public void testthreeSum() {
//        int[] nums = new int[]{1, 5, 9, 100};
//        int i = oneClosest(nums, 80);
//        System.out.println("closest num=" + i);

        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> res = threeSum(nums);
        System.out.println(res);

    }

    public List<List<Integer>> threeSum(int[] nums) {
        // 排序 nums
        Arrays.sort(nums);

        // 临界条件判断
        if (nums[0] > 0) return new ArrayList<>();

        // 正常逻辑
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
//        res.add(Arrays.asList(0, 0, 0));
        for (int i = 0; i < n; i++) {
            // 去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1, k = n - 1;
            while (j < k) {
                int tmp = nums[i] + nums[j] + nums[k];
                if (tmp == 0) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    // 去重
                    while (j < k && nums[j] == nums[j + 1]) j++;
                    // 去重
                    while (j < k && nums[k] == nums[k - 1]) k--;
                    j++;
                    k--;

                } else if (tmp > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return res;
    }

    public int oneClosest(int[] nums, int target) {
        int res = 0, minAbs = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            if ((Math.abs(val - target) < minAbs)) {

                minAbs = Math.abs(val - target);
                res = val;
            }

        }
        return res;
    }

    /**
     * top16：最接近的三数之和  https://leetcode.cn/problems/3sum-closest/submissions/527804470/
     * <p>
     * 解法1： 暴力求解。 三层循环，但是要主要每层循环不能起始值一样
     * 时间复杂度：O(n3)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        int minAbs = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    int tmp = nums[i] + nums[j] + nums[k];
                    if (tmp == target) {
                        return target;
                    }
                    if (Math.abs(tmp - target) <= minAbs) {
                        minAbs = Math.abs(tmp - target);
                        res = tmp;
                    }
                }
            }
        }
        return res;
    }

    /**
     * top16：最接近的三数之和  https://leetcode.cn/problems/3sum-closest/submissions/527804470/
     * <p>
     * 解法2： 排序+双指针
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest2(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);

        int minAbs = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < n; i++) {
            int j = i + 1, k = n - 1;
            while (j < k) {
                int tmp = nums[i] + nums[j] + nums[k];
                if (tmp == target) return tmp;
                if (Math.abs(tmp - target) <= minAbs) {
                    minAbs = Math.abs(tmp - target);
                    res = tmp;
                }
                if (tmp > target) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return res;
    }

    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            int value = values[i];
            String symbol = symbols[i];
            while (num >= value) {
                num -= value;
                sb.append(symbol);
            }
            if (num == 0) break;

        }
        return sb.toString();
    }

    /**
     * top11：11. 盛最多水的容器（双指针，清晰图解） https://leetcode.cn/problems/container-with-most-water/solutions/11491/container-with-most-water-shuang-zhi-zhen-fa-yi-do/
     * <p>
     * 题解： 双指针，求最大面积
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1, res = 0;
        while (i < j) {
            res = height[i] < height[j] ?
                    Math.max(res, (j - i) * height[i++]) : // height[i++] 是因为： height[i] < height[j]， 最大面积由height[i]决定
                    Math.max(res, (j - i) * height[j--]);  // height[i++] 是因为： height[i] > height[j]， 最大面积由height[j]决定
        }
        return res;
    }


    @Test
    public void testrestoreIpAddresses() {
        String str = "25525511135";

        List<String> strings = restoreIpAddresses(str);
        System.out.println(strings);
    }

    /**
     * top93: 赋值IP地址  https://leetcode.cn/problems/restore-ip-addresses/solutions/15658/jian-dan-yi-yu-li-jie-de-hui-su-fa-java-by-caipeng/
     * <p>
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


    @Test
    public void teststrStr() {
        String str = "sadbutsad";
        String needle = "sad";
        strStr(str, needle);
    }

    public int strStr1(String haystack, String needle) {

        return haystack.indexOf(needle);

    }

    /**
     * 28. 找出字符串中第一个匹配项的下标   https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/description/
     * 题解： // 字符挨个匹配，匹配失败重新匹配
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        // 字符挨个匹配，匹配失败重新匹配
        int n = haystack.length();
        int m = needle.length();
        char[] hc = haystack.toCharArray();
        char[] dc = needle.toCharArray();
        for (int i = 0; i <= n - m; i++) {
            int a = i, b = 0;
            while (b < m && hc[a] == dc[b]) {
                a++;
                b++;
            }
            // 完全匹配返回i
            if (b == m) return i;
        }
        return -1;
    }


    /**
     * top35: 搜索插入位置 https://leetcode.cn/problems/search-insert-position/description/
     * 题解： 二分法查找，实际就是相当于一层快排
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private int getIndex(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }


    @Test
    public void testaddBinary1() {
        String a = "1111", b = "1111";
        String s = addBinary(a, b);
        System.out.println(s);
    }


    /**
     * top67: 二进制求和  https://leetcode.cn/problems/add-binary/
     * 题解：
     * 1、 找出最大串，和最小串
     * 2、 同时遍历，当lmax >= lmin && lmin >= 0 计算重叠部分，其余直接加即可， 用sb来存放，val%2表示当前各位，val/2表示进位
     * 3、 遍历结束看进位是否为1，为1则追加
     * 4、 sb.reverse来返回结果
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        int la = a.length();
        int lb = b.length();
        String max, min;
        if (la >= lb) {
            max = a;
            min = b;
        } else {
            max = b;
            min = a;
        }
        int lmax = max.length() - 1;
        int lmin = min.length() - 1;

        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (lmax >= 0 || lmin >= 0) {
            int cmax = max.charAt(lmax) - '0';
            int val;
            // 计算重叠部分
            if (lmax >= lmin && lmin >= 0) {
                int cmin = min.charAt(lmin) - '0';
                val = cmax + cmin + carry;
                lmin--;
            } else // 计算超的部分, 直接追加进位即可
            {
                val = cmax + carry;
            }
            // 求当前位
            carry = val / 2;
            //求进位
            sb.append(val % 2);
            lmax--;
        }
        if (carry == 1) sb.append(1);
        return sb.reverse().toString();
    }


    /**
     * top101: https://leetcode.cn/problems/symmetric-tree/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
     * <p>
     * 它们的两个根结点具有相同的值
     * 每个树的右子树都与另一个树的左子树镜像对称
     */
    public boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }

    public boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }

    /**
     * top637. 二叉树的层平均值 https://leetcode.cn/problems/average-of-levels-in-binary-tree/description/
     *
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        // 记录每层节点数
        List<Integer> counts = new ArrayList<>();
        // 记录每层节点数之和
        List<Double> sums = new ArrayList<>();
        dfs(root, 0, counts, sums);
        // 求平均， 遍历和，然后除以层数即为平均值
        List<Double> avgs = new ArrayList<>();
        for (int i = 0; i < sums.size(); i++) {
            avgs.add(sums.get(i) / counts.get(i));
        }
        return avgs;
    }

    private void dfs(TreeNode root, int level, List<Integer> counts, List<Double> sums) {
        if (root == null) {
            return;
        }

        if (level < sums.size()) {
            sums.set(level, sums.get(level) + root.val);
            counts.set(level, counts.get(level) + 1);
        } else {// 只有一个节点
            sums.add(1.0 * root.val);
            counts.add(1);
        }

        dfs(root.left, level + 1, counts, sums);
        dfs(root.right, level + 1, counts, sums);

    }


    /**
     * top94: 二叉树中序遍历 https://leetcode.cn/problems/binary-tree-inorder-traversal/submissions/526604108/
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        bst(res, root);
        return res;
    }

    private void bst(List<Integer> res, TreeNode root) {
        if (root == null) return;

        bst(res, root.left);
        res.add(root.val);
        bst(res, root.right);
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    @Test
    public void testmerge() {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;

        merge(nums1, m, nums2, n);
    }


    /**
     * top88： 合并两个有序数组  https://leetcode.cn/problems/merge-sorted-array/
     * <p>
     * 题解：三指针解决， 用len来存放 len1，len2 大的元素，一次进行比较，比较完之后将len2没有比较的元素全部都copy过去
     * <p>
     * 思路
     * 标签：从后向前数组遍历
     * 因为 nums1 的空间都集中在后面，所以从后向前处理排序的数据会更好，节省空间，一边遍历一边将值填充进去
     * 设置指针 len1 和 len2 分别指向 nums1 和 nums2 的有数字尾部，从尾部值开始比较遍历，同时设置指针 len 指向 nums1 的最末尾，每次遍历比较值大小之后，则进行填充
     * 当 len1<0 时遍历结束，此时 nums2 中海油数据未拷贝完全，将其直接拷贝到 nums1 的前面，最后得到结果数组
     * 时间复杂度：O(m+n)O(m+n)O(m+n)
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int len1 = m - 1;
        int len2 = n - 1;
        int len = m + n - 1;

        while (len1 >= 0 && len2 >= 0) {
            // nums1的前m个数与nums2的n个元素进行比较
            if (nums1[len1] > nums2[len2]) {
                nums1[len] = nums1[len1];
                len1--;
            } else {
                nums1[len] = nums2[len2];
                len2--;
            }
            len--;
        }
        // 表示将nums2数组从下标0位置开始，拷贝到nums1数组中，从下标0位置开始，长度为len2+1
        System.arraycopy(nums2, 0, nums1, 0, len2 + 1);
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low >= high) return;


        // step1: choose pivot
        int pivot = high;

        int leftPointer = low;
        int rightPointer = high;

        // step2: partition
        while (leftPointer < rightPointer) {
            // leftPointer往右侧移动，直到它位置元素比pivot位置大
            while (arr[leftPointer] <= arr[pivot] && leftPointer < rightPointer) {
                leftPointer++;
            }


            // rightPointer往左侧移动，直到它位置元素比pivot位置小
            while (arr[rightPointer] >= arr[pivot] && leftPointer < rightPointer) {
                rightPointer--;
            }

            // 交换lp和rp
            swap(arr, leftPointer, rightPointer);

        }
        // 将lp与pivot进行交换
        swap(arr, leftPointer, pivot);

        quickSort(arr, low, leftPointer - 1);
        quickSort(arr, leftPointer + 1, high);

    }

    private static void swap(int[] arr, int leftPointer, int rightPointer) {
        int temp = arr[rightPointer];
        arr[rightPointer] = arr[leftPointer];
        arr[leftPointer] = temp;
    }

    public void quickSort1(int[] arr, int low, int high) {
        // int pivot
        int n = arr.length;

        int pivot = arr[n - 1];
        int left = low;
        int right = high;
        while (left < right) {
            while (arr[left] <= pivot && left < right) {
                left++;
            }
            while (arr[right] >= pivot && left < right) {
                right--;
            }
            int tmp = arr[right];
            arr[right] = arr[left];
            arr[left] = tmp;
        }
        arr[low] = arr[left];
        arr[left] = pivot;
        quickSort(arr, low, left - 1);
        quickSort(arr, left + 1, high);
        // sort
    }


    @Test
    public void testdeleteDuplicates() {
        ListNode l2 = new ListNode(1, null);
        ListNode l1 = new ListNode(1, l2);
        ListNode l = new ListNode(1, l1);

        ListNode listNode = deleteDuplicates(l);
        System.out.println(listNode);
    }

    /**
     * top83. 删除排序链表中的重复元素   https://leetcode.cn/problems/remove-duplicates-from-sorted-list/description/
     * <p>
     * 题解： 链表问题双指针， head，cur， cur=cur.next来移动位置
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        // 临界条件
        if (head == null || head.next == null) return head;
        // 双指针
        ListNode cur = head;
        while (cur != null) {
            ListNode tmp = cur.next;
            // tmp为空，说明是最后一个node，直接返回
            if (tmp == null) {
                return head;
            }
            // 前一个和后一个的val相同则直接将next指向下一个
            if (cur.val == tmp.val) {
                cur.next = tmp.next;
                continue;
            }
            cur = cur.next;

        }
        return head;
    }


    /**
     * top69: https://leetcode.cn/problems/sqrtx/  x的平方根
     * <p>
     * 题解：// 利用函数变化
     * <p>
     * 时间复杂度： O(1)
     * 空间复杂度： O(1)
     */
    public int mySqrt(int x) {
        // 临界条件：
        if (x == 0) return 0;
        // 利用函数变化
        int ans = (int) Math.exp(0.5 * Math.log(x));
        // 因为是int取整， ans的平方<x -> ans+1; 转为long，是因为计算有可能超出int长度
        return (long) (ans + 1) * (ans + 1) <= x ? ans + 1 : ans;
    }

    @Test
    public void testplusOne() {
        int[] nums = {9, 9, 9};

        int[] ints = plusOne(nums);
        PrintUtils.printIntArray(ints);


    }

    /**
     * top66: 加1： https://leetcode.cn/problems/plus-one/
     * <p>
     * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     * <p>
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     * <p>
     * 示例 1：
     * <p>
     * 输入：digits = [1,2,3]
     * 输出：[1,2,4]
     * 解释：输入数组表示数字 123。
     * digits=[9]  -> [1,0]
     * <p>
     * 题解：
     * 1、+1后数组元素不变
     * 2、+1后数组元素个数+1
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        //+1后数组元素不变
        for (int i = n - 1; i >= 0; i--) {
            digits[i] = digits[i] + 1;
            digits[i] = digits[i] % 10;
            // 无进位，加1结束返回
            if (digits[i] != 0) return digits;
        }
        // +1后数组元素个数+1,其实考虑999999这种情况
        digits = new int[n + 1];
        digits[0] = 1;
        return digits;
    }

    @Test
    public void testjump() {
        int[] nums = {2, 3, 0, 1, 4};
        int jump = jump(nums);

    }

    /**
     * top45： https://leetcode.cn/problems/jump-game-ii/
     * 题解：返回索引0位置跳到最后一个索引的中间的最小步数，每次能跳的最大步数由该索引位置上值决定
     * <p>
     * <p>
     * nums = [2,3,0,1,4] -> 2
     * nums = [2,3,1,1,4] -> 2
     * nums = [1,2,3]     -> 2
     * nums = [3,2,1]     -> 1
     * nums = [1]         -> 1
     */
    public int jump(int[] nums) {

        int end = 0, maxPosition = 0, steps = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxPosition = Math.max(maxPosition, nums[i] + i);
            // 跳到边界，更新边界，步数加一
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;

    }

    @Test
    public void testmultiply() {
        String num1 = "123";
        String num2 = "45";
        multiply1(num1, num2);

    }

    public String multiply1(String s1, String s2) {
        // 临界条件
        if ("0".equals(s1) || "0".equals(s2)) return "0";
        // 竖版求解
        int m = s1.length();
        int n = s2.length();
        int[] ans = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            int a = s1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int b = s2.charAt(j) - '0';
                int sum = ans[i + j + 1] + a * b;
                // 尾数求和
                ans[i + j + 1] = sum % 10;
                // 进位加和
                ans[i + j] += sum / 10;
            }
        }
        // 结果输出
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ans.length; i++) {
            if (i == 0 && ans[i] == 0) continue;
            sb.append(ans[i]);
        }
        return sb.toString();
    }


    /**
     * top43： 43. 字符串相乘  https://leetcode.cn/problems/multiply-strings/description/
     *
     * @param s1
     * @param s2
     * @return
     */
    public String multiply(String s1, String s2) {
        // 临界条件
        if (s1.equals("0") || s2.equals("0")) return "0";
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
