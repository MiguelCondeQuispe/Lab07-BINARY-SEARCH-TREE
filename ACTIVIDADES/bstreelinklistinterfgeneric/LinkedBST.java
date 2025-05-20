package bstreelinklistinterfgeneric;

import Exceptions.*;
import bstreeInterface.BinarySearchTree;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {
	
	class Node<E>{
		public E data;
		public Node left;
		public Node right;
		
		public Node(E data) {
			this(data, null, null);
		}
		
		public Node(E data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}
	
	private Node<E> root;
	
	public LinkedBST() {
		this.setRoot(null);
	}
	
	public void insert(E x) throws ItemDuplicated {
		this.setRoot(insertRec(x, this.getRoot()));
	}

	protected Node<E> insertRec(E x, Node<E> actual) throws ItemDuplicated {
		Node<E> res = actual;
		if (actual == null) {
			res = new Node<E>(x);
		}
		else { 
			int resC = actual.data.compareTo(x);
			if (resC == 0 ) {
				throw new ItemDuplicated(x + "esta duplcado");
			}
			if (resC < 0) {
				res.right = insertRec(x, actual.right);
			}
			else {
				res.left = insertRec(x, actual.left);
			}
		}
		return res;
	}

	public E search(E x) throws ItemNotFound {
		Node<E> res = searchRec(x, this.getRoot());
		if (res == null)
			throw new ItemNotFound ("El dato "+ x + "no esta");
		return res.data;
	}
	
	protected Node<E> searchRec(E x, Node<E> n){
		if (n == null) return null;
		else {
			int resC = n.data.compareTo(x);
			if (resC < 0) {
				return searchRec(x, n.right);
			}
			else if (resC > 0){
				return searchRec(x, n.left);
			}
			else return n;
		}
	}

	public void delete(E x) throws ExceptionIsEmpty{
		if (getRoot() == null) {
	        throw new ExceptionIsEmpty("El árbol está vacío");
	    }

	    Node<E> dummy = new Node<E>(null);
	    setRoot(deleteRec(getRoot(), x, dummy));

	    if (dummy.data == null) {
	        throw new ExceptionIsEmpty("El dato " + x + " esta vacio");
	    }

	}
	
	private Node<E> deleteRec(Node<E> nodo, E x, Node<E> eliminado) {
	    if (nodo == null) {
	    	return null;
	    }
	    int cmp = x.compareTo(nodo.data);
	    if (cmp < 0) {
	        nodo.left = deleteRec(nodo.left, x, eliminado);
	    } else if (cmp > 0) {
	        nodo.right = deleteRec(nodo.right, x, eliminado);
	    } else {
	        eliminado.data = nodo.data;
	        if (nodo.left == null && nodo.right == null) {
	            return null;
	        }
	        if (nodo.left == null) {
	        	return nodo.right;
	        }
	        if (nodo.right == null) {
	        	return nodo.left;
	        }
	        Node<E> padreMin = nodo;
	        Node<E> min = nodo.right;
	        while (min.left != null) {
	            padreMin = min;
	            min = min.left;
	        }
	        nodo.data = min.data;
	        if (padreMin == nodo) {
	            nodo.right = min.right;
	        } else {
	            padreMin.left = min.right;
	        }
	    }

	    return nodo;
	}
	private String inOrder() {
	    String texto = "";
	    Node actual = getRoot();
	    Node predecesor;

	    while (actual != null) {
	        if (actual.left == null) {
	            texto += actual.data + " ";
	            actual = actual.right;
	        } else {
	            predecesor = actual.left;
	            while (predecesor.right != null && predecesor.right != actual) {
	                predecesor = predecesor.right;
	            }

	            if (predecesor.right == null) {
	                predecesor.right = actual;
	                actual = actual.left;
	            } else {
	                predecesor.right = null;
	                texto += actual.data + " ";
	                actual = actual.right;
	            }
	        }
	    }

	    if (texto.equals("")) return "";
	    return texto.substring(0, texto.length() - 1);
	}
	
	private String preOrder() {
	    String texto = "";
	    Node actual = getRoot();

	    while (actual != null) {
	        if (actual.left == null) {
	            texto += actual.data + " ";
	            actual = actual.right;
	        } else {
	            Node predecesor = actual.left;
	            while (predecesor.right != null && predecesor.right != actual) {
	                predecesor = predecesor.right;
	            }

	            if (predecesor.right == null) {
	                texto += actual.data + " "; 
	                predecesor.right = actual;
	                actual = actual.left;
	            } else {
	                predecesor.right = null;
	                actual = actual.right;
	            }
	        }
	    }

	    if (texto.equals("")) return "";
	    return texto.substring(0, texto.length() - 1);
	}
	
	public String postOrden(){
		if (this.getRoot() != null) {
			return postOrden(this.getRoot());
		}
		else {
			return "*";
		}
	}
	
	protected String postOrden(Node actual){
		String res = "";
		if (actual.left != null) {
			res += postOrden(actual.left);
		}
		if (actual.right != null) {
			res += postOrden(actual.right);
		}
		return res + actual.data.toString() + ", ";
	}
	
	public boolean isEmpty() {
		return getRoot() == null;
	}
	
	public Node<E> getRoot() {
		return root;
	}

	public void setRoot(Node<E> root) {
		this.root = root;
	}
	
	public E findMinNode(Node<E> n) throws ItemNotFound {
	    if (n == null) {
	    	throw new ItemNotFound("Subárbol nulo");
	    }
	    E dato = n.data;
	    this.search(dato);
	    Node<E> actual = n;
	    while (actual.left != null) {
	        actual = actual.left;
	    }
	    return actual.data;
	}

	public E findMaxNode(Node<E> n) throws ItemNotFound {
	    if (n == null) {
	    	throw new ItemNotFound("Subárbol nulo");
	    }
	    E dato = n.data;
	    this.search(dato); 
	    Node<E> actual = n;
	    while (actual.right != null) {
	        actual = actual.right;
	    }
	    return actual.data;
	}	
	
	public String toString() {
		String resultado = "";
	    Node actual = getRoot();
	    Node predecesor;

	    while (actual != null) {
	        if (actual.left == null) {
	            resultado += actual.data + " ";
	            actual = actual.right;
	        } else {
	            predecesor = actual.left;
	            while (predecesor.right != null && predecesor.right != actual) {
	                predecesor = predecesor.right;
	            }

	            if (predecesor.right == null) {
	                predecesor.right = actual;
	                actual = actual.left;
	            } else {
	                predecesor.right = null;
	                resultado += actual.data + " ";
	                actual = actual.right;
	            }
	        }
	    }

	    if (resultado.equals("")) {
	    	return "";
	    }
	    return resultado.substring(0, resultado.length() - 1);
	}
}