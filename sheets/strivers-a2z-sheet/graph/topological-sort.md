---
id: topological-sort
aliases: []
tags: []
layout: default
title: Topological Sort
---

## Solution

```java
class Solution {
  public static ArrayList<Integer> topoSort(int V, int[][] edges) {
    Deque<Integer> s = new ArrayDeque<>();

    boolean[] visited = new boolean[V];
    List<List<Integer>> graph = buildGraph(V, edges);
    for (int i = 0; i < V; i++) {
      if (!visited[i]) dfs(i, graph, visited, s);
    }

    return new ArrayList<>(s);
  }

  private static void dfs(int src, List<List<Integer>> graph, boolean[] visited, Deque<Integer> s) {
    visited[src] = true;
    for (int neigh : graph.get(src)) {
      if (!visited[neigh]) dfs(neigh, graph, visited, s);
    }
    s.push(src);
  }

  private static List<List<Integer>> buildGraph(int v, int[][] edges) {
    List<List<Integer>> list = new ArrayList<>(v);
    for (int i = 0; i < v; i++) list.add(new ArrayList<>());
    for (int[] edge : edges) list.get(edge[0]).add(edge[1]);
    return list;
  }
}
```

## BFS Way, Kahn's Algo

```java
class Solution {
  public static ArrayList<Integer> topoSort(int V, int[][] edges) {
    List<List<Integer>> graph = buildGraph(V, edges);

    // calculate indegree of all edges
    int[] indegree = new int[V];
    for (int[] edge : edges)
      // edge[0] -> edges[1]
      indegree[edge[1]]++;

    ArrayList<Integer> ans = new ArrayList<>(V);
    // prepare q for bfs
    Deque<Integer> q = new ArrayDeque<>();
    // vertices with 0 indgree go in queue
    for (int i = 0; i < V; i++) if (indegree[i] == 0) q.offer(i);
    while (!q.isEmpty()) {
      int v = q.poll();
      ans.add(v);

      // decrement all indegree of its neighbours
      for (int neigh : graph.get(v)) {
        indegree[neigh]--;
        if (indegree[neigh] == 0) q.offer(neigh);
      }
    }

    return ans;
  }

  private static List<List<Integer>> buildGraph(int v, int[][] edges) {
    List<List<Integer>> list = new ArrayList<>(v);
    for (int i = 0; i < v; i++) list.add(new ArrayList<>());
    for (int[] edge : edges) list.get(edge[0]).add(edge[1]);
    return list;
  }
}
```
