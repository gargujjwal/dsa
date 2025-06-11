---
id: serialize-and-deserialize-tree
aliases: []
tags: []
layout: deafult
title: Serialize and Deserialize Tree
---

## Memory Intensive Solution, Mine Solution

```java
class Codec {
  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    if (root == null) {
      return String.valueOf(Integer.MAX_VALUE);
    }
    int height = heightOfTree(root);
    transformToCompleteTree(root, height - 1);
    return levelOrderSerialization(root);
  }

  private String levelOrderSerialization(TreeNode root) {
    StringBuffer sb = new StringBuffer();
    Deque<TreeNode> q = new ArrayDeque<>();
    q.offer(root);
    while (!q.isEmpty()) {
      TreeNode n = q.poll();
      sb.append(String.valueOf(n.val));
      sb.append(",");

      if (n.left != null) {
        q.offer(n.left);
      }
      if (n.right != null) {
        q.offer(n.right);
      }
    }
    return sb.deleteCharAt(sb.length() - 1).toString();
  }

  // convert tree into complete tree
  private void transformToCompleteTree(TreeNode root, int height) {
    if (height <= 0) return;

    if (root.left == null) {
      root.left = new TreeNode(Integer.MAX_VALUE);
    }
    if (root.right == null) {
      root.right = new TreeNode(Integer.MAX_VALUE);
    }

    transformToCompleteTree(root.left, height - 1);
    transformToCompleteTree(root.right, height - 1);
  }

  private int heightOfTree(TreeNode root) {
    if (root == null) return 0;
    return 1 + Math.max(heightOfTree(root.left), heightOfTree(root.right));
  }

  private TreeNode fromLevelOrderTraversal(int[] arr, int st) {
    TreeNode root = null;
    if (st < arr.length) {
      root = new TreeNode(arr[st]);

      // insert left child
      root.left = fromLevelOrderTraversal(arr, 2 * st + 1);
      root.right = fromLevelOrderTraversal(arr, 2 * st + 2);
    }

    return root;
  }

  private void removeInvalidNodes(TreeNode root) {
    if (root == null) return;
    if (root.left != null && root.left.val == Integer.MAX_VALUE) root.left = null;
    if (root.right != null && root.right.val == Integer.MAX_VALUE) root.right = null;

    removeInvalidNodes(root.left);
    removeInvalidNodes(root.right);
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    if (data.equals(String.valueOf(Integer.MAX_VALUE))) {
      return null;
    }
    int[] nodes = Arrays.stream(data.split(",")).mapToInt(Integer::parseInt).toArray();

    TreeNode root = fromLevelOrderTraversal(nodes, 0);
    removeInvalidNodes(root);
    return root;
  }
}
```

## Optimal Solution

```java
class Codec {
  private static final String INVALID = "#";
  private static final String DELIMITER = ",";

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    if (root == null) return "";
    StringBuilder sb = new StringBuilder();

    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);
    while (!q.isEmpty()) {
      TreeNode n = q.poll();

      if (n == null) {
        sb.append(INVALID);
        sb.append(DELIMITER);
        continue;
      }

      sb.append(n.val);
      sb.append(DELIMITER);
      q.offer(n.left);
      q.offer(n.right);
    }

    return sb.toString();
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    if (data.isEmpty()) return null;

    Queue<TreeNode> q = new LinkedList<>();
    String[] values = data.split(DELIMITER);
    TreeNode root = new TreeNode(Integer.parseInt(values[0]));
    q.offer(root);

    for (int i = 1; i < values.length; i++) {
      TreeNode parent = q.poll();

      if (!values[i].equals(INVALID)) {
        TreeNode left = new TreeNode(Integer.parseInt(values[i]));
        parent.left = left;
        q.offer(left);
      }

      if (!values[++i].equals(INVALID)) {
        TreeNode right = new TreeNode(Integer.parseInt(values[i]));
        parent.right = right;
        q.offer(right);
      }
    }

    return root;
  }
}
```

### Optimal Solution using Preorder Traversal

```java
class Codec {
  private static final String INVALID = "#";
  private static final String DELIMITER = ",";

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    StringBuilder ans = new StringBuilder();
    preorderTraversal(root, ans);

    // remove last delemiter and return string
    return ans.deleteCharAt(ans.length() - 1).toString();
  }

  private void preorderTraversal(TreeNode root, StringBuilder ans) {
    if (root == null) {
      ans.append(INVALID);
      ans.append(DELIMITER);
      return;
    }

    ans.append(root.val);
    ans.append(DELIMITER);
    preorderTraversal(root.left, ans);
    preorderTraversal(root.right, ans);
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    return preorderTraversalDeserializer(new StringTokenizer(data, DELIMITER));
  }

  private TreeNode preorderTraversalDeserializer(StringTokenizer tokenizer) {
    if (!tokenizer.hasMoreTokens()) {
      return null;
    }

    String token = tokenizer.nextToken();
    if (token.equals(INVALID)) {
      return null;
    }

    TreeNode root = new TreeNode(Integer.parseInt(token));
    root.left = preorderTraversalDeserializer(tokenizer);
    root.right = preorderTraversalDeserializer(tokenizer);
    return root;
  }
}
```
