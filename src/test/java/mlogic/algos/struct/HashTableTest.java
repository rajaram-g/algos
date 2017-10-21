package mlogic.algos.struct;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * @author Rajaram G
 *
 */
@RunWith(value = Parameterized.class)
public class HashTableTest {

	private Class classUnderTest;

	@Parameters
	public static Collection getParameters() {
		List<Class> testClasses = new ArrayList<Class>();
		testClasses.add(ChainedHashTableOld.class);
		testClasses.add(ChainedHashTable2.class);
		return testClasses;
	}

	public HashTableTest(Class hashTableClass) {
		this.classUnderTest = hashTableClass;
	}

	/**
	 * Test method for
	 * {@link mlogic.algos.struct.HashTable#put(java.lang.Comparable, java.lang.Object)}.
	 */
	@Test
	public void testPut() {
		HashTable<Integer, Integer> ht = null;
		ht = createHashTable(ht);
		ht.put(0, 0);
		assertEquals(new Integer(1), ht.size());
	}

	private HashTable<Integer, Integer> createHashTable(HashTable<Integer, Integer> ht) {
		try {
			ht = (HashTable<Integer, Integer>) this.classUnderTest.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Error creating hash table");
		}
		return ht;
	}

	/**
	 * Test method for
	 * {@link mlogic.algos.struct.HashTable#get(java.lang.Comparable)}.
	 */
	@Test
	public void testGet() {
		HashTable<Integer, Integer> ht = null;
		ht = createHashTable(ht);
		ht.put(0, 0);
		ht.put(10, 100);
		assertEquals(new Integer(100), ht.get(10));
	}

	/**
	 * Test method for
	 * {@link mlogic.algos.struct.HashTable#remove(java.lang.Comparable)}.
	 */
	@Test(expected = NoSuchElementException.class)
	public void testRemove() {
		HashTable<Integer, Integer> ht = null;
		ht = createHashTable(ht);
		ht.put(0, 0);
		ht.put(10, 100);
		ht.remove(0);
		ht.get(0);
	}

	/**
	 * Test method for {@link mlogic.algos.struct.HashTable#maximum()}.
	 */
	@Test
	public void testMaximum() {
		HashTable<Integer, Integer> ht = null;
		ht = createHashTable(ht);
		ht.put(0, 0);
		ht.put(10, 100);
		ht.put(20, 200);
		assertEquals(new Integer(20), ht.maximum().key);
	}

	/**
	 * Test method for {@link mlogic.algos.struct.HashTable#minimum()}.
	 */
	@Test
	public void testMinimum() {
		HashTable<Integer, Integer> ht = null;
		ht = createHashTable(ht);
		ht.put(0, 0);
		ht.put(10, 100);
		ht.put(20, 200);
		assertEquals(new Integer(0), ht.minimum().key);
	}

	/**
	 * Test method for
	 * {@link mlogic.algos.struct.HashTable#predecessor(java.lang.Comparable)}.
	 */
	@Test
	public void testPredecessor() {
		HashTable<Integer, Integer> ht = null;
		ht = createHashTable(ht);
		ht.put(0, 0);
		ht.put(10, 100);
		ht.put(20, 200);
		assertEquals(new Integer(0), ht.predecessor(10).key);
	}

	/**
	 * Test method for
	 * {@link mlogic.algos.struct.HashTable#successor(java.lang.Comparable)}.
	 */
	@Test
	public void testSuccessor() {
		HashTable<Integer, Integer> ht = null;
		ht = createHashTable(ht);
		ht.put(0, 0);
		ht.put(10, 100);
		ht.put(20, 200);
		assertEquals(new Integer(20), ht.successor(10).key);
	}

}
