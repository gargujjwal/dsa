---
id: detect-loop-in-ll-ii
aliases: []
tags: []
layout: default
title: Detect Loop in LL II
---

## Optimal Solution

```java
/**
 * Definition for singly-linked list.
 * class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
public class Solution {
  public ListNode detectCycle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;

    boolean hasLoop = false;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;

      if (slow == fast) {
        hasLoop = true;
        break;
      }
    }

    if (!hasLoop) {
      return null;
    }
    slow = head;
    while (slow != fast) {
      slow = slow.next;
      fast = fast.next;
    }

    return slow;
  }
}
```
