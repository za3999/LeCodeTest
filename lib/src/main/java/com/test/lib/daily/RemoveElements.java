package com.test.lib.daily;

import com.test.lib.ListNode;

/**
 * 203. 移除链表元素
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 */

public class RemoveElements {

    public ListNode removeElements(ListNode head, int val) {
        ListNode result = new ListNode(0);
        ListNode beforeTarget = result;
        ListNode target = head;
        beforeTarget.next = target;
        while (target != null) {
            if (target.val == val) {
                beforeTarget.next = target.next;
            } else {
                beforeTarget = target;
            }
            target = target.next;
        }
        return result.next;
    }
}
