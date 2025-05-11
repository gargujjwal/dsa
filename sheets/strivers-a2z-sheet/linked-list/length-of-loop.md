---
id: length-of-loop
aliases: []
tags: []
layout: default
title: Length of Loop
---

## Brute Solution

```java
/*
class Node
{
    int data;
    Node next;
    Node(int d) {data = d; next = null; }
}
*/

class Solution {
    // Function to find the length of a loop in the linked list.
    public int countNodesinLoop(Node head) {
        // figure out if there is a loop and add nodes to set
        Set<Node> set = new HashSet<>();
        boolean hasLoop = false;
        Node i;
        for (i = head; i != null; i = i.next) {
            if (set.contains(i)) {
                hasLoop = true;
                break;
            }
            set.add(i);
        }
        // if no loop then return 0
        if (!hasLoop) {
            return 0;
        }
        // figure out the length by iterating in loop again
        int ans = 0;
        Node firstNode = i;
        do {
            ans++;
            i = i.next;
        } while (i != firstNode);

        return ans;
    }
}
```

## Optimal Solution
