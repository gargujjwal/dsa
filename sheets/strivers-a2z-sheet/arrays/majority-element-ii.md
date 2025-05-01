---
id: majority-element
aliases: []
tags: []
layout: default
title: Majority Element
---

## Better Solution I, T:O(n) S:O(n)

```python
def majorityElement(self, nums: List[int]) -> List[int]:
    # calc frequency
    count_map: Dict[int, int] = dict()
    for i in nums:
        if i in count_map:
            count_map[i] += 1
        else:
            count_map[i] = 1

    ans: List[int] = list()
    threshold = len(nums) // 3
    for val, cnt in count_map.items():
        if cnt > threshold:
            ans.append(val)

    return ans
```

## Better Solution II, T:O(n) S:O(n)

```python
    def majorityElement(self, nums: List[int]) -> List[int]:
        cnt_map: Dict[int, int] = dict()
        ans: List[int] = list()
        threshold = len(nums) // 3
        for i in nums:
            if i in cnt_map:
                cnt_map[i] += 1
            else:
                cnt_map[i] = 1

            if cnt_map[i] == threshold + 1:
                ans.append(i)

            # maximum ans can consist of is 2 integers
            if len(ans) == 2:
                break

        return ans
```

## Optimal Solution, T:O(n) S:O(1)

Moore's Voting Algorithm

- n/3 majority element dictates that at max there can be 2 elements which occur
  \> n/3 times
- So we write an exact replica of moore's voting algo with two possible candidates
  `first_el`, `sec_el`

```python
def majorityElement(self, nums: List[int]) -> List[int]:
    first_el = {"val": sys.maxsize, "cnt": 0}
    sec_el = {"val": sys.maxsize, "cnt": 0}

    for i in nums:
        if first_el["cnt"] == 0 and i != sec_el["val"]:
            first_el["val"] = i
            first_el["cnt"] = 1
        elif sec_el["cnt"] == 0 and i != first_el["val"]:
            sec_el["val"] = i
            sec_el["cnt"] = 1
        elif i == first_el["val"]:
            first_el["cnt"] += 1
        elif i == sec_el["val"]:
            sec_el["cnt"] += 1
        else:
            # subtract from both cuz u encountered a value which competes both
            # current candidates and there's no reason to favour one over the
            # other
            first_el["cnt"] -= 1
            sec_el["cnt"] -= 1

    # check if the candidates are right
    first_el["cnt"] = 0
    sec_el["cnt"] = 0
    for i in nums:
        if i == first_el["val"]:
            first_el["cnt"] += 1
        elif i == sec_el["val"]:
            sec_el["cnt"] += 1

    ans: List[int] = list()
    threshold = len(nums) // 3
    if first_el["cnt"] > threshold:
        ans.append(first_el["val"])
    if sec_el["cnt"] > threshold:
        ans.append(sec_el["val"])

    return ans
```
