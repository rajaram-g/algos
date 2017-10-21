package mlogic.algos.struct;

import mlogic.algos.struct.LinkedList;
import mlogic.algos.struct.SinglyLinkedListOld;

public class SinglyLinkedListTest extends LinkedListTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.struct.LinkedListTest#createLinkedList()
	 */
	@Override
	public LinkedList<Integer, Integer> createLinkedList() {
		return new SinglyLinkedListOld<Integer, Integer>();
	}

}
