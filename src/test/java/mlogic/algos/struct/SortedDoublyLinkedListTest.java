package mlogic.algos.struct;

public class SortedDoublyLinkedListTest extends SortedLinkedListTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.struct.LinkedListTest#createLinkedList()
	 */
	@Override
	public List<Integer> createLinkedList() {
		return new SortedDoublyLinkedList<Integer>();
	}

}
