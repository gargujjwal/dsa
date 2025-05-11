---
id: insertion-at-position-doubly-linked-list
aliases: []
tags: []
layout: default
title: Insertion at Position Doubly Linked List
---

```java
class Solution {
    // Function to insert a new node at given position in doubly linked list.
    Node addNode(Node head, int p, int x) {
        int i = 0;
        Node temp;
        for (temp = head; i != p; temp = temp.next, i++);

        Node newNode = new Node(x);
        newNode.prev = temp;
        newNode.next = temp.next;

        if (temp.next != null) {
            temp.next.prev = newNode;
        }

        temp.next = newNode;
        return head;
    }
}
```
