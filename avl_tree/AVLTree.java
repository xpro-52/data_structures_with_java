package avl_tree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class AVLTree<K, V> {
    static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> left;
        Entry<K, V> right;
        Entry<K, V> parent;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.parent = null;
        }

        @Override
        public String toString() {
            return "{" + this.key + ":" + this.value + "}";
        }
    }

    private final Comparator<? super K> comparator;
    private int size;
    private Entry<K, V> root;

    public AVLTree(Comparator<? super K> comparator) {
        this.comparator = comparator;
        root = null;
        size = 0;
    }

    public void insert(K key, V value) {
        if (root == null) {
            root = new Entry<>(key, value);
            size = 1;
            return;
        }

        Entry<K, V> iter = root;
        Entry<K, V> parent = null;
        while (iter != null) {
            parent = iter;
            if (comparator.compare(key, iter.key) < 0) {
                iter = iter.left;
            } else {
                iter = iter.right;
            }
        }
        Entry<K, V> entry = new Entry<>(key, value);
        if (comparator.compare(key, parent.key) < 0) {
            parent.left = entry;
        } else {
            parent.right = entry;
        }
        entry.parent = parent;
        size++;
        adjustBalance(entry);
    }

    private void adjustBalance(Entry<K, V> entry) {
        Entry<K, V> iter = entry;
        while (iter != null) {
            if (Math.abs(height(iter.left) - height(iter.right)) > 1) {
                if (iter.right == null) {
                    if (iter.left.right == null) {
                        rotateRight(iter);
                    } else {
                        rotateLeftAndRight(iter.left);
                    }
                } else if (iter.left == null){
                    if (iter.right.left == null) {
                        rotateLeft(iter);
                    } else {
                        rotateRightAndLeft(iter.right);
                    }
                } else {  // left and right are exist
                    if (iter.left.left == entry.parent) {
                        rotateRight(iter);
                    } else if (iter.left.right == entry.parent) {
                        rotateLeftAndRight(iter.left);
                    } else if (iter.right.right == entry.parent) {
                        rotateLeft(iter);
                    } else {
                        rotateRightAndLeft(iter.right);
                    }
                }
            }
            iter = iter.parent;
        }
    }

    public V remove(K key) {

        return null;
    }

    private int height(Entry<K, V> entry) {
        if (entry == null) {
            return 0;
        }
        return Math.max(height(entry.left), height(entry.right)) + 1;
    }

    private void rotateLeft(Entry<K, V> x) {  // insert x, y, z (x < y < z)
        Entry<K, V> parent = x.parent;
        Entry<K, V> y = x.right;
        if (parent == null) {
            root = y;
        } else if (parent.left == x) {
            parent.left = y;
        } else {
            parent.right = y;
        }
        y.parent = parent;
        x.right = y.left;
        if (x.right != null) {
            x.right.parent = x;
        }
        y.left = x;
        x.parent = y;
    }

    private void rotateRight(Entry<K, V> z) {  // insert z, y, x (x < y < z)
        Entry<K, V> parent = z.parent;
        Entry<K, V> y = z.left;
        if (parent == null) {
            root = y;
        } else if (parent.left == z) {
            parent.left = y;
        } else {
            parent.right = y;
        }
        y.parent = parent;
        z.left = y.right;
        if (y.right != null) {
            y.right.parent = z;
        }
        y.right = z;
        z.parent = y;
    }

    private void rotateRightAndLeft(Entry<K, V> z) {  // insert x, z, y (x < y < z)
        Entry<K, V> x = z.parent;
        rotateRight(z);
        rotateLeft(x);
    }

    private void rotateLeftAndRight(Entry<K, V> x) {  // insert z, x, y (x, y, z)
        Entry<K, V> z = x.parent;
        rotateLeft(x);
        rotateRight(z);
    }

    public void display() {
        StringBuilder sb = new StringBuilder();
        display(root, "", "   ", "ROOT: ", sb);
        System.out.println("height: " + height(root));
        System.out.println(sb);
    }

    private void display(Entry<K, V> entry, String spacer, String branch, String hand, StringBuilder sb) {
        if (entry == null)
            return;
        sb.append(spacer).append(hand).append(entry).append("\n");
        spacer += branch + " ";
        if(entry.left != null)
            display(entry.left, spacer, "|", "LEFT: ", sb);
        if(entry.right != null)
            display(entry.right, spacer, " ", "RIGHT: ", sb);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(" ");
        toString(root, sb);
        return sb.append("]").toString();
    }

    private void toString(Entry<K, V> entry, StringBuilder sb) {
        if (entry != null) {
            toString(entry.left, sb);
            sb.append(entry).append(" ");
            toString(entry.right, sb);
        }
    }

    public static void main(String[] args) {
        AVLTree<Integer, Integer> tree = new AVLTree<>((a, b) -> Integer.compare(a, b));
        // tree.insert(40, 40);
        // tree.insert(20, 20);
        // tree.insert(10, 10);
        // tree.insert(25, 25);
        // tree.insert(30, 30);
        // tree.insert(22, 22);
        // tree.insert(50, 50);
        tree.insert(40, 40);
        tree.insert(30, 30);
        tree.insert(50, 50);
        tree.insert(20, 20);
        tree.insert(35, 35);
        tree.insert(22, 22);
        tree.display();
        System.out.println(tree);

    }
}
