---
id: intersection-of-ll
aliases: []
tags: []
layout: default
title: Intersection of LL
---

## Brute Solution

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode l1, ListNode l2) {
        // hash entire l2
        Set<ListNode> set = new HashSet<>();
        for (ListNode i = l2; i != null; i = i.next) {
            set.add(i);
        }

        for (ListNode i = l1; i != null; i = i.next) {
            if (set.contains(i)) {
                return i;
            }
        }

        return null;
    }
}
```

## Better Solution

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode l1, ListNode l2) {
        int n1 = length(l1);
        int n2 = length(l2);

        // l1 should be > l2
        if (n1 < n2) {
            ListNode temp = l1;
            l1 = l2;
            l2 = temp;

            int t = n1;
            n1 = n2;
            n2 = t;
        }

        // iterate till the difference
        for (int i = 0; l1 != null && i != n1 - n2; l1 = l1.next, i++)
            ;

        // now iterate together
        while (l1 != null && l2 != null) {
            if (l1 == l2) {
                return l1;
            }
            l1 = l1.next;
            l2 = l2.next;
        }

        return null;
    }

    public int length(ListNode h) {
        int ans = 0;
        for (; h != null; h = h.next, ans++)
            ;
        return ans;
    }
}
```

## Optimal Solution

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode l1, ListNode l2) {
        ListNode i = l1;
        ListNode j = l2;

        while (i != null || j != null) {
            if (i == null) {
                i = l2;
            }
            if (j == null) {
                j = l1;
            }
            if (i == j) {
                return i;
            }
            i = i.next;
            j = j.next;
        }

        return null;
    }
}
```
