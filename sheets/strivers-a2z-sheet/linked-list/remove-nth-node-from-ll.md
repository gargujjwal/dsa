---
id: remove-nth-node-from-ll
aliases: []
tags: []
layout: default
title: remove-nth-node-from-ll
---

## Optimal Position

- We will keep two pointers, fast and slow, fast moves till `n` places
  and stops
- Then we initialize slow to head, then it moves till fast reaches end of ll
- Now slow is at the node before nth node from end

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // move fast to n places
        ListNode fast = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // it means that we are asked to delete head
        if (fast == null) {
            return head.next;
        }

        // move slow node to the node before nth node from end
        ListNode slow = head;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // delete nth node
        slow.next = slow.next.next;
        return head;
    }
}
```
