package mlogic.algos.struct;

public class SinglyLinkedListTest extends LinkedListTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.struct.LinkedListTest#createLinkedList()
	 */
	@Override
	public List<Integer> createLinkedList() {
		return new SinglyLinkedList<Integer>();
	}

}
