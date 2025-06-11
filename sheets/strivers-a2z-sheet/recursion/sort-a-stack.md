---
id: sort-a-stack
aliases: []
tags: []
layout: default
title: Sort a Stack
---

## Right Solution using Recursion

```java
class GfG {
    public Stack<Integer> sort(Stack<Integer> s) {
        if (s.size() == 1) {
            return s;
        }

        // remove top element
        int el = s.pop();
        // sort rest of the stack
        s = sort(s);
        // insert in the sorted stack
        sortedInsert(s, el);

        return s;
    }

    private void sortedInsert(Stack<Integer> s, int x) {
        if (s.isEmpty() || s.peek() < x) {
            s.add(x);
            return;
        }

        // remove top element
        int temp = s.pop();
        sortedInsert(s, x);
        // add it back
        s.add(temp);
    }
}
```
