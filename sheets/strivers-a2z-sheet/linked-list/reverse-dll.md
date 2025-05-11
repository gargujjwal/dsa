---
id: reverse-dll
aliases: []
tags: []
layout: default
title: Reverse DLL
---

```java
class Solution {
    public DLLNode reverseDLL(DLLNode head) {
        int n = 1;
        DLLNode last;
        for (last = head; last.next != null; last = last.next, n++);

        DLLNode first = head;
        for (int i = 0; i < n / 2; i++) {
            swap(first, last);
            first = first.next;
            last = last.prev;
        }

        return head;
    }

    private void swap(DLLNode first, DLLNode sec) {
        int temp = first.data;
        first.data = sec.data;
        sec.data = temp;
    }
}
```
