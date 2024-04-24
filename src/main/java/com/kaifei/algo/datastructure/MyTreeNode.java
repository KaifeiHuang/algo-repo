package com.kaifei.algo.datastructure;

/**
 * 二叉树： https://turingplanet.org/2020/03/17/%e6%a0%91tree-%e4%ba%8c%e5%88%86%e6%9f%a5%e6%89%be%e6%a0%91binary-search-tree%e3%80%8c%e6%95%b0%e6%8d%ae%e7%bb%93%e6%9e%84%e5%92%8c%e7%ae%97%e6%b3%957%e3%80%8d/
 */
public class MyTreeNode {

    private static TreeNode root;

    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode
                right;


        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }


        /**
         * 树的插入: 在insert中，我们用current来寻找要插入的位置，通过比较数值大小不断地迭代遍历左子树或右子树以找到合适的位置。
         *
         * @param key
         */
        public void insert(int key) {
            // 检查根节点
            if (root == null) {
                root = new TreeNode(key);
                return;
            }

            TreeNode current = root;
            TreeNode parent = null;

            while (true) {
                parent = current;
                if (key < parent.val) {
                    current = parent.left;
                    if (current == null) {
                        parent.left = new TreeNode(key);
                        return;
                    }

                } else if (key > parent.val) {
                    current = parent.right;
                    if (current == null) {
                        parent.right = new TreeNode(key);
                        return;
                    }
                } else {
                    // 二叉树不允许节点的值相同
                    return;
                }
            }
        }


        /**
         * get的逻辑很简单，只需要比对数值大小遍历左右子树即可：
         *
         * @param key
         * @return
         */
        public TreeNode get(int key) {
            TreeNode current = root;
            while (current != null && current.val != key) {
                if (key < current.val) {
                    current = current.left;
                } else if (key > current.val) {
                    current = current.right;
                }
            }
            return current == null ? null : current;
        }


        /**
         * delete比较复杂，我们需要考虑被删除节点的属性进行不同的逻辑操作，在下面的代码中，我们根据三种不同情况对树进行不同的操作:
         * <p>
         * 被删节点是尾节点；
         * 被删节点只有一个孩子；
         * 被删节点有两个孩子：
         *
         * @param key
         * @return
         */
        public boolean delete(int key) {
            return false;
        }


        //*******       树的遍历：   前序，中序，后序                            *******//

        /**
         *  树的遍历：   前序，中序，后序
         *
         *  前序：节 -> 左 -> 右
         *
         *  中序：左 -> 节 -> 右
         *
         *  后序：左 -> 右 -> 节
         *
         *
         *
         * @param root
         */


        /**
         * 前序遍历：在前序遍历中，先访问节点自己，然后访问左子树，最后再访问右子树，对于每个节点迭代此操作：
         *
         * @param root
         */
        public void preOrderTraversal(TreeNode root) {
            if (root == null) return;
            System.out.println(root.val);
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }


        /**
         * 中序遍历： 中序遍历在中序遍历中，先访问左子树上的节点，再访问自己，最后再访问右子树上的节点：
         *
         * @param root
         */
        public void inOrderTraversal(TreeNode root) {
            if (root == null) return;
            inOrderTraversal(root.left);
            System.out.println(root.val);
            inOrderTraversal(root.right);

        }


        /**
         * 后序遍历： 后序遍历中，先访问左子树上的节点，再访问自己，最后再访问右子树上的节点：
         *
         * @param root
         */
        public void postOrderTraversal(TreeNode root) {
            if (root == null) return;
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            System.out.println(root.val);

        }

    }


}
