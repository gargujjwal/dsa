---
id: count-of-linked-list
aliases: []
tags: []
layout: default
title: Count of Linked List
---

```java
/*
class Node{
    int data;
    Node next;
    Node(int a){  data = a; next = null; }
}*/

class Solution {
    // Function to count nodes of a linked list.
    public int getCount(Node head) {
        int i = 0;
        for (Node start = head; start != null; start = start.next, i++)
            ;
        return i;
    }
}
```
