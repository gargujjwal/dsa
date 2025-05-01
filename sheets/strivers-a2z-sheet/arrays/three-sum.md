---
id: three-sum
aliases: []
tags: []
layout: default
title: Three Sum
---

## Better Solution

```java
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public List<List<Integer>> threeSum(int[] arr) {
        Set<Integer> set = new HashSet<>();
		Set<List<Integer>> triplets = new HashSet<>();

		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				int thirdDig = -1 * (arr[i] + arr[j]);
				if (set.contains(thirdDig)) {
					List<Integer> triplet = new ArrayList<>(
                        List.of(arr[i], arr[j], thirdDig)
                    );
					triplet.sort(Integer::compare);
					triplets.add(triplet);
				}
				set.add(arr[j]);
			}
			set.clear();
		}
		return new ArrayList<>(triplets);
	}
}
```

## Optimal Solution

- We sort the array array first to keep duplicates together and to allow us
  to take advantage of order of array
- We iterate over the array, then for every i, over the rest of the subarray, we
  use two-sum approach (sorted vaali)

```python
def threeSum(self, arr: List[int]) -> List[List[int]]:
    n = len(arr)

    arr.sort()
    ans: List[List[int]] = list()
    i = 0
    while i < n:
        j = i + 1
        k = n - 1
        while j < k:
            curr_sum = arr[i] + arr[j] + arr[k]

            if curr_sum == 0:
                ans.append([arr[i], arr[j], arr[k]])
                # move both pointers to next unique element from both sides
                j += 1
                while j < k and arr[j] == arr[j - 1]:
                    j += 1
                k -= 1
                while k > j and arr[k] == arr[k + 1]:
                    k -= 1
            elif curr_sum < 0:
                # move j to increase sum
                j += 1
            else:
                # move k to decrease sum
                k -= 1

        # move to next unique el i
        i += 1
        while i < n and arr[i] == arr[i - 1]:
            i += 1

    return ans
```
