package mlogic.algos.struct;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;

import org.junit.Test;

import mlogic.algos.exceptions.ElementAlreadyExistsException;
import mlogic.algos.util.RandomizationHelper;

public class BalancedBinarySearchTreeTest {

	@Test
	public void testPutOne() {
		BalancedBinarySearchTree<Integer> bst = new BalancedBinarySearchTree<Integer>();
		bst.put(0);
		assertEquals(new Integer(1), bst.size());

	}

	@Test
	public void testPutMany() {
		BalancedBinarySearchTree<Integer> tree = createTestTree();
		assertEquals(new Integer(9), tree.size());
		assertTrue(tree.isBalanced());
	}

	@Test
	public void testPutInDecreasingOrder() {
		BalancedBinarySearchTree<Integer> tree = createDecreasingTestTree();
		assertEquals(new Integer(9), tree.size());
		assertTrue(tree.isBalanced());
	}

	@Test
	public void testPutInIncreasingOrder() {
		BalancedBinarySearchTree<Integer> tree = createIncreasingTestTree();
		assertEquals(new Integer(9), tree.size());
		assertTrue(tree.isBalanced());
	}

	@Test
	public void testRandomTrees() {
		Integer n = 1000;
		for (int i = 0; i < 20; i++) {
			BalancedBinarySearchTree<String> tree = createRandomTree(n);
			assertEquals(n, tree.size());
			assertTrue(tree.isBalanced());
		}
	}

	@Test
	public void testPutsAndDeletesFromRandomTrees() {
		Integer n = 1000;
		for (int i = 0; i < 20; i++) {
			BalancedBinarySearchTree<String> tree = createAndEmptyRandomTree(n);
			assertEquals(new Integer(0), tree.size());
		}
	}

	@Test
	public void testGet() {
		BalancedBinarySearchTree<Integer> tree = createTestTree();
		BinaryNode<Integer> node = tree.get(4);
		assertEquals(new Integer(4), node.item);

	}

	@Test
	public void testRemoveRoot() {
		BalancedBinarySearchTree<Integer> tree = createTestTree();
		tree.remove(5);
		assertEquals(new Integer(8), tree.size());
	}

	@Test
	public void testRemoveLeaf() {
		BalancedBinarySearchTree<Integer> tree = createTestTree();
		tree.remove(6);
		assertEquals(new Integer(8), tree.size());
		tree.remove(7);
		assertEquals(new Integer(7), tree.size());
	}

	@Test
	public void testPutDuplicates() {
		BalancedBinarySearchTree<String> tree = createTestTreeWithDuplicates();
		assertEquals(new Integer(4), tree.size());
	}

	@Test
	public void testRemoveDuplicates() {
		BalancedBinarySearchTree<String> tree = createTestTreeWithDuplicates();
		tree.remove("pe");
		assertEquals(new Integer(3), tree.size());
		try {
			tree.remove("pe");
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		assertEquals(new Integer(3), tree.size());
	}

	@Test
	public void testRemoveIntermediate() {
		BalancedBinarySearchTree<Integer> tree = createTestTree();
		tree.remove(3);
		assertEquals(new Integer(8), tree.size());
		tree.remove(8);
		assertEquals(new Integer(7), tree.size());
	}

	@Test
	public void testRemoveLastNode() {
		BalancedBinarySearchTree<Integer> tree = new BalancedBinarySearchTree<Integer>();
		tree.put(5);
		tree.remove(5);
		assertEquals(new Integer(0), tree.size());
	}

	private BalancedBinarySearchTree<Integer> createTestTree() {
		BalancedBinarySearchTree<Integer> tree = new BalancedBinarySearchTree<Integer>();
		tree.put(5);
		tree.put(3);
		tree.put(1);
		tree.put(2);
		tree.put(4);
		tree.put(8);
		tree.put(7);
		tree.put(6);
		tree.put(9);
		return tree;
	}

	private BalancedBinarySearchTree<String> createRandomTree(Integer n) {
		String[] array = RandomizationHelper.getShuffledStringArrayOfSizeN(n);
		BalancedBinarySearchTree<String> tree = new BalancedBinarySearchTree<String>();
		for (int i = 0; i < array.length; i++) {
			tree.put(array[i]);
			if (!tree.isBalanced()) {
				System.out.println(tree.toString());
				fail("Tree is unbalanced after putting " + array[i]);
			}
		}

		return tree;
	}

	private BalancedBinarySearchTree<String> createAndEmptyRandomTree(Integer n) {
		String[] array = RandomizationHelper.getShuffledStringArrayOfSizeN(n);
		BalancedBinarySearchTree<String> tree = new BalancedBinarySearchTree<String>();
		for (int i = 0; i < array.length; i++) {
			tree.put(array[i]);
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

	private BalancedBinarySearchTree<String> createTestTreeWithDuplicates() {
		BalancedBinarySearchTree<String> tree = new BalancedBinarySearchTree<String>();
		tree.put("aa");
		tree.put("zo");
		tree.put("mi");
		tree.put("pe");
		try {
			tree.put("pe");
		} catch (ElementAlreadyExistsException e) {
			e.printStackTrace();
		}
		return tree;
	}

	private BalancedBinarySearchTree<Integer> createDecreasingTestTree() {
		BalancedBinarySearchTree<Integer> tree = new BalancedBinarySearchTree<Integer>();
		tree.put(9);
		tree.put(8);
		tree.put(7);
		tree.put(6);
		tree.put(5);
		tree.put(4);
		tree.put(3);
		tree.put(2);
		tree.put(1);
		return tree;
	}

	private BalancedBinarySearchTree<Integer> createIncreasingTestTree() {
		BalancedBinarySearchTree<Integer> tree = new BalancedBinarySearchTree<Integer>();
		tree.put(1);
		tree.put(2);
		tree.put(3);
		tree.put(4);
		tree.put(5);
		tree.put(6);
		tree.put(7);
		tree.put(8);
		tree.put(9);
		return tree;
	}
}
