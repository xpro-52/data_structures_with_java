package binary_search_tree;

import java.util.Comparator;
import java.util.StringJoiner;

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
            return key.toString() + ": " + value.toString();
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
        size++;
    }

    public V get(K key) {
        Entry<K, V> iter = root;
        while (iter != null) {
            int cpr = comparator.compare(key, iter.key);
            if (cpr == 0) {
                return iter.value;
            }
            iter = cpr < 0 ? iter.left : iter.right;
        }
        return null;    
    }

    @SuppressWarnings("unchecked")
    public V remove(K key) {
        Object[] deleteValue = new Object[1];
        root = remove(key, root, deleteValue);
        return (V) deleteValue[0];
    }

    private Entry<K, V> remove(K key, Entry<K, V> entry, Object[] deleteValue) {  // deleteValue is array of length 1 because it is used as pointer of delete value
        if (entry == null) {
            return entry;
        }
        int cpr = comparator.compare(key, entry.key);
        if (cpr == 0) {
            deleteValue[0] = entry.value;
            entry = deleteEntry(entry);
            size--;
        } else if (cpr < 0) {
            entry.left = remove(key, entry.left, deleteValue);
        } else {
            entry.right = remove(key, entry.right, deleteValue);
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
        StringJoiner sj = new StringJoiner(", ");
        toString(root, sj);
        return "{" + sj.toString() + "}";
    }

    private void toString(Entry<K, V> entry, StringJoiner sj) {
        if (entry != null) {
            toString(entry.left, sj);
            sj.add(entry.toString());
            toString(entry.right, sj);
        }
    }
}
