package mlogic.algos.struct;

import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

import org.junit.Test;

import mlogic.algos.exceptions.ElementAlreadyExistsException;
import mlogic.algos.util.RandomizationHelper;

public class BinarySearchTreeTest {

	@Test
	public void testPutOne() {
		BST<Integer> bst = new BinarySearchTree<Integer>();
		bst.put(0);
		assertEquals(new Integer(1), bst.size());

	}

	@Test
	public void testPutMany() {
		BST<Integer> tree = createTestTree();
		assertEquals(new Integer(9), tree.size());
	}

	@Test
	public void testPutInDecreasingOrder() {
		BST<Integer> tree = createDecreasingTestTree();
		assertEquals(new Integer(9), tree.size());
		assertEquals(new Integer(9), tree.height());
	}

	@Test
	public void testPutInIncreasingOrder() {
		BST<Integer> tree = createIncreasingTestTree();
		assertEquals(new Integer(9), tree.size());
		assertEquals(new Integer(9), tree.height());
	}

	@Test
	public void testRandomTrees() {
		Integer n = 1000;
		for (int i = 0; i < 10; i++) {
			BST<String> tree = createRandomTree(n);
			assertEquals(n, tree.size());
		}
	}

	@Test
	public void testPutsAndDeletesFromRandomTrees() {
		Integer n = 1000;
		for (int i = 0; i < 10; i++) {
			BST<String> tree = createAndEmptyRandomTree(n);
			assertEquals(new Integer(0), tree.size());
		}
	}

	@Test
	public void testGet() {
		BST<Integer> tree = createTestTree();
		BinaryNode<Integer> node = tree.get(4);
		assertEquals(new Integer(4), node.item);

	}

	@Test
	public void testRemoveRoot() {
		BST<Integer> tree = createTestTree();
		tree.remove(5);
		assertEquals(new Integer(8), tree.size());
	}

	@Test
	public void testRemoveLeaf() {
		BST<Integer> tree = createTestTree();
		tree.remove(6);
		assertEquals(new Integer(8), tree.size());
		tree.remove(7);
		assertEquals(new Integer(7), tree.size());
	}

	@Test
	public void testPutDuplicates() {
		BST<String> tree = createTestTreeWithDuplicates();
		assertEquals(new Integer(4), tree.size());
	}

	@Test
	public void testRemoveDuplicates() {
		BST<String> tree = createTestTreeWithDuplicates();
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
		BST<Integer> tree = createTestTree();
		tree.remove(3);
		assertEquals(new Integer(8), tree.size());
		tree.remove(8);
		assertEquals(new Integer(7), tree.size());
	}

	@Test
	public void testRemoveLastNode() {
		BST<Integer> tree = new BinarySearchTree<Integer>();
		tree.put(5);
		tree.remove(5);
		assertEquals(new Integer(0), tree.size());
	}

	private BST<Integer> createTestTree() {
		BST<Integer> tree = new BinarySearchTree<Integer>();
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

	private BST<String> createRandomTree(Integer n) {
		String[] array = RandomizationHelper.getShuffledStringArrayOfSizeN(n);
		BST<String> tree = new BinarySearchTree<String>();
		for (int i = 0; i < array.length; i++)
			tree.put(array[i]);

		return tree;
	}

	private BST<String> createAndEmptyRandomTree(Integer n) {
		String[] array = RandomizationHelper.getShuffledStringArrayOfSizeN(n);
		BST<String> tree = new BinarySearchTree<String>();
		for (int i = 0; i < array.length; i++)
			tree.put(array[i]);

		for (int i = 0; i < array.length; i++)
			tree.remove(array[i]);

		return tree;
	}

	private BST<String> createTestTreeWithDuplicates() {
		BST<String> tree = new BinarySearchTree<String>();
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

	private BST<Integer> createDecreasingTestTree() {
		BST<Integer> tree = new BinarySearchTree<Integer>();
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

	private BST<Integer> createIncreasingTestTree() {
		BST<Integer> tree = new BinarySearchTree<Integer>();
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
