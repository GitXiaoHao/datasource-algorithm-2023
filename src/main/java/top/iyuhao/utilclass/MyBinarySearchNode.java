package top.iyuhao.utilclass;

/**
 * @author yuhao
 * @time 2024/1/27 13:42
 **/
public class MyBinarySearchNode<K extends Comparable<K>,V> {
    public K key;
    public V val;
    public MyBinarySearchNode<K,V> left;
    public MyBinarySearchNode<K,V> right;

    public MyBinarySearchNode(K key) {
        this.key = key;
    }

    public MyBinarySearchNode(K key, V val, MyBinarySearchNode<K,V> left, MyBinarySearchNode<K,V> right) {
        this.key = key;
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public MyBinarySearchNode(K key, V val) {
        this.key = key;
        this.val = val;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "MyBinarySearchNode{" +
                "key=" + key +
                ", val=" + val +
                '}';
    }
}
