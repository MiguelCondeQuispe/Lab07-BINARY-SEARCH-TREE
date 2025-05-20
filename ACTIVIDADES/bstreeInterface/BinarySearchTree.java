package bstreeInterface;
import Exceptions.*;

public interface BinarySearchTree<E> {
	public void insert(E data) throws ItemDuplicated;
	public E search(E data) throws ItemNotFound;
	public void delete(E data) throws ExceptionIsEmpty;
	public boolean isEmpty();
}
