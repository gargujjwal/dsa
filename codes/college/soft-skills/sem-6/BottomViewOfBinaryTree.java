import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class Node {
    int data;
    Node left;
    Node right;

    public Node(final int data) {
        this.data = data;
    }

}

class Pair<T, V> {
    T first;
    V sec;

    public Pair(T first, V sec) {
        this.first = first;
        this.sec = sec;
    }

}

/**
 * BottomViewOfBinaryTree
 */
public class BottomViewOfBinaryTree {

    public List<Integer> solution(Node root) {
        final Map<Integer, Integer> view = new TreeMap<>();

        Deque<Pair<Integer, Node>> q = new ArrayDeque<>();
        q.offer(new Pair<>(0, root));

        while (!q.isEmpty()) {
            Pair<Integer, Node> currPair = q.poll();
            Node node = currPair.sec;
            int col = currPair.first;

            view.put(col, node.data);

            if (node.left != null) {
                q.offer(new Pair<>(col - 1, node.left));
            }
            if (node.right != null) {
                q.offer(new Pair<>(col + 1, node.right));
            }

        }

        return new ArrayList<>(view.values());
    }

    public List<Integer> topView(Node root) {
        final Map<Integer, Integer> view = new TreeMap<>();

        Deque<Pair<Integer, Node>> q = new ArrayDeque<>();
        q.offer(new Pair<>(0, root));

        while (!q.isEmpty()) {
            Pair<Integer, Node> currPair = q.poll();
            Node node = currPair.sec;
            int col = currPair.first;

            view.putIfAbsent(col, node.data);

            if (node.left != null) {
                q.offer(new Pair<>(col - 1, node.left));
            }
            if (node.right != null) {
                q.offer(new Pair<>(col + 1, node.right));
            }

        }

        return new ArrayList<>(view.values());
    }
}
