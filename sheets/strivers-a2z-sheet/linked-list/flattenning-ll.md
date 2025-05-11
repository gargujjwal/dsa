---
id: flattening-lls
aliases: []
tags: []
layout: default
title: Flattening LL
---

## Brute Solution

```java
class Solution {
    // Function to flatten a linked list
    Node flatten(Node root) {
        // convert ll to array
        List<Integer> arr = new ArrayList<>();
        for (Node i = root; i != null; i = i.next) {
            arr.add(i.data);

            if (i.bottom != null) {
                for (Node j = i.bottom; j != null; j = j.bottom) {
                    arr.add(j.data);
                }
            }
        }

        // sort to get the required order
        Collections.sort(arr);

        // convert array back to ll
        Node ansLL = new Node(-1);
        Node tail = ansLL;
        for (int i: arr) {
            tail.bottom = new Node(i);
            tail = tail.bottom;
        }
        return ansLL.bottom;
    }
}
```

## Better Solution

```java
class Solution {
    // Function to flatten a linked list
    Node flatten(Node root) {
        Node ansLL = new Node(-1);
        Node tail = ansLL;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.data));
        // seed pq with head
        pq.add(root);
        while (pq.size() > 0) {
            Node i = pq.poll();
            // add its adjacent nodes to pq
            if (i.next != null) {
                pq.add(i.next);
            }
            if (i.bottom != null) {
                pq.add(i.bottom);
            }

            // add the current node to ans
            tail.bottom = i;
            tail = tail.bottom;
        }

        // end the ll
        tail.next = null;
        return ansLL.bottom;
    }
}
```

## Better Solution #2

```java
class Solution {
    // Function to flatten a linked list
    Node flatten(Node root) {
        Node ansLL = new Node(-1);
        Node tail = ansLL;

        Node i = root;
        while (i != null) {
            Node prevToMinNode = getNodePrevToMinNode(i);

            if (prevToMinNode == null) {
                // node i is minimum node
                tail.bottom = i;
                tail = tail.bottom;
                if (i.bottom == null) {
                    i = i.next;
                } else {
                    i.bottom.next = i.next;
                    i = i.bottom;
                }
                continue;
            }
            Node minNode = prevToMinNode.next;
            // add the node to ansll
            tail.bottom = minNode;
            tail = tail.bottom;

            // remove min node from ll
            if (minNode.bottom == null) {
                prevToMinNode.next = minNode.next;
            } else {
                minNode.bottom.next = minNode.next;
                prevToMinNode.next = minNode.bottom;
            }
        }

        // end the ll
        tail.next = null;
        tail.bottom = null;
        return ansLL.bottom;
    }

    Node getNodePrevToMinNode(Node head) {
        Node ans = head;
        int min = head.data;
        for (Node i = head; i.next != null; i = i.next) {
            if (i.next.data < min) {
                min = i.next.data;
                ans = i;
            }
        }

        if (ans == head && min == head.data) {
            return null;
        }

        return ans;
    }
}
```

## Optimal Solution

```java
class Solution {
    // Function to flatten a linked list
    Node flatten(Node root) {
        Node i = root;
        while (i != null && i.next != null) {
            Node nxt = i.next.next;
            Node merged = mergeTwoSortedList(i, i.next);
            merged.next = nxt;
            i = merged;
        }

        return i;
    }

    Node mergeTwoSortedList(Node l1, Node l2) {
        Node ansLL = new Node(-1);
        Node tail = ansLL;

        l1.next = null;
        l2.next = null;
        while (l1 != null && l2 != null) {
            if (l1.data < l2.data) {
                tail.bottom = l1;
                l1 = l1.bottom;
            } else {
                tail.bottom = l2;
                l2 = l2.bottom;
            }
            tail = tail.bottom;
        }

        if (l1 != null) {
            tail.bottom = l1;
        }
        if (l2 != null) {
            tail.bottom = l2;
        }

        return ansLL.bottom;
    }
}
```

## Optimal Solution #Recursive Solution

```java
class Solution {
    // Function to flatten a linked list
    Node flatten(Node head) {
        if (head.next == null) {
            return head;
        } else if (head.next.next == null) {
            return mergeTwoSortedList(head, head.next);
        } else {
            Node rightFlattenedLL = flatten(head.next);
            return mergeTwoSortedList(head, rightFlattenedLL);
        }
    }

    Node mergeTwoSortedList(Node l1, Node l2) {
        Node ansLL = new Node(-1);
        Node tail = ansLL;

        l1.next = null;
        l2.next = null;
        while (l1 != null && l2 != null) {
            if (l1.data < l2.data) {
                tail.bottom = l1;
                l1 = l1.bottom;
            } else {
                tail.bottom = l2;
                l2 = l2.bottom;
            }
            tail = tail.bottom;
        }

        if (l1 != null) {
            tail.bottom = l1;
        }
        if (l2 != null) {
            tail.bottom = l2;
        }

        return ansLL.bottom;
    }
}
```
