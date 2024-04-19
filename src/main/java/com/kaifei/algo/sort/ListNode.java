package com.kaifei.algo.sort;

public class ListNode {

    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode addAfter(ListNode baseNode, ListNode newNode) {
        ListNode resNode = new ListNode(-1);
        ListNode curr = baseNode;

        while (curr != null){
            // 插入元素比链表第一个元素小
            if (newNode.val <=curr.val) {
                newNode.next = curr;
                return newNode;
            }
            // 插入元素在链表中间位置
            ListNode  curNext= curr.next;
            if (curNext!=null && newNode.val > curr.val && newNode.val< curNext.val) {
                newNode.next = curNext;
                curr.next = newNode;
                return baseNode;
            }
            // 插入元素比所有元素都大
            if (curNext == null && curr.val< newNode.val) {
                curr.next = newNode;
                return baseNode;
            }
            curr = curr.next;
        }

        return resNode.next;
    }


    public static void main(String[] args) {
        ListNode node1 = new ListNode(4, null);
        ListNode node2 = new ListNode(2, node1);
        ListNode node = new ListNode(1, node2);

        ListNode newNode = new ListNode(-1, null);
        ListNode res = addAfter(node, newNode);
        System.out.println(res);

    }
}
