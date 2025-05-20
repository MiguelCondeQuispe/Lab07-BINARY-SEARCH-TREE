package bstreelinklistinterfgeneric;

import Exceptions.ItemDuplicated;
import Exceptions.ItemNotFound;

public class Prueba {

	public static void main(String[] args) throws ItemDuplicated {
		try {
            LinkedBST<Integer> arbol = new LinkedBST<>();
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(70);
            arbol.insert(20);
            arbol.insert(40);
            arbol.insert(60);
            arbol.insert(80);

            System.out.println("Mínimo del árbol: " + arbol.findMinNode(arbol.getRoot()));
            System.out.println("Máximo del árbol: " + arbol.findMaxNode(arbol.getRoot()));

        } catch (ItemNotFound e) {
            System.out.println("Error: " + e.getMessage());
        }
	}
}
