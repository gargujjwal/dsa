---
id: top-view
aliases: []
tags: []
layout: default
title: Top View
---

## Solution

- Use level iteration

```java
class Node {
  int data;
  Node left;
  Node right;

  Node(int data) {
    this.data = data;
    left = null;
    right = null;
  }
}

class Pair {
  Node node;
  int lvl;

  public Pair(Node node, int lvl) {
    this.node = node;
    this.lvl = lvl;
  }
}

class Solution {

  public static ArrayList<Integer> topView(Node root) {
    Map<Integer, Integer> map = new TreeMap<>();
    Deque<Pair> q = new ArrayDeque<>();
    q.offer(new Pair(root, 0));

    while (!q.isEmpty()) {
      Pair p = q.poll();
      // if we haven't seen it before
      if (!map.containsKey(p.lvl)) {
        map.put(p.lvl, p.node.data);
      }

      if (p.node.left != null) {
        q.offer(new Pair(p.node.left, p.lvl - 1));
      }
      if (p.node.right != null) {
        q.offer(new Pair(p.node.right, p.lvl + 1));
      }
    }

    // create ans from map
    ArrayList<Integer> ans = new ArrayList<>();
    map.entrySet().stream().mapToInt(Map.Entry::getValue).forEach(ans::add);
    return ans;
  }
}
```
