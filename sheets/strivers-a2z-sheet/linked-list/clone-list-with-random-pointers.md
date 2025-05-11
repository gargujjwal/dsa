---
id: clone-list-with-random-pointers
aliases: []
tags: []
layout: default
title: Clone List with Random Pointers
---

## Brute Solution

```java
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node ansLL = new Node(0);
        Node tail = ansLL;

        // cpy all except random ptrs
        for (Node i = head; i != null; i = i.next) {
            tail.next = new Node(i.val);
            tail = tail.next;
            map.put(i, tail);
        }
        ansLL = ansLL.next;

        // cpy random ptrs
        for (Node i = head, j = ansLL; i != null; i = i.next, j = j.next) {
            j.random = map.get(i.random);
        }

        return ansLL;
    }
}
```

## Optimal Solution

```java
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        // insert node in betwen
        Node i = head;
        while (i != null) {
            Node newNode = new Node(i.val);
            newNode.next = i.next;
            i.next = newNode;
            i = i.next.next;
        }

        // now assign random pointers
        i = head;
        while (i != null) {
            if (i.random != null) {
                i.next.random = i.random.next;
            }
            i = i.next.next;
        }

        // now create ll while undoing all the changes in org
        // list
        Node ansLL = new Node(1);
        Node tail = ansLL;
        i = head;
        while (i != null) {
            tail.next = i.next;
            tail = tail.next;
            i.next = i.next.next;
            i = i.next;
        }
        tail.next = null;

        return ansLL.next;
    }
}
```
