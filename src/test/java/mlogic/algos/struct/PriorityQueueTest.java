package mlogic.algos.struct;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Rajaram G
 *
 */
public class PriorityQueueTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.enqueue(10);
		pq.enqueue(20);
		pq.enqueue(5);
		pq.enqueue(100);
		pq.enqueue(0);
		pq.enqueue(-10);
		pq.enqueue(2000);
		assertTrue(pq.size() == 7);
		assertEquals(new Integer(-10), pq.dequeue());
		assertTrue(pq.size() == 6);
		pq.enqueue(8);
		assertTrue(pq.size() == 7);
		assertEquals(new Integer(0), pq.dequeue());
		assertEquals(new Integer(5), pq.dequeue());
		assertEquals(new Integer(8), pq.dequeue());
		assertTrue(pq.size() == 4);
		assertEquals(new Integer(10), pq.dequeue());
		assertEquals(new Integer(20), pq.dequeue());
		assertTrue(pq.size() == 2);
		assertEquals(new Integer(100), pq.dequeue());
		assertEquals(new Integer(2000), pq.dequeue());
		assertNull(pq.dequeue());
		assertTrue(pq.size() == 0);
	}

}
