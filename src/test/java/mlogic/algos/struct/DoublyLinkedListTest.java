package mlogic.algos.struct;

import mlogic.algos.struct.DoublyLinkedList;
import mlogic.algos.struct.LinkedList;

public class DoublyLinkedListTest extends LinkedListTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.struct.LinkedListTest#createLinkedList()
	 */
	@Override
	public LinkedList<Integer, Integer> createLinkedList() {
		return new DoublyLinkedList<Integer, Integer>();
	}

}
