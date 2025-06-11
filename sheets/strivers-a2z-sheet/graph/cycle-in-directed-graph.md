---
id: cycle-in-directed-graph
aliases: []
tags: []
layout: default
title: Cycle in Directed Graph
---

## My

```java
class Solution {
  private int orderIdx = 0;

  public int[] findOrder(int numCourses, int[][] prerequisites) {
    if (prerequisites.length == 0) return IntStream.range(0, numCourses).toArray();

    Map<Integer, Set<Integer>> graph = buildGraph(numCourses, prerequisites);
    int[] order = new int[numCourses];

    boolean[] visited = new boolean[numCourses];
    boolean[] pathVisited = new boolean[numCourses];

    for (int i = 0; i < numCourses; i++) {
      if (!visited[i]) {
        if (hasCycle(i, graph, visited, pathVisited, order)) {
          return new int[0]; // cycle detected
        }
      }
    }

    return order;
  }

  private boolean hasCycle(
      int src,
      Map<Integer, Set<Integer>> graph,
      boolean[] visited,
      boolean[] pathVisited,
      int[] order) {

    visited[src] = true;
    pathVisited[src] = true;

    for (int v : graph.get(src)) {
      if (pathVisited[v]) return true;
      if (!visited[v] && hasCycle(v, graph, visited, pathVisited, order)) return true;
    }

    order[orderIdx++] = src;
    pathVisited[src] = false;
    return false;
  }

  private Map<Integer, Set<Integer>> buildGraph(int vertices, int[][] prerequisites) {
    Map<Integer, Set<Integer>> graph = new HashMap<>(vertices);
    for (int i = 0; i < vertices; i++) graph.put(i, new HashSet<>());
    for (int[] pre : prerequisites) graph.get(pre[0]).add(pre[1]);
    return graph;
  }
}
```

## Optimal Solution

```java
class Solution {
  public int[] findOrder(int numCourses, int[][] prerequisites) {
    // build graph
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < numCourses; i++) graph.add(new ArrayList<>());

    for (int[] pre : prerequisites)
      graph.get(pre[1]).add(pre[0]); // course `pre[0]` depends on `pre[1]`

    int[] order = new int[numCourses];
    boolean[] visited = new boolean[numCourses];
    boolean[] visiting = new boolean[numCourses];
    int[] index = {numCourses - 1}; // use reverse fill to avoid reversing at the end

    for (int i = 0; i < numCourses; i++) {
      if (!visited[i]) {
        if (hasCycle(i, graph, visited, visiting, order, index)) {
          return new int[0]; // cycle detected
        }
      }
    }

    return order;
  }

  private boolean hasCycle(int node, List<List<Integer>> graph, boolean[] visited,
                           boolean[] visiting, int[] order, int[] index) {
    visiting[node] = true;

    for (int neighbor : graph.get(node)) {
      if (visiting[neighbor]) return true; // cycle
      if (!visited[neighbor]) {
        if (hasCycle(neighbor, graph, visited, visiting, order, index)) return true;
      }
    }

    visiting[node] = false;
    visited[node] = true;
    order[index[0]--] = node; // store course in reverse order
    return false;
  }
}
```
