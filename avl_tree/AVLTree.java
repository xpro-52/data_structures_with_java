package avl_tree;

import java.util.Comparator;
import java.util.StringJoiner;

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
            return this.key + ": " + this.value;
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

    public V get(K key) {
        return getEntry(key).value;
    }

    private Entry<K, V> getEntry(K key) {
        Entry<K, V> iter = root;
        while (iter != null) {
            int cpr = comparator.compare(key, iter.key);
            if (cpr == 0) {
                return iter;
            }
            iter = cpr < 0 ? iter.left : iter.right;
        }
        return null;
    }

    public V remove(K key) {
        Entry<K, V> entry = getEntry(key);
        if (entry == null) {
            return null;
        }
        V value = entry.value;
        deleteEntry(entry);
        return value;
    }

    private void deleteEntry(Entry<K, V> entry) {
        Entry<K, V> parent = entry.parent;
        if (entry.left == null && entry.right == null) {
            if (parent == null) {
                root = null;
            } else {
                if (parent.left == entry) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
        } else if (entry.left == null) {
            Entry<K, V> right = entry.right;
            right.parent = parent;
            if (parent == null) {
                root = right;
            } else {
                if (parent.left == entry) {
                    parent.left = right;
                } else {
                    parent.right = right;
                }
            }
        } else if (entry.right == null) {
            Entry<K, V> left = entry.left;
            left.parent = parent;
            if (parent == null) {
                root = left;
            } else {
                if (parent.left == entry) {
                    parent.left = left;
                } else {
                    parent.right = left;
                }
            }
        } else {
            Entry<K, V> left = entry.left;
            Entry<K, V> right = entry.right;
            Entry<K, V> new_right = maxEntry(left);
            new_right.right = right;
            right.parent = new_right;
            left.parent = parent;
            if (parent == null) {
                root = left;
            } else {
                if (parent.left == entry) {
                    parent.left = left;
                } else {
                    parent.right = left;
                }
            }
            adjustBalance(left);
        }
        entry = null;
        size--;
    }

    private Entry<K, V> maxEntry(Entry<K, V> root) {
        while (root.right != null) {
            root = root.right;
        }
        return root;
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

    public int size() {
        return size;
    }

    public void __structure() {  // debug
        StringBuilder sb = new StringBuilder();
        __structure(root, "", "   ", "ROOT: ", sb);
        System.out.println("height: " + height(root));
        System.out.println(sb);
    }

    private void __structure(Entry<K, V> entry, String spacer, String branch, String hand, StringBuilder sb) {
        if (entry == null) {
            return;
        }
        sb.append(spacer).append(hand).append(entry.value.toString()).append(", parent: " + entry.parent).append("\n");
        spacer += branch + " ";
        if(entry.left != null) {
            __structure(entry.left, spacer, "|", "LEFT: ", sb);
        }
        if(entry.right != null) {
            __structure(entry.right, spacer, " ", "RIGHT: ", sb);
        }
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
