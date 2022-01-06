package binary_search_tree;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class BinarySearchTree<K, V> {
    static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> left;
        Entry<K, V> right;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            return "{" + key.toString() + ":" + value.toString() + "}";
        }
    }

    private Entry<K, V> root;
    private int size;

    private final Comparator<? super K> comparator;

    public BinarySearchTree(Comparator<? super K> comparator) {
        this.comparator = comparator;
        root = null;
        size = 0;
    }

    public void put(K key, V value) {
        root = put(root, key, value);
        size++;
    }

    private Entry<K, V> put(Entry<K, V> entry, K key, V value) {
        if (entry == null) {
            entry = new Entry<>(key, value);
        } else if (comparator.compare(key, entry.key) < 0) {
            entry.left = put(entry.left, key, value);
        } else {
            entry.right = put(entry.right, key, value);
        }
        return entry;
    }

    public V get(K key) {
        return get(root, key).value;
    }

    private Entry<K, V> get(Entry<K, V> entry, K key) {
        if (entry == null) {
            throw new NoSuchElementException();
        }
        int cmp = comparator.compare(key, entry.key);
        if (cmp == 0) {
            return entry;
        } else if (cmp < 0) {
            return get(entry.left, key);
        } else {
            return get(entry.right, key);
        }
    }

    public void remove(K key) {
        root = remove(root, key);
        size--;
    }

    private Entry<K, V> remove(Entry<K, V> entry, K key) {
        if (entry == null) {
            throw new NoSuchElementException();
        }
        int cmp = comparator.compare(key, entry.key);
        if (cmp == 0) {
            entry = deleteEntry(entry);
        } else if (cmp < 0) {
            entry.left = remove(entry.left, key);
        } else {
            entry.right = remove(entry.right, key);
        }
        return entry;
    }

    private Entry<K, V> deleteEntry(Entry<K, V> entry) {
        if (entry.left == null) {
            if (entry.right == null) {
                return null;
            } else {
                return entry.right;
            }
        } else {
            if (entry.right == null) {
                return entry.left;
            } else {
                Entry<K, V> new_right = maxEntry(entry.left);
                new_right.right = entry.right;
                return entry.left;
            }
        }
    }

    private Entry<K, V> maxEntry(Entry<K, V> root) {
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }

    public int size() {
        return size;
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
}
