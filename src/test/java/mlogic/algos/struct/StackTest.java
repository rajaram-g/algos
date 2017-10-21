/**
 * 
 */
package mlogic.algos.struct;

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

}
