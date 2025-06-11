---
id: is-bipartite-graph
aliases: []
tags: []
layout: default
title: Is Bipartite Graph
---

## Solution

```java
record Pair(int v, int p) {}

class Solution {
  public boolean isBipartite(int[][] graph) {
    int n = graph.length;
    boolean[] visited = new boolean[n];
    Set<Integer> setA = new HashSet<>();
    Set<Integer> setB = new HashSet<>();

    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        if (!bfs(i, graph, visited, setA, setB)) {
          return false;
        }
      }
    }

    return true;
  }

  private boolean bfs(
      int src, int[][] graph, boolean[] visited, Set<Integer> setA, Set<Integer> setB) {
    visited[src] = true;
    Deque<Pair> q = new ArrayDeque<>();
    q.offer(new Pair(src, -1));
    while (!q.isEmpty()) {
      Pair p = q.poll();

      // figure out which set it belongs to
      if (setB.contains(p.v())) {
        Set<Integer> temp = setA;
        setA = setB;
        setB = temp;
      }

      // always put source node in setA
      setA.add(p.v());

      // put all its neighbours in setB
      for (int neigh : graph[p.v()]) {
        if (neigh == p.p()) continue;
        // since there is an edge between them they cannot belong to same
        // set
        if (setA.contains(neigh)) return false;

        if (visited[neigh]) continue;

        setB.add(neigh);
        visited[neigh] = true;
        q.offer(new Pair(neigh, p.v()));
      }
    }

    return true;
  }
}
```

## Coloring Approach

```java
class Solution {
  public boolean isBipartite(int[][] graph) {
    int n = graph.length;
    // 0 -> not yet visited
    // -1 -> colored
    // 1 -> colored
    int[] colored = new int[n];

    for (int i = 0; i < n; i++) {
      if (colored[i] == 0) {
        if (!dfs(i, graph, colored, 1)) {
          return false;
        }
      }
    }

    return true;
  }

  private boolean dfs(int src, int[][] graph, int[] colored, int color) {
    colored[src] = color;

    // now color the neighbours with alternate color
    for (int v : graph[src]) {
      if (colored[v] == color) return false;
      // not visited and recursion yields false
      if (colored[v] == 0 && !dfs(v, graph, colored, color * -1)) return false;
    }

    return true;
  }
}
```
