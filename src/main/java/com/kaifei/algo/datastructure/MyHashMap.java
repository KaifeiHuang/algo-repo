package com.kaifei.algo.datastructure;


import java.util.*;


/**
 * HashMap本质有以下组成
 * 1、数组： node数组 Node<K,V>[] table; 默认大小为16， loadfactor=0.75，数组元素超过12就要扩容，扩容时table变为原来table的2倍
 * 2、链表： hash冲突时用链表
 * 3、红黑树：
 * - 红黑树就是为了查找数据快，解决链表查询深度的问题，我们知道红黑树属于平衡二叉树，但是为了保持“平衡”是需要付出代价的，但是该代价所损耗的资源要比遍历线性链表要少，所以当长度大于8的时候，会使用红黑树，如果链表长度很短的话，根本不需要引入红黑树，引入反而会慢。
 * - 链表长度>8,而且桶的数量>64 链表转化为红黑树； 8时根据泊松分布，实验得来，为了获得最小的时间复杂度O(logN)
 * - 树转链表条件：链表节点数量<=6, 红黑树转化为链表
 */
public class MyHashMap<K, V> {


    // node数组
    private Node<K, V>[] table;
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    static final int TREEIFY_THRESHOLD = 8;
    static final int UNTREEIFY_THRESHOLD = 6;


    /**
     * 流程步骤
     *
     * @param
     */
    public void put(K key, V value) {
        // 1、计算数组位置
        int n = table.length;
        int hash = hash(key);
        int index = hash & (n - 1);

        // 2、判断该位置是否有元素
        Node<K, V> p;
        if ((p = table[index]) == null) {
            // 没有元素直接插入
            table[index] = new Node<>(hash, key, value, null);
        }
        else
        {
            // 检查当前位置是否是红黑树，是-> 插入红黑树

            // 检查链表的长度是否超过8。 未超过8，在当前index形成链表; 超过8，转化为红黑树;
        }
    }


    /**
     * @param key
     * @return
     */
    public V get(Object key) {
        Node<K, V> e;
        // 计算hash值
        return (e = getNode(hash(key), key)) == null ? null : e.value;
    }

    private Node<K, V> getNode(int hash, Object key) {
        Node<K, V> first, e;
        // 判断当前hash位置的元素first是否为空，为空则返回null，不为空
        if ((first=table[hash]) != null) {
            // 检查当前位置的hash与计算的hash 以及key是否等于要查询的key
            if (first.hash == hash && first.key == key) {
                return first;
            }
            // 没有直接获取到，说明该位置是红黑树或者链表
            if ((e = first.next)!=null ){
                // 判断是否是红黑树，是返回
                if (first instanceof TreeNode){
                    return ((TreeNode<?, ?>) first).getTreeNode(hash, key);
                }
                // 链表里获取
                do {
                    if (first.hash == hash && first.key == key) {
                        return first;
                    }
                }while ((e = e.next)!= null);
            }

        }
        return null;
    }

    private int hash(Object key) {
        int h;
        /**
         * 算法原理: https://zhuanlan.zhihu.com/p/458305988
         *
         * 目的： 主要原因是保留高16位与低16位的特性，增大散列程度，增大索引的散列程度的目的就是为了减少哈希碰撞
         * https://zhuanlan.zhihu.com/p/458305988
         *
         * hash值其实是一个int类型，二进制位为32位，而HashMap的table数组初始化size为16，取余操作为hashCode & 15 ==> hashCode & 1111 。这将会存在一个巨大的问题，1111只会与hashCode的低四位进行与操作，也就是hashCode的高位其实并没有参与运算，会导很多hash值不同而高位有区别的数，最后算出来的索引都是一样的
         *
         * 举个例子，我假设hashCode为1111110001，那么`1111110001 & 1111 = 0001`，高位发生变化时`1011110001 & 1111 = 0001`，`1001110001 & 1111 = 0001`，也就是说在高位发生变化时，你最后算出来的索引都一样了，这样就会导致很多数据都被放到一个数组里面了，造成性能退化。
         *
         * 为了避免这种情况，HashMap将高16位与低16位进行异或，这样可以保证高位的数据也参与到与运算中来，以增大索引的散列程度，让数据分布得更为均匀 (个人觉得很多博客说的减小哈希碰撞是错误的说法，因为hash碰撞指的是两个hashCode相同，这里显然不是)
         *
         * 为什么用异或，不用 & 或者 | 操作，因为异或可以保证两个数值的特性，&运算使得结果向0靠近， |运算使得结果向1靠近
         *
         * hash值逻辑：
         * 1、计算key的hashcode
         * 2、将hashcode进行Java中无符号右移16位 h>>> 16， 无论正负，都在高位插入0
         * 3、将1和2进行异或运算
         *   做异或的原因：让key的hashcode高位参与运算，让散列更加均匀。  是数组索引的计算是以位运算取余公式是 hash & (size - 1)， size初始为16，hash & 15 即 hash & 1111做与运算，只有低位参与运算，高位没参与，导致容易产生hash冲突，使用异或运算让高位参与运算，散列分布更加均匀。
         *
         * 使用XOR进行值交换
         * 在不使用临时变量的情况下交换两个变量的值，可以使用XOR运算符（^）。
         *
         * a ^= b
         * b ^= a
         * a ^= b
         * 第一步，将 a 设置为原始 a 和 b 的异或结果。
         *
         * 第二步，b 变成 a和原始 b 的异或，等价于原始的 a。
         *
         * 第三步，将 a 设置为步骤1的结果和步骤2的新 b 的异或，即原始的 b。
         *
         * 检查奇偶数
         *
         *
         * 位运算：
         *  与&： 11为1， 其余为0.
         *  无符号友移动： >>>
         *  异或^: 相同为0， 不同为1
         *
         *  15 = 01111
         *
         */
        return key == null ? 0 : (h = key.hashCode()) ^ (h >>> 16);

    }


    static class Node<K, V> implements Map<K, V> {
        // 用key计算hash，也根据hash来查找key的文职
        final int hash;

        // key不修改
        final K key;
        V value;

        // 链表
        Node<K, V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean containsKey(Object key) {
            return false;
        }

        @Override
        public boolean containsValue(Object value) {
            return false;
        }

        @Override
        public V get(Object key) {
            return null;
        }

        @Override
        public V put(K key, V value) {
            return null;
        }

        @Override
        public V remove(Object key) {
            return null;
        }

        @Override
        public void putAll(Map<? extends K, ? extends V> m) {

        }

        @Override
        public void clear() {

        }

        @Override
        public Set<K> keySet() {
            return Set.of();
        }

        @Override
        public Collection<V> values() {
            return List.of();
        }

        @Override
        public Set<Entry<K, V>> entrySet() {
            return Set.of();
        }
    }


    static class TreeNode<K,V> extends Node{
        TreeNode<K,V> parent;  // red-black tree links
        TreeNode<K,V> left;
        TreeNode<K,V> right;
        TreeNode<K,V> prev;    // needed to unlink next upon deletion
        boolean red;

        public TreeNode(int hash, Object key, Object value, Node next) {
            super(hash, key, value, next);
        }


        final TreeNode<K,V> getTreeNode(int h, Object k) {
            return null;
        }



    }

}
