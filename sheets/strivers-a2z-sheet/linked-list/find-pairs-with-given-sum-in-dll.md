---
id: find-pairs-with-given-sum-in-dll
aliases: []
tags: []
layout: default
title: Find Pairs with Given Sum in DLL
---

## Optimal Solution

```java
class Solution {
    public static ArrayList<ArrayList<Integer>> findPairsWithGivenSum(int target,
                                                                      Node head) {
        Node end;
        for (end = head; end.next != null; end = end.next);

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        while (!((head == end) || (head.prev == end))) {
            int currSum = head.data + end.data;
            if (currSum == target) {
                ans.add(new ArrayList<>(List.of(head.data, end.data)));
                head = head.next;
                end = end.prev;
            } else if (currSum < target) {
                head = head.next;
            } else {
                end = end.prev;
            }
        }

        return ans;
    }
}
```
