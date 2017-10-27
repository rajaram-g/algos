/**
 * 
 */
package mlogic.algos.struct;

import java.util.Arrays;

import junit.framework.TestCase;

/**
 * @author Rajaram G
 *
 */
public class StackTest extends TestCase {

	/**
	 * Test method for {@link mlogic.algos.struct.Stack#push(java.lang.Object)}.
	 */
	public void testPushAndPop() {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(10);
		stack.push(9);
		stack.push(8);
		assertEquals(new Integer(8), stack.pop());
	}

	/**
	 * Test method for {@link mlogic.algos.struct.Stack#pop()}.
	 */
	public void testPopFromEmptyStack() {
		Stack<Integer> stack = new Stack<Integer>();
		assertEquals(null, stack.pop());
	}

	public void testToString() {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(10);
		stack.push(9);
		stack.push(8);
		assertEquals("[8, 9, 10]", stack.toString());
	}

	public void testToArray() {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(10);
		stack.push(9);
		stack.push(8);
		Integer[] array = new Integer[stack.size()];
		stack.toArray(array);
		assertEquals("[8, 9, 10]", Arrays.toString(array));
	}
}
