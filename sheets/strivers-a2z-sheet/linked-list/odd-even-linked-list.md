---
id: odd-even-linked-list
aliases: []
tags: []
layout: default
title: Odd Even Linked List
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
    public ListNode oddEvenList(ListNode head) {
        // create odds and evens linked list
        ListNode odds = new ListNode(Integer.MAX_VALUE);
        ListNode oddsTail = odds;
        ListNode evens = new ListNode(Integer.MAX_VALUE);
        ListNode evensTail = evens;

        boolean isEven = false;
        for (ListNode i = head; i != null; i = i.next) {
            if (isEven) {
                evensTail.next = i;
                evensTail = evensTail.next;
            } else {
                oddsTail.next = i;
                oddsTail = oddsTail.next;
            }
            isEven = !isEven;
        }

        // clean last value of the linked list
        evensTail.next = null;
        oddsTail.next = null;

        // append the evens ll to odd ll and return even ll head
        // remember odds first node is dummy node
        oddsTail.next = evens.next;

        // remember evens first node is dummy node
        return odds.next;
    }
}
```
