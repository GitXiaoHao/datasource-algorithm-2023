package top.iyuhao.algorithm.binarysearchtree;

import top.iyuhao.utilclass.MyBinarySearchNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yuhao
 * @time 2024/1/27 13:39
 **/
public class BinarySearchTree<K extends Comparable<K>,V> {
    /**
     * 根节点
     */
    MyBinarySearchNode<K,V> root;

    /**
     * 查找关键字对应的值
     *
     * @param key 关键字
     * @return
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        return doGet(root, key).val;
    }

    /**
     * 查找最小关键字对应的值
     *
     * @return
     */
    public MyBinarySearchNode<K, V> min() {
        if (root == null) {
            throw new IllegalArgumentException("Not Data"); //To change body of generated methods, choose Tools | Templates.
        }
        return doMin(root);
    }

    /**
     * 查找最大关键字对应的值
     *
     * @return
     */
    public V max() {
        if (root == null) {
            throw new IllegalArgumentException("Not Data"); //To change body of generated methods, choose Tools | Templates.
        }
        return doMax(root);
    }

    /**
     * 存储关键字和对应值
     *
     * @param key
     * @param val
     */
    public void put(K key, V val) {
        List<MyBinarySearchNode<K, V>> list = doGetAndParent(root, key, true, true);
        MyBinarySearchNode<K, V> parent = list.get(2);
        //没有找到
        if(parent == null){
            root = new MyBinarySearchNode<>(key,val);
            return;
        }
        int res = key.compareTo(parent.key);
        if (res < 0) {
            parent.left = new MyBinarySearchNode<>(key,val);
        }else {
            parent.right = new MyBinarySearchNode<>(key,val);
        }

    }

    /**
     * 获取关键字的后继节点
     * 情况一： 节点有右子树，此时后继节点就是右子树的最小值
     * 情况二： 节点没有右子树，若离他最近的祖先自从右而来
     *
     * @param key
     * @return
     */
    public MyBinarySearchNode<K, V> successor(K key) {
        List<MyBinarySearchNode<K, V>> list = doGetAndParent(root, key, false,false);
        MyBinarySearchNode<K, V> node = list.get(0);
        MyBinarySearchNode<K,V> ancestorFromRight = list.get(1);
        if (node == null) {
            throw new IllegalArgumentException("Not Data");
        }
        // 节点有右子树，此时后继节点就是右子树的最小值
        if (node.right != null) {
            return doMin(node.right);
        }
        //情况二： 节点没有右子树，若离他最近的祖先自从右而来
        return ancestorFromRight;
    }

    /**
     * 获取关键字的前驱节点
     * 情况一：节点有左子树，此时前任就是左子树最大值
     * 情况二：节点没有左子树，若离他最近的、自左而来的祖先就是前任
     */
    public V predecessor(K key) {
        List<MyBinarySearchNode<K, V>> list = doGetAndParent(root, key, true,false);
        MyBinarySearchNode<K, V> node = list.get(0);
        MyBinarySearchNode<K,V> ancestorFromLeft = list.get(1);
        if (node == null) {
            throw new IllegalArgumentException("Not Data");
        }
        //情况一：节点有左子树，此时前任就是左子树最大值
        if (node.left != null) {
            return doMax(node.left);
        }
        //情况二：节点没有左子树，若离他最近的、自左而来的祖先就是前任
        return ancestorFromLeft == null ? null : ancestorFromLeft.val; //离他最近的、自左而来的祖先就是前任
    }

    /**
     * 根据关键字删除
     * 1. 删除节点没有左孩子，将右孩子给parent
     * 2. 删除节点没有有孩子，将左孩子给parent
     * 3. 删除节点左右孩子都没有，已经被涵盖在情况1 2 中，把null 给parent
     * 4. 删除节点左右孩子都有，可以将他的后继节点给parent
     *      - 删除的节点与后继节点相邻，只需给parent
     *      - 删除节点与后继节点不相邻，需要将后继节点的子节点给后继节点的parent  在将后继节点给删除节点的parent
     * @param key
     * @return
     */
    public V delete(K key) {
        List<MyBinarySearchNode<K, V>> list = doGetAndParent(root, key, false, true);
        MyBinarySearchNode<K, V> node = list.get(0);
        MyBinarySearchNode<K,V> parent = list.get(2);
        if (node == null) {
            throw new IllegalArgumentException("Not Data");
        }
        //删除操作
        if (node.left == null) {
            //1. 删除节点没有左孩子，将右孩子给parent
            shift(parent,node,node.right);
        }else if (node.right == null) {
            //2. 删除节点没有有孩子，将左孩子给parent
            shift(parent,node,node.left);
        }else {
            // 被删除节点找后继节点(节点有右子树，此时后继节点就是右子树的最小值)
            MyBinarySearchNode<K,V> successor = doMin(node.right);
            if (successor != node.right) {
                // 删除节点与后继节点不相邻，需要将后继节点的子节点给后继节点的parent  在将后继节点给删除节点的parent
                List<MyBinarySearchNode<K, V>> successorParent = doGetAndParent(root, successor.key, false, true);
                MyBinarySearchNode<K, V> successorParentNode = successorParent.get(2);
                //不可能有左孩子
                shift(successorParentNode,successor,successor.right);
                successor.right = node.right;
            }
            //4. 删除节点左右孩子都有，可以将他的后继节点给parent
            shift(parent,node,successor);
            successor.left = node.left;
        }
        return node.val;
    }

