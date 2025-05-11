---
id: reverse-in-grp-k
aliases: []
tags: []
layout: default
title: Reverse in Group K
---

## Brute Solution

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        List<ListNode> kGrps = new ArrayList<>();
        // divide in k grps and add to list
        ListNode start = head;
        ListNode end = head;
        int cnt = 0;
        while (end != null) {
            cnt++;
            ListNode nxt = end.next;
            if (cnt != k) {
                end = nxt;
                continue;
            }

            end.next = null;
            // add to list
            kGrps.add(start);
            // reset values for next grp
            start = nxt;
            end = nxt;
            cnt = 0;
        }

        ListNode incmpGrp = null;
        if (start != null) {
            incmpGrp = start;
        }


        // reverse every ll and stich every ll
        ListNode newLL = new ListNode(0);
        ListNode newLLTail = newLL;
        for (int i = 0; i < kGrps.size(); i++) {
            ListNode newHead = reverseList(kGrps.get(i));
            // add to ans ll
            newLLTail.next = newHead;
            // move to the end
            while (newLLTail.next != null) {
                newLLTail = newLLTail.next;
            }
        }

        newLLTail.next = incmpGrp;

        return newLL.next;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = null;
        ListNode nxt = null;
        while (head != null) {
            nxt = head.next;
            head.next = prev;
            prev = head;
            head = nxt;
        }

        return prev;
    }
}
```

## Optimal Solution

### Recursive Solution

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        // base case
        ListNode start = head;
        ListNode end = kthNode(head, k);
        if (end == null) {
            // don't reverse the ll which has less than k elements
            return start;
        }

        // preserve next node
        ListNode nxt = end.next;
        // reverse this ll
        end.next = null;
        ListNode newHead = reverseList(start);
        // end and start would have changed role ie, end is now pointing to
        // start of ll & start is at end ll
        start.next = reverseKGroup(nxt, k);

        return end;
    }

    private ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = null;
        ListNode nxt = null;
        while (head != null) {
            nxt = head.next;
            head.next = prev;
            prev = head;
            head = nxt;
        }

        return prev;
    }

    private ListNode kthNode(ListNode head, int k) {
        ListNode i = head;
        int cnt = 0;
        for (; i != null; i = i.next) {
            cnt++;
            if (cnt == k) {
                break;
            }
        }
        return i;
    }
}
```

### Iterative Solution

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode ans = null;
        ListNode prevStart = null;
        while (true) {
            ListNode start = prevStart == null ? head : prevStart.next;
            ListNode end = kthNode(start, k);

            // if there are less than k elements then don't
            // reverse and our work is done
            if (end == null) {
                break;
            }
            // preserve nxt
            ListNode nxt = end.next;
            // reverse ll [start, end]
            end.next = null;
            reverseList(start);
            // after reversal start is at end of ll
            // and end is at start of ll, so swap 'em
            // to keep their semantic meaning
            ListNode temp = start;
            start = end;
            end = temp;
            // reattach the reversed ll to main ll
            if (prevStart != null) {
                prevStart.next = start;
            } else {
                ans = start;
            }

            prevStart = end;
            end.next = nxt;
        }

        return ans;
    }

    private ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = null;
        ListNode nxt = null;
        while (head != null) {
            nxt = head.next;
            head.next = prev;
            prev = head;
            head = nxt;
        }

        return prev;
    }

    private ListNode kthNode(ListNode head, int k) {
        ListNode i = head;
        int cnt = 0;
        for (; i != null; i = i.next) {
            cnt++;
            if (cnt == k) {
                break;
            }
        }
        return i;
    }
}
```
