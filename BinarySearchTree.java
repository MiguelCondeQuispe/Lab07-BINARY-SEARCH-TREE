package bstreeInterface;

import exceptions.*;

public interface BinarySearchTree<E extends Comparable<E>> {
    void insert(E data) throws ItemDuplicated;
    void delete(E data) throws ItemNotFound, ExceptionIsEmpty;
    boolean search(E data) throws ItemNotFound;
    boolean isEmpty();
    String toString();
    void inOrder();
    void preOrder();
    void postOrder();
    E findMin() throws ItemNotFound;
    E findMax() throws ItemNotFound;
    void destroyNodes() throws ExceptionIsEmpty;
    int countAllNodes();
    int countNodes();
    int height(E x);
    int amplitude(int level);
    int areaBST();
    void drawBST();
    boolean sameArea(bstreelinklistinterfgeneric.LinkedBST<E> other);
    void parenthesize();
}
