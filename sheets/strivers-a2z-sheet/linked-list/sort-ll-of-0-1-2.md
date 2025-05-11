---
id: sort-ll-of-0-1-2
aliases: []
tags: []
layout: default
title: Sort LL of 0 1 2
---

## Optimal Solution

```java
/* class Node
{
    int data;
    Node next;
    Node(int data)
    {
        this.data = data;
        next = null;
    }
}*/
class Solution {
    static Node segregate(Node head) {
        // create new ll of each number
        Node zeroHead = new Node(0);
        Node zeroTail = zeroHead;
        Node oneHead = new Node(1);
        Node oneTail = oneHead;
        Node twoHead = new Node(2);
        Node twoTail = twoHead;

        for (Node i = head; i != null;) {
            Node nxt = i.next;
            // add to respective ll
            switch (i.data) {
                case 0:
                    zeroTail.next = i;
                    zeroTail = zeroTail.next;
                    break;
                case 1:
                    oneTail.next = i;
                    oneTail = oneTail.next;
                    break;
                case 2:
                    twoTail.next = i;
                    twoTail = twoTail.next;
            }
            i = nxt;
        }

        // end all ll
        oneTail.next = null;
        twoTail.next = null;

        // if there is no one, then attach twoHead
        if (oneHead.next != null) {
            zeroTail.next = oneHead.next;
        } else {
            zeroTail.next = twoHead.next;
        }

        // connect oneTail to twoHead
        oneTail.next = twoHead.next;

        return zeroHead.next;
    }
}
```
