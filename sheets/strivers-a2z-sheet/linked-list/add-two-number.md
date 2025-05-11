---
id: add-two-number
aliases: []
tags: []
layout: default
title: Add two Number represented by LL
---

## Optimal Solution

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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // ll to carry the sum
        ListNode sum = new ListNode(0);
        ListNode sumTail = sum;

        ListNode i = l1;
        ListNode j = l2;
        boolean carry = false;;
        while (i != null && j != null) {
            // calculate current digits sum with carry
            int currSum = i.val + j.val;
            if (carry) {
                currSum += 1;
            }

            // if current sum exceeds 9 then carry becomes active
            if (currSum > 9) {
                currSum -= 10;
                carry = true;
            } else {
                carry = false;
            }

            // add the new sum to sum ll
            sumTail.next = new ListNode(currSum);
            sumTail = sumTail.next;

            // iterate forward
            i = i.next;
            j = j.next;
        }

        while (i != null) {
            int currSum = i.val;
            if (carry) {
                currSum += 1;
            }

            if (currSum > 9) {
                currSum -= 10;
                carry = true;
            } else {
                carry = false;
            }
            sumTail.next = new ListNode(currSum);
            sumTail = sumTail.next;

            i = i.next;
        }

        while (j != null) {
            int currSum = j.val;
            if (carry) {
                currSum += 1;
            }

            if (currSum > 9) {
                currSum -= 10;
                carry = true;
            } else {
                carry = false;
            }
            sumTail.next = new ListNode(currSum);
            sumTail = sumTail.next;

            j = j.next;
        }

        if (carry) {
            sumTail.next = new ListNode(1);
        }

        return sum.next;
    }
}
```
