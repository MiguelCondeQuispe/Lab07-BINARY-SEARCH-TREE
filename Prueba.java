package Prueba;

import bstreelinklistinterfgeneric.LinkedBST;

public class Prueba {
    public static void main(String[] args) throws Exception {
        LinkedBST<Integer> bst = new LinkedBST<>();

        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        System.out.println("InOrder:");
        bst.inOrder();

        System.out.println("PreOrder:");
        bst.preOrder();

        System.out.println("PostOrder:");
        bst.postOrder();

        System.out.println("Min: " + bst.findMin());
        System.out.println("Max: " + bst.findMax());

        System.out.println("Altura del nodo 30: " + bst.height(30));
        System.out.println("Amplitud en nivel 2: " + bst.amplitude(2));

        System.out.println("Área del árbol: " + bst.areaBST());

        System.out.println("Dibujo del árbol:");
        bst.drawBST();

        System.out.println("Representación parentética:");
        bst.parenthesize();
    }
}
