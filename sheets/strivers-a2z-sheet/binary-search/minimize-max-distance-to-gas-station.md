---
id: minimize-max-distance-to-gas-station
aliases: []
tags: []
layout: default
title: Minimize Max Distance to Gas Station
---

## Brute Solution

```python
class Solution:
    def findSmallestMaxDist(self, stations: List[int], k: int) -> float:
        gas_stations_placed = [0] * (len(stations) - 1)

        # run till all gas stations are placed
        for _ in range(k):
            # figure the two gas stations with maximum distance even with
            # new gas stations put in
            max_section = -1
            max_sec_idx = -1
            for i in range(len(stations) - 1):
                diff = stations[i + 1] - stations[i]
                # if new stations are placed in bw these stations then
                # figure out each section's length by dividing it by how many
                # gas stations are put in
                section_length = diff / (gas_stations_placed[i] + 1)
                if section_length > max_section:
                    max_section = section_length
                    max_sec_idx = i

            # place the new gas station at the max distance
            gas_stations_placed[max_sec_idx] += 1

        # find the maximum diff bw gas stations
        ans = -1
        for i in range(len(stations) - 1):
            diff = stations[i + 1] - stations[i]
            section_length = diff / (gas_stations_placed[i] + 1)
            ans = max(ans, section_length)

        return ans
```

## Better Solution

```python
def findSmallestMaxDist(stations: List[int], k: int) -> float:
    gas_stations_placed = [0] * (len(stations) - 1)
    # create max heap of distances between stations
    pq: List[Tuple[int, int]] = list()
    for i in range(len(stations) - 1):
        dist = stations[i + 1] - stations[i]
        heapq.heappush(pq, (-dist, i))

    # run till all gas stations are placed
    for _ in range(k):
        # figure the two gas stations with maximum distance even with
        # new gas stations put in
        _, max_sec_idx = heapq.heappop(pq)
        # place new gas station
        gas_stations_placed[max_sec_idx] += 1
        # add new dist to heap
        init_dist = stations[max_sec_idx + 1] - stations[max_sec_idx]
        new_section_length = init_dist / (gas_stations_placed[max_sec_idx] + 1)
        heapq.heappush(pq, (-new_section_length, max_sec_idx))

    # find the maximum diff bw gas stations
    ans = -pq[0][0]
    return ans
```

## Optimal Solution

```python
class Solution:
    def findSmallestMaxDist(self, stations: List[int], k: int) -> float:
        # find limits
        max_diff = -1
        for i in range(len(stations) - 1):
            diff = stations[i + 1] - stations[i]
            max_diff = max(max_diff, diff)

        left = 0
        right = max_diff
        while right - left > 1e-6:
            mid = (left + right) / 2.0

            if self.count_stations(stations, mid) > k:
                left = mid
            else:
                right = mid

        return right

    def count_stations(self, stations: List[int], max_dist: float) -> int:
        cnt = 0
        for i in range(len(stations) - 1):
            no_of_stations = floor((stations[i + 1] - stations[i]) / max_dist)
            if (stations[i + 1] - stations[i]) / max_dist == no_of_stations * max_dist:
                no_of_stations -= 1

            cnt += no_of_stations

        return cnt
```