    /**
     * 找到小于key的所有value
     * @param key
     * @return
     */
    public List<V> less(K key){
        List<V> result = new ArrayList<>();
        MyBinarySearchNode<K, V> node = root;
        LinkedList<MyBinarySearchNode<K,V> > stack = new LinkedList<>();
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            }else {
                MyBinarySearchNode<K, V> pop = stack.pop();
                if (pop.key.compareTo(key) < 0) {
                    result.add(pop.val);
                }else {
                    break;
                }
                node = pop.right;
            }
        }
        return result;
    }

    /**
     * 找到大于key的所有value
     * @param key
     * @return
     */
    public List<V> greater(K key){
        List<V> result = new ArrayList<>();
        MyBinarySearchNode<K, V> node = root;
        LinkedList<MyBinarySearchNode<K,V> > stack = new LinkedList<>();
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.right;
            }else {
                MyBinarySearchNode<K, V> pop = stack.pop();
                if (pop.key.compareTo(key) > 0) {
                    result.add(pop.val);
                }else {
                    break;
                }
                node = pop.left;
            }
        }
        return result;
    }

    /**
     * 找到 >= key1 且 <= key2的所有值
     * @param key1
     * @param key2
     * @return
     */
    public List<V> between(K key1,K key2){
        List<V> result = new ArrayList<>();
        MyBinarySearchNode<K, V> node = root;
        LinkedList<MyBinarySearchNode<K,V> > stack = new LinkedList<>();
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            }else {
                MyBinarySearchNode<K, V> pop = stack.pop();
                if (pop.key.compareTo(key1) >= 0 && pop.key.compareTo(key2) <= 0) {
                    result.add(pop.val);
                }else if (pop.key.compareTo(key2) > 0) {
                    break;
                }
                node = pop.right;
            }
        }
        return result;
    }

    private MyBinarySearchNode<K,V> doDelete(MyBinarySearchNode<K,V> node, K key,List<V> result) {
        if (node == null) {
            return null;
        }
        int res = key.compareTo(node.key);
        if (res < 0) {
            node.left = doDelete(node.left, key,result);
            return node;
        }
        if (res > 0) {
            node.right = doDelete(node.right, key,result);
            return node;
        }
        //找到了
        result.add(node.val);
        if (node.left == null) {
            return node.right;
        }
        if (node.right == null) {
            return node.left;
        }
        MyBinarySearchNode<K, V> successor = doMin(node.right);
        successor.right = doDelete(node.right,successor.key,new ArrayList<>());
        successor.left = node.left;
        return successor;
    }

    /**
     * 获取节点及其父节点
     * @param node 当前节点
     * @param key 值
     * @param leftOrRight true为获取自左而来的父节点 false为获取自右而来的父节点
     * @return 0为节点 1为祖先 2为父节点
     */
    private List<MyBinarySearchNode<K,V>> doGetAndParent(MyBinarySearchNode<K,V> node, K key,boolean leftOrRight,boolean ifParent){
        MyBinarySearchNode<K,V> ancestor = null;
        MyBinarySearchNode<K,V> parent = null;
        List<MyBinarySearchNode<K,V>> list = new ArrayList<>();
        int res = 0;
        while (node != null) {
            if (ifParent) {
                parent = node;
            }
            res = key.compareTo(node.key);
            if (res < 0) {
                if (ifParent || !leftOrRight) {
                    ancestor = node;
                }
                node = node.left;
            }else if(res > 0){
                if (ifParent || leftOrRight) {
                    ancestor = node;
                }
                node = node.right;
            }else {
                break;
            }
        }
        list.add(node);
        list.add(ancestor);
        list.add(parent);
        return list;
    }

    private MyBinarySearchNode<K,V> doGet(MyBinarySearchNode<K,V> node, K key) {
        List<MyBinarySearchNode<K, V>> list = doGetAndParent(node, key, true,false);
        return list.get(0); //key为节点 val为parent
    }
    private MyBinarySearchNode<K, V> doMin(MyBinarySearchNode<K,V> node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node;
        }
        return doMin(node.left);
    }
    private V doMax(MyBinarySearchNode<K,V> node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node.val;
        }
        return doMax(node.right);
    }

    /**
     * 托孤方法
     * @param parent 被删除节点的父节点
     * @param node 被删除的节点
     * @param child 被删除节点的子节点
     */
    private void shift(MyBinarySearchNode<K,V> parent,MyBinarySearchNode<K,V> node, MyBinarySearchNode<K,V> child){
        if (parent == null) {
            root = child;
        }else if(node == parent.left){
            parent.left = child;
        }else {
            parent.right = child;
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<String,Integer> tree = new BinarySearchTree<>();
        tree.put("A",1);
        tree.put("B",2);
        tree.put("C",3);
        tree.put("D",4);
        tree.put("E",5);
        tree.put("F",6);
        tree.put("G",7);
        System.out.println(tree.successor("F"));
        System.out.println(tree.predecessor("F"));
        System.out.println(tree.max());
        System.out.println(tree.min());

    }
}
