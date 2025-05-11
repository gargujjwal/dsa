---
id: detect-loop-in-ll
aliases: []
tags: []
layout: default
title: Detect Loop in LL
---

## Optimal Solution

```java
public class Solution {

  public boolean hasCycle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;

    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;

      if (slow == fast) {
        return true;
      }
    }

    return false;
  }
}
```
