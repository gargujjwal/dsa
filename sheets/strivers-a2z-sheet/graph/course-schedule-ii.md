---
id: course-schedule-ii
aliases: []
tags: []
layout: default
title: Course Schedule II
---

## Solution using DFS and Topological Sort

```java
import java.util.*;

class Solution {
  public static int[] findOrder(int numCourses, int[][] prerequisites) {
    List<List<Integer>> graph = buildGraph(numCourses, prerequisites);
    int[] order = new int[numCourses];
    int[] orderIdx = {0};

    boolean[] visited = new boolean[numCourses];
    boolean[] visiting = new boolean[numCourses];
    for (int i = 0; i < numCourses; i++) {
      if (visited[i]) continue;
      if (hasCycle(i, graph, visited, visiting, order, orderIdx)) return new int[0];
    }

    return order;
  }

  private static boolean hasCycle(
      int src,
      List<List<Integer>> graph,
      boolean[] visited,
      boolean[] visiting,
      int[] order,
      int[] orderIdx) {
    visited[src] = true;
    visiting[src] = true;

    for (int v : graph.get(src)) {
      if (visiting[v]) return true;
      if (visited[v]) continue;
      if (hasCycle(v, graph, visited, visiting, order, orderIdx)) return true;
    }

    visiting[src] = false;
    order[orderIdx[0]++] = src;
    return false;
  }

  private static List<List<Integer>> buildGraph(int v, int[][] edges) {
    List<List<Integer>> list = new ArrayList<>(v);
    for (int i = 0; i < v; i++) list.add(new ArrayList<>());
    for (int[] edge : edges) list.get(edge[0]).add(edge[1]);
    return list;
  }
}
```
