---
id: course-schedule-i
aliases: []
tags: []
layout: default
title: Course Schedule
---

## Solution

```java
class Solution {
  public static boolean canFinish(int numCourses, int[][] prerequisites) {
    List<List<Integer>> graph = buildGraph(numCourses, prerequisites);

    // calculate indegree of all edges
    int[] indegree = new int[numCourses];
    for (int[] edge : prerequisites)
      // edge[0] -> edges[1]
      indegree[edge[1]]++;

    int cnt = 0;
    // prepare q for bfs
    Deque<Integer> q = new ArrayDeque<>();
    // vertices with 0 indgree go in queue
    for (int i = 0; i < numCourses; i++) if (indegree[i] == 0) q.offer(i);
    while (!q.isEmpty()) {
      int v = q.poll();
      cnt++;

      // decrement all indegree of its neighbours
      for (int neigh : graph.get(v)) {
        indegree[neigh]--;
        if (indegree[neigh] == 0) q.offer(neigh);
      }
    }

    return cnt != numCourses;
  }

  private static List<List<Integer>> buildGraph(int v, int[][] edges) {
    List<List<Integer>> list = new ArrayList<>(v);
    for (int i = 0; i < v; i++) list.add(new ArrayList<>());
    for (int[] edge : edges) list.get(edge[0]).add(edge[1]);
    return list;
  }
}
```
