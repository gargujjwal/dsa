---
id: delete-all-occurrences-of-k-in-dll
aliases: []
tags: []
layout: default
title: Delete All Occurrences of k in DLL
---

## Solution

```java
/****************************************************************

 Following is the class structure of the Node class:

 class Node {
     public int data;
     public Node next;
     public Node prev;

     Node()
     {
         this.data = 0;
         this.next = null;
         this.prev = null;
     }
     Node(int data)
     {
         this.data = data;
         this.next = null;
         this.prev = null;
     }
     Node(int data, Node next, Node prev)
     {
         this.data = data;
         this.next = next;
         this.prev = prev;
     }
 }

 *****************************************************************/

public class Solution {
    public static Node deleteAllOccurrences(Node head, int k) {
        // remove all starting nodes with data k
        while (head != null && head.data == k) {
            head = head.next;
        }
        if (head == null) {
            return null;
        }
        head.prev = null;

        // now remove all nodes with data k in remaining ll
        for (Node i = head; i != null;) {
            Node nxt = i.next;
            if (i.data == k) {
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
