---
id: unidirected-graph-cycle
aliases: []
tags: []
layout: default
title: Unidirected graph cycle
---

## Solution BFS

```java
class Pair {
  int node;
  int parent;

  public Pair(int node, int parent) {
    this.node = node;
    this.parent = parent;
  }
}

class Solution {
  public static boolean isCycle(int v, int[][] edges) {
    List<List<Integer>> graph = buildGraph(v, edges);
    boolean[] visited = new boolean[v];

    for (int i = 0; i < v; i++)
      if (!visited[i] && hasCycleBfs(graph, i, visited))
        return true;

    return false;
  }

  private static boolean hasCycleBfs(List<List<Integer>> graph, int srcVtx, boolean[] visited) {
    Deque<Pair> q = new ArrayDeque<>();
    visited[srcVtx] = true;
    q.offer(new Pair(srcVtx, -1));

    while (!q.isEmpty()) {
      Pair p = q.poll();

      for (int neighbour : graph.get(p.node)) {
        if (neighbour == p.parent) continue;

        // if we have already seen the node then we have a cycle
        if (visited[neighbour]) return true;
        else {
          visited[neighbour] = true;
          q.offer(new Pair(neighbour, p.node));
        }
      }
    }
    return false;
  }

  private static List<List<Integer>> buildGraph(int v, int[][] edges) {
    List<List<Integer>> list = new ArrayList<>(v);
    for (int i = 0; i < v; i++) list.add(new ArrayList<>());

    for (int[] edge : edges) {
      list.get(edge[0]).add(edge[1]);
      list.get(edge[1]).add(edge[0]);
    }

    return list;
  }
}
```

## Solution DFS

```java
class Solution {
  public static boolean isCycle(int v, int[][] edges) {
    List<List<Integer>> graph = buildGraph(v, edges);
    boolean[] visited = new boolean[v];

    for (int i = 0; i < v; i++) {
      if (!visited[i] && hasCycleDfs(graph, i, -1, visited)) return true;
    }

    return false;
  }

  private static boolean hasCycleDfs(
      List<List<Integer>> graph, int srcVtx, int parent, boolean[] visited) {
    visited[srcVtx] = true;
    for (int neighbour : graph.get(srcVtx)) {
      if (neighbour == parent) continue;
      if (visited[neighbour] || hasCycleDfs(graph, neighbour, srcVtx, visited)) return true;
    }
    return false;
  }

  private static List<List<Integer>> buildGraph(int v, int[][] edges) {
    List<List<Integer>> list = new ArrayList<>(v);
    for (int i = 0; i < v; i++) list.add(new ArrayList<>());

    for (int[] edge : edges) {
      list.get(edge[0]).add(edge[1]);
      list.get(edge[1]).add(edge[0]);
    }

    return list;
  }
}
```
