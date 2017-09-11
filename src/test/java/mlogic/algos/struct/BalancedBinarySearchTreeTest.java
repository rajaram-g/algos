package mlogic.algos.struct;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import mlogic.algos.struct.BalancedBinarySearchTree;
import mlogic.algos.struct.BinaryNode;
import mlogic.algos.util.RandomizationHelper;

public class BalancedBinarySearchTreeTest {

	@Test
	public void testPutOne() {
		BalancedBinarySearchTree<Integer, Integer> bst = new BalancedBinarySearchTree<Integer, Integer>();
		bst.put(0, 0);
		assertEquals(new Integer(1), bst.size());

	}

	@Test
	public void testPutMany() {
		BalancedBinarySearchTree<Integer, Integer> tree = createTestTree();
		assertEquals(new Integer(9), tree.size());
		assertTrue(tree.isBalanced());
	}

	@Test
	public void testPutInDecreasingOrder() {
		BalancedBinarySearchTree<Integer, Integer> tree = createDecreasingTestTree();
		assertEquals(new Integer(9), tree.size());
		assertTrue(tree.isBalanced());
	}

	@Test
	public void testPutInIncreasingOrder() {
		BalancedBinarySearchTree<Integer, Integer> tree = createIncreasingTestTree();
		assertEquals(new Integer(9), tree.size());
		assertTrue(tree.isBalanced());
	}

	@Test
	public void testRandomTrees() {
		Integer n = 1000;
		for (int i = 0; i < 20; i++) {
			BalancedBinarySearchTree<String, String> tree = createRandomTree(n);
			assertEquals(n, tree.size());
			assertTrue(tree.isBalanced());
		}
	}

	@Test
	public void testPutsAndDeletesFromRandomTrees() {
		Integer n = 1000;
		for (int i = 0; i < 20; i++) {
			BalancedBinarySearchTree<String, String> tree = createAndEmptyRandomTree(n);
			assertEquals(new Integer(0), tree.size());
		}
	}

	@Test
	public void testGet() {
		BalancedBinarySearchTree<Integer, Integer> tree = createTestTree();
		BinaryNode<Integer, Integer> node = tree.get(4);
		assertEquals(new Integer(4), node.value);

	}

	@Test
	public void testRemoveRoot() {
		BalancedBinarySearchTree<Integer, Integer> tree = createTestTree();
		tree.remove(5);
		assertEquals(new Integer(8), tree.size());
	}

	@Test
	public void testRemoveLeaf() {
		BalancedBinarySearchTree<Integer, Integer> tree = createTestTree();
		tree.remove(6);
		assertEquals(new Integer(8), tree.size());
		tree.remove(7);
		assertEquals(new Integer(7), tree.size());
	}

	@Test
	public void testPutDuplicates() {
		BalancedBinarySearchTree<String, String> tree = createTestTreeWithDuplicates();
		assertEquals(new Integer(4), tree.size());
	}

	@Test
	public void testRemoveDuplicates() {
		BalancedBinarySearchTree<String, String> tree = createTestTreeWithDuplicates();
		tree.remove("pe");
		assertEquals(new Integer(3), tree.size());
		tree.remove("pe");
		assertEquals(new Integer(3), tree.size());
	}

	@Test
	public void testRemoveIntermediate() {
		BalancedBinarySearchTree<Integer, Integer> tree = createTestTree();
		tree.remove(3);
		assertEquals(new Integer(8), tree.size());
		tree.remove(8);
		assertEquals(new Integer(7), tree.size());
	}

	@Test
	public void testRemoveLastNode() {
		BalancedBinarySearchTree<Integer, Integer> tree = new BalancedBinarySearchTree<Integer, Integer>();
		tree.put(5, 5);
		tree.remove(5);
		assertEquals(new Integer(0), tree.size());
	}

	private BalancedBinarySearchTree<Integer, Integer> createTestTree() {
		BalancedBinarySearchTree<Integer, Integer> tree = new BalancedBinarySearchTree<Integer, Integer>();
		tree.put(5, 5);
		tree.put(3, 3);
		tree.put(1, 1);
		tree.put(2, 2);
		tree.put(4, 4);
		tree.put(8, 8);
		tree.put(7, 7);
		tree.put(6, 6);
		tree.put(9, 9);
		return tree;
	}

	private BalancedBinarySearchTree<String, String> createRandomTree(Integer n) {
		String[] array = RandomizationHelper.getShuffledStringArrayOfSizeN(n);
		BalancedBinarySearchTree<String, String> tree = new BalancedBinarySearchTree<String, String>();
		for (int i = 0; i < array.length; i++) {
			tree.put(array[i], array[i]);
			if (!tree.isBalanced()) {
				System.out.println(tree.toString());
				fail("Tree is unbalanced after putting " + array[i]);
			}
		}

		return tree;
	}

	private BalancedBinarySearchTree<String, String> createAndEmptyRandomTree(Integer n) {
		String[] array = RandomizationHelper.getShuffledStringArrayOfSizeN(n);
		BalancedBinarySearchTree<String, String> tree = new BalancedBinarySearchTree<String, String>();
		for (int i = 0; i < array.length; i++) {
			tree.put(array[i], array[i]);
			if (!tree.isBalanced()) {
				System.out.println(tree.toString());
				fail("Tree is unbalanced after putting " + array[i]);
			}
		}

		for (int i = 0; i < array.length; i++) {
			tree.remove(array[i]);
			if (!tree.isBalanced()) {
				System.out.println(tree.toString());
				fail("Tree is unbalanced after removing " + array[i]);
			}
		}

		return tree;
	}

	private BalancedBinarySearchTree<String, String> createTestTreeWithDuplicates() {
		BalancedBinarySearchTree<String, String> tree = new BalancedBinarySearchTree<String, String>();
		tree.put("aa", "aa");
		tree.put("zo", "zo");
		tree.put("mi", "mi");
		tree.put("pe", "pe");
		tree.put("pe", "pe");
		return tree;
	}

	private BalancedBinarySearchTree<Integer, Integer> createDecreasingTestTree() {
		BalancedBinarySearchTree<Integer, Integer> tree = new BalancedBinarySearchTree<Integer, Integer>();
		tree.put(9, 9);
		tree.put(8, 8);
		tree.put(7, 7);
		tree.put(6, 6);
		tree.put(5, 5);
		tree.put(4, 4);
		tree.put(3, 3);
		tree.put(2, 2);
		tree.put(1, 1);
		return tree;
	}

	private BalancedBinarySearchTree<Integer, Integer> createIncreasingTestTree() {
		BalancedBinarySearchTree<Integer, Integer> tree = new BalancedBinarySearchTree<Integer, Integer>();
		tree.put(1, 1);
		tree.put(2, 2);
		tree.put(3, 3);
		tree.put(4, 4);
		tree.put(5, 5);
		tree.put(6, 6);
		tree.put(7, 7);
		tree.put(8, 8);
		tree.put(9, 9);
		return tree;
	}
}
