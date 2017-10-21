package mlogic.algos.struct;

public class SortedSinglyLinkedListTest extends SortedLinkedListTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.struct.LinkedListTest#createLinkedList()
	 */
	@Override
	public List<Integer> createLinkedList() {
		return new SortedSinglyLinkedList<Integer>();
	}

}
