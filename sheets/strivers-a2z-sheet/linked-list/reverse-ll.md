---
id: reverse-ll
aliases: []
tags: []
layout: default
title: Reverse LL
---

## Iterative Solution

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
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = null;
        ListNode nxt = null;
        while (head != null) {
            nxt = head.next;
            head.next = prev;
            prev = head;
            head = nxt;
        }

        return prev;
    }
}
```

## Recursive Solution

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
  public ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    // reverse the linked list after head
    ListNode newHead = reverseList(head.next);
    // head's next pointer would be pointing to null, so make it point
    // to head
    head.next.next = head;
    // make had point to null, cuz head will be the last node
    head.next = null;
    // return the head received from recursion call
    return newHead;
  }
}
```
