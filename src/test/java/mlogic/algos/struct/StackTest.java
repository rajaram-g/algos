/**
 * 
 */
package mlogic.algos.struct;

import junit.framework.TestCase;
import mlogic.algos.struct.Stack;

/**
 * @author Rajaram G
 *
 */
public class StackTest extends TestCase {

	/**
	 * Test method for
	 * {@link mlogic.algos.struct.Stack#push(java.lang.Object)}.
	 */
	public void testPushAndPop() {
		Stack<Integer, Integer> stack = new Stack<Integer, Integer>();
		stack.push(10, 10);
		stack.push(9, 9);
		stack.push(8, 8);
		assertEquals(new Integer(8), stack.pop().key());
	}

	/**
	 * Test method for {@link mlogic.algos.struct.Stack#pop()}.
	 */
	public void testPopFromEmptyStack() {
		Stack<Integer, Integer> stack = new Stack<Integer, Integer>();
		assertEquals(null, stack.pop());
	}

}
