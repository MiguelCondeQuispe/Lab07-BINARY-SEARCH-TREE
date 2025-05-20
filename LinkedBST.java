package bstreelinklistinterfgeneric;

import bstreeInterface.BinarySearchTree;
import exceptions.*;

import java.util.LinkedList;
import java.util.Queue;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {

    protected static class Node<E> {
        E data;
        Node<E> left, right;

        public Node(E data) {
            this.data = data;
        }
    }

    protected Node<E> root;

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void insert(E data) throws ItemDuplicated {
        root = insert(root, data);
    }

    private Node<E> insert(Node<E> node, E data) throws ItemDuplicated {
        if (node == null) return new Node<>(data);
        int cmp = data.compareTo(node.data);
        if (cmp == 0) throw new ItemDuplicated("Elemento duplicado: " + data);
        if (cmp < 0) node.left = insert(node.left, data);
        else node.right = insert(node.right, data);
        return node;
    }

    @Override
    public boolean search(E data) throws ItemNotFound {
        return searchNode(root, data) != null;
    }

    private Node<E> searchNode(Node<E> node, E data) throws ItemNotFound {
        if (node == null) throw new ItemNotFound("No encontrado: " + data);
        int cmp = data.compareTo(node.data);
        if (cmp == 0) return node;
        if (cmp < 0) return searchNode(node.left, data);
        else return searchNode(node.right, data);
    }

    @Override
    public void delete(E data) throws ItemNotFound, ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Árbol vacío.");
        root = delete(root, data);
    }

    private Node<E> delete(Node<E> node, E data) throws ItemNotFound {
        if (node == null) throw new ItemNotFound("No encontrado: " + data);
        int cmp = data.compareTo(node.data);
        if (cmp < 0) node.left = delete(node.left, data);
        else if (cmp > 0) node.right = delete(node.right, data);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node<E> minNode = findMinNode(node.right);
            node.data = minNode.data;
            node.right = delete(node.right, minNode.data);
        }
        return node;
    }

    @Override
    public E findMin() throws ItemNotFound {
        return findMinNode(root).data;
    }

    private Node<E> findMinNode(Node<E> node) throws ItemNotFound {
        if (node == null) throw new ItemNotFound("Árbol vacío.");
        while (node.left != null) node = node.left;
        return node;
    }

    @Override
    public E findMax() throws ItemNotFound {
        return findMaxNode(root).data;
    }

    private Node<E> findMaxNode(Node<E> node) throws ItemNotFound {
        if (node == null) throw new ItemNotFound("Árbol vacío.");
        while (node.right != null) node = node.right;
        return node;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, sb);
        return sb.toString();
    }

    private void toString(Node<E> node, StringBuilder sb) {
        if (node != null) {
            toString(node.left, sb);
            sb.append(node.data).append(" ");
            toString(node.right, sb);
        }
    }

    @Override
    public void inOrder() {
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node<E> node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }

    @Override
    public void preOrder() {
        preOrder(root);
        System.out.println();
    }

    private void preOrder(Node<E> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    @Override
    public void postOrder() {
        postOrder(root);
        System.out.println();
    }

    private void postOrder(Node<E> node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data + " ");
        }
    }

    @Override
    public void destroyNodes() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Árbol ya está vacío.");
        root = null;
    }

    @Override
    public int countAllNodes() {
        return countNonLeafNodes(root);
    }

    @Override
    public int countNodes() {
        return countNonLeafNodes(root);
    }

    private int countNonLeafNodes(Node<E> node) {
        if (node == null || (node.left == null && node.right == null)) return 0;
        return 1 + countNonLeafNodes(node.left) + countNonLeafNodes(node.right);
    }

    @Override
    public int height(E x) {
        Node<E> node;
        try {
            node = searchNode(root, x);
        } catch (ItemNotFound e) {
            return -1;
        }

        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(node);
        int height = -1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            height++;
            for (int i = 0; i < size; i++) {
                Node<E> current = queue.poll();
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
        }

        return height;
    }

    @Override
    public int amplitude(int level) {
        return countNodesAtLevel(root, level, 0);
    }

    private int countNodesAtLevel(Node<E> node, int target, int currentLevel) {
        if (node == null) return 0;
        if (currentLevel == target) return 1;
        return countNodesAtLevel(node.left, target, currentLevel + 1)
             + countNodesAtLevel(node.right, target, currentLevel + 1);
    }

    @Override
    public int areaBST() {
        return countLeaves(root) * height(root);
    }

    private int height(Node<E> node) {
        if (node == null) return -1;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(node);
        int height = -1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            height++;
            for (int i = 0; i < size; i++) {
                Node<E> current = queue.poll();
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
        }

        return height;
    }

    private int countLeaves(Node<E> node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        return countLeaves(node.left) + countLeaves(node.right);
    }

    @Override
    public void drawBST() {
        drawBST(root, "", true);
    }

    private void drawBST(Node<E> node, String prefix, boolean isTail) {
        if (node != null) {
            System.out.println(prefix + (isTail ? "└── " : "├── ") + node.data);
            drawBST(node.left, prefix + (isTail ? "    " : "│   "), false);
            drawBST(node.right, prefix + (isTail ? "    " : "│   "), true);
        }
    }

    @Override
    public boolean sameArea(LinkedBST<E> other) {
        return this.areaBST() == other.areaBST();
    }

    @Override
    public void parenthesize() {
        parenthesize(root, 0);
    }

    private void parenthesize(Node<E> node, int depth) {
        if (node == null) return;
        System.out.println("  ".repeat(depth) + node.data);
        if (node.left != null || node.right != null) {
            if (node.left != null) parenthesize(node.left, depth + 1);
            if (node.right != null) parenthesize(node.right, depth + 1);
        }
    }
}
