---
id: sort-ll
aliases: []
tags: []
layout: default
title: Sort LL
---

## Merge Sort on LL

```java
public class Solution {
  static class ListNode {
    public int val;
    public ListNode next;

    ListNode(int val) {
      this.val = val;
      this.next = null;
    }

    public String toString() {
      return Integer.valueOf(this.val) + "->" + this.next == null ? "x" : this.next.toString();
    }
  }

  public static ListNode sortList(ListNode head) {
    return mergeSort(head);
  }

  public static ListNode mergeSort(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode beforeMiddle = nodeBeforeMiddleNode(head);
    ListNode mid = beforeMiddle.next;
    beforeMiddle.next = null;
    ListNode leftHead = mergeSort(head);
    ListNode rightHead = mergeSort(mid);
    ListNode sortedList = merge(leftHead, rightHead);

    return sortedList;
  }

  public static ListNode merge(ListNode head, ListNode mid) {
    // new list
    ListNode newList = new Solution.ListNode(-1);
    ListNode tail = newList;
    ListNode i = head;
    ListNode j = mid;

    while (i != null && j != null) {
      ListNode nxt = null;
      if (i.val < j.val) {
        nxt = i.next;
        tail.next = i;
        i = nxt;
      } else {
        nxt = j.next;
        tail.next = j;
        j = nxt;
      }
      tail = tail.next;
      tail.next = null;
    }

    while (i != null) {
      ListNode nxt = null;
      nxt = i.next;
      tail.next = i;
      i = nxt;
      tail = tail.next;
      tail.next = null;
    }

    while (j != null) {
      ListNode nxt = null;
      nxt = j.next;
      tail.next = j;
      j = nxt;
      tail = tail.next;
      tail.next = null;
    }

    return newList.next;
  }

  public static ListNode nodeBeforeMiddleNode(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode slow = head;
    ListNode fast = head;
    fast = fast.next.next;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    return slow;
  }
}
```

## Merge Sort on LL but using middle node function

```java
public class Solution {
  static class ListNode {
    public int val;
    public ListNode next;

    ListNode(int val) {
      this.val = val;
      this.next = null;
    }

    public String toString() {
      return Integer.valueOf(this.val) + "->" + this.next == null ? "x" : this.next.toString();
    }
  }

  public static ListNode sortList(ListNode head) {
    return mergeSort(head);
  }

  public static ListNode mergeSort(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode mid = middleNode(head);
    ListNode midNxt = mid.next;
    mid.next = null;
    ListNode leftHead = mergeSort(head);
    ListNode rightHead = mergeSort(midNxt);
    ListNode sortedList = merge(leftHead, rightHead);

    return sortedList;
  }

  public static ListNode merge(ListNode head, ListNode mid) {
    // new list
    ListNode newList = new Solution.ListNode(-1);
    ListNode tail = newList;
    ListNode i = head;
    ListNode j = mid;

    while (i != null && j != null) {
      ListNode nxt = null;
      if (i.val < j.val) {
        nxt = i.next;
        tail.next = i;
        i = nxt;
      } else {
        nxt = j.next;
        tail.next = j;
        j = nxt;
      }
      tail = tail.next;
      tail.next = null;
    }

    while (i != null) {
      ListNode nxt = null;
      nxt = i.next;
      tail.next = i;
      i = nxt;
      tail = tail.next;
      tail.next = null;
    }

    while (j != null) {
      ListNode nxt = null;
      nxt = j.next;
      tail.next = j;
      j = nxt;
      tail = tail.next;
      tail.next = null;
    }

    return newList.next;
  }

  public static ListNode middleNode(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode slow = head;
    ListNode fast = head.next;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    return slow;
  }

  public static void main(String[] args) {
    Solution.ListNode head = new ListNode(4);
    head.next = new ListNode(2);
    head.next.next = new ListNode(1);
    head.next.next.next = new ListNode(3);
    sortList(head);
  }
}
```
