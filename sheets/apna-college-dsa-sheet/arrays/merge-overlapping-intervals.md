---
id: merge-overlapping-intervals
aliases: []
tags: []
layout: default
title: Merge Overlapping Intervals
---

## Optimal Solution

Sort the intervals on the basis of first index and then merge them

```python
from typing import List

Intervals = List[List[int]]


class Solution:
    def mergeOverlap(self, intervals: Intervals) -> Intervals:
        # sort the intervals based on starting of interval
        intervals.sort(key=lambda i: i[0])

        new_intervals: Intervals = list()
        curr_interval = intervals[0]
        for interval in intervals:
            # extend the curr_interval if condition matches
            if interval[0] <= curr_interval[1]:
                curr_interval[1] = max(curr_interval[1], interval[1])
            # we have a new interval which can not be merged in prev interval
            else:
                new_intervals.append(curr_interval)
                curr_interval = interval

        # check if new_interval is empty
        if len(new_intervals) == 0:
            new_intervals.append(curr_interval)
        # check for the last interval manually, if we can merge the last interval
        elif curr_interval[1] <= new_intervals[-1][0]:
            new_intervals[-1][1] = max(new_intervals[-1][0], curr_interval[1])
        else:
            new_intervals.append(curr_interval)

        return new_intervals
```
