---
id: shortest-dist
aliases: []
tags: []
layout: default
title: Shortest Distance in Undirected Graph
---

```java
class Solution {
  public int[] shortestPath(ArrayList<ArrayList<Integer>> adj, int src) {
    int[] dist = new int[adj.size()];
    Arrays.fill(dist, (int) 1e9);
    dist[src] = 0;

    Queue<Integer> q = new ArrayDeque<>();
    q.offer(src);
    while (!q.isEmpty()) {
      int v = q.poll();

      for (int neigh : adj.get(v)) {
        int newDist = dist[v] + 1;
        if (newDist < dist[neigh]) {
          dist[neigh] = newDist;
          q.offer(neigh);
        }
      }
    }

    for (int i = 0; i < adj.size(); i++) if (dist[i] == 1e9) dist[i] = -1;
    return dist;
  }
}

```
