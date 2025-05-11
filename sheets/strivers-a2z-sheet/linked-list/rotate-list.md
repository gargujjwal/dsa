---
id: rotate-list
aliases: []
tags: []
layout: default
title: Rotate List
---

## Optimal Solution

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
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        int length = 1;
        ListNode tail;
        for (tail = head; tail.next != null; tail = tail.next, length++);

        // bring k to length of ll
        k %= length;
        k = length - k;
        // make a circular ll
        tail.next = head;
        // move head to k places
        while (k-- > 0) {
            head = head.next;
            tail = tail.next;
        }
        // now remove circular property
        tail.next = null;
        return head;
    }
}
```
