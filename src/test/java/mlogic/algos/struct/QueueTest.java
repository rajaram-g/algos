/**
 * 
 */
package mlogic.algos.struct;

import junit.framework.TestCase;

/**
 * @author Rajaram G
 *
 */
public class QueueTest extends TestCase {

	/**
	 * Test method for
	 * {@link mlogic.algos.struct.Queue#enqueue(java.lang.Object)}.
	 */
	public void testEnqueue() {
		Queue<Integer> queue = new Queue<Integer>();
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		assertEquals(new Integer(4), queue.size());
	}

	/**
	 * Test method for {@link mlogic.algos.struct.Queue#dequeue()}.
	 */
	public void testDequeue() {
		Queue<Integer> queue = new Queue<Integer>();
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		assertEquals(new Integer(1), queue.dequeue());
		assertEquals(new Integer(3), queue.size());
		assertEquals(new Integer(2), queue.dequeue());
		assertEquals(new Integer(3), queue.dequeue());
		assertEquals(new Integer(4), queue.dequeue());
		assertEquals(new Integer(0), queue.size());
	}

}
