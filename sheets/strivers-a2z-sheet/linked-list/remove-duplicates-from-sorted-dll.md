---
id: remove-duplicates-from-sorted-dll
aliases: []
tags: []
layout: default
title: Remove Duplicates from Sorted DLL
---

```java
class Solution {
    Node removeDuplicates(Node head) {
        Node i = head.next;
        while (i != null) {
            Node nxt = i.next;
            if (i.data == i.prev.data) {
                i.prev.next = nxt;
                if (nxt != null) {
                    nxt.prev = i.prev;
                }
            }
            i = nxt;
        }

        return head;
    }
}
```
