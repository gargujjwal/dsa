---
id: number-of-provinces
aliases: []
tags: []
layout: default
title: Number of Provinces
---

## Solution

```java
class Solution {
  public int findCircleNum(int[][] graph) {
    int cnt = 0;

    boolean[] visited = new boolean[graph.length];
    for (int i = 0; i < graph.length; i++) {
      if (!visited[i]) {
        cnt++;
        dfs(i, graph, visited);
      }
    }

    return cnt;
  }

  private void dfs(int srcVtx, int[][] graph, boolean[] visited) {
    visited[srcVtx] = true;
    for (int i = 0; i < graph.length; i++)
      if (graph[srcVtx][i] == 1 && !visited[i]) dfs(i, graph, visited);
  }
}
```
