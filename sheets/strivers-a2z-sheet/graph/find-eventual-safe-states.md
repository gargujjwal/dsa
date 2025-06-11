---
id: find-eventual-safe-states
aliases: []
tags: []
layout: default
title: Find Evential Safe Nodes
---

## Solution

```java
class Solution {
  public List<Integer> eventualSafeNodes(int[][] graph) {
    int n = graph.length;
    boolean[] visited = new boolean[n];
    boolean[] visiting = new boolean[n];
    boolean[] checked = new boolean[n];

    for (int i = 0; i < n; i++)
      if (!visited[i]) dfs(i, graph, visiting, visited, checked);

    List<Integer> ans = new ArrayList<>();
    for (int i = 0; i < n; i++) if (checked[i]) ans.add(i);
    return ans;
  }

  private boolean dfs(
      int src, int[][] graph, boolean[] visiting, boolean[] visited, boolean[] checked) {
    if (visiting[src]) return false; // cycle detected
    if (visited[src]) return checked[src]; // return previously computed result

    visiting[src] = true;

    for (int neighbor : graph[src])
      if (!dfs(neighbor, graph, visiting, visited, checked)) {
        visiting[src] = false;
        return false;
      }

    visiting[src] = false;
    visited[src] = true;
    checked[src] = true;
    return true;
  }
}
```
