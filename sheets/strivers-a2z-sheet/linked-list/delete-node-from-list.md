---
id: delete-node-from-list
aliases: []
tags: []
layout: default
title: Delete Node from List
---

```java
class Solution {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public void deleteNode(ListNode node) {
        ListNode i;
        for (i = node; i.next.next != null; i = i.next) {
            i.val = i.next.val;
        }

        i.next = null;
    }
}
```
