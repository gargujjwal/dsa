---
id: connected-components-in-an-undirected-graph
aliases: []
tags: []
layout: default
title: Connected Components in an Undirected Graph
---

## BFS Solution

```java
class Solution {
  public ArrayList<ArrayList<Integer>> getComponents(int V, int[][] edges) {
    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    List<List<Integer>> graph = buildGraph(V, edges);

    boolean[] visited = new boolean[V];
    for (int i = 0; i < V; i++) {
      if (!visited[i]) {
        ArrayList<Integer> currPath = new ArrayList<>();
        bfs(i, graph, visited, currPath);
        ans.add(currPath);
      }
    }

    return ans;
  }

  private void bfs(int srcVtx, List<List<Integer>> graph, boolean[] visited, List<Integer> path) {
    Deque<Integer> q = new ArrayDeque<>();
    q.offer(srcVtx);
    visited[srcVtx] = true;

    while (!q.isEmpty()) {
      int v = q.poll();
      path.add(v);
      for (int neighbor : graph.get(v)) {
        if (!visited[neighbor]) {
          visited[neighbor] = true;
          q.offer(neighbor);
        }
      }
    }
  }

  private List<List<Integer>> buildGraph(int v, int[][] edges) {
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
