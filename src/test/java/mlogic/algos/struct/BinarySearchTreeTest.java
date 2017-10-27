package mlogic.algos.struct;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import mlogic.algos.struct.BST;
import mlogic.algos.struct.BinaryKeyValueNode;
import mlogic.algos.struct.BinarySearchTree;
import mlogic.algos.util.RandomizationHelper;

public class BinarySearchTreeTest {

	@Test
	public void testPutOne() {
		BST<Integer, Integer> bst = new BinarySearchTree<Integer, Integer>();
		bst.put(0, 0);
		assertEquals(new Integer(1), bst.size());

	}

	@Test
	public void testPutMany() {
		BST<Integer, Integer> tree = createTestTree();
		assertEquals(new Integer(9), tree.size());
	}

	@Test
	public void testPutInDecreasingOrder() {
		BST<Integer, Integer> tree = createDecreasingTestTree();
		assertEquals(new Integer(9), tree.size());
		assertEquals(new Integer(9), tree.height());
	}

	@Test
	public void testPutInIncreasingOrder() {
		BST<Integer, Integer> tree = createIncreasingTestTree();
		assertEquals(new Integer(9), tree.size());
		assertEquals(new Integer(9), tree.height());
	}

	@Test
	public void testRandomTrees() {
		Integer n = 1000;
		for (int i = 0; i < 10; i++) {
			BST<String, String> tree = createRandomTree(n);
			assertEquals(n, tree.size());
		}
	}

	@Test
	public void testPutsAndDeletesFromRandomTrees() {
		Integer n = 1000;
		for (int i = 0; i < 10; i++) {
			BST<String, String> tree = createAndEmptyRandomTree(n);
			assertEquals(new Integer(0), tree.size());
		}
	}

	@Test
	public void testGet() {
		BST<Integer, Integer> tree = createTestTree();
		BinaryKeyValueNode<Integer, Integer> node = tree.get(4);
		assertEquals(new Integer(4), node.value);

	}

	@Test
	public void testRemoveRoot() {
		BST<Integer, Integer> tree = createTestTree();
		tree.remove(5);
		assertEquals(new Integer(8), tree.size());
	}

	@Test
	public void testRemoveLeaf() {
		BST<Integer, Integer> tree = createTestTree();
		tree.remove(6);
		assertEquals(new Integer(8), tree.size());
		tree.remove(7);
		assertEquals(new Integer(7), tree.size());
	}

	@Test
	public void testPutDuplicates() {
		BST<String, String> tree = createTestTreeWithDuplicates();
		assertEquals(new Integer(4), tree.size());
	}

	@Test
	public void testRemoveDuplicates() {
		BST<String, String> tree = createTestTreeWithDuplicates();
		tree.remove("pe");
		assertEquals(new Integer(3), tree.size());
		tree.remove("pe");
		assertEquals(new Integer(3), tree.size());
	}

	@Test
	public void testRemoveIntermediate() {
		BST<Integer, Integer> tree = createTestTree();
		tree.remove(3);
		assertEquals(new Integer(8), tree.size());
		tree.remove(8);
		assertEquals(new Integer(7), tree.size());
	}

	@Test
	public void testRemoveLastNode() {
		BST<Integer, Integer> tree = new BinarySearchTree<Integer, Integer>();
		tree.put(5, 5);
		tree.remove(5);
		assertEquals(new Integer(0), tree.size());
	}

	private BST<Integer, Integer> createTestTree() {
		BST<Integer, Integer> tree = new BinarySearchTree<Integer, Integer>();
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

	private BST<String, String> createRandomTree(Integer n) {
		String[] array = RandomizationHelper.getShuffledStringArrayOfSizeN(n);
		BST<String, String> tree = new BinarySearchTree<String, String>();
		for (int i = 0; i < array.length; i++)
			tree.put(array[i], array[i]);

		return tree;
	}

	private BST<String, String> createAndEmptyRandomTree(Integer n) {
		String[] array = RandomizationHelper.getShuffledStringArrayOfSizeN(n);
		BST<String, String> tree = new BinarySearchTree<String, String>();
		for (int i = 0; i < array.length; i++)
			tree.put(array[i], array[i]);

		for (int i = 0; i < array.length; i++)
			tree.remove(array[i]);

		return tree;
	}

	private BST<String, String> createTestTreeWithDuplicates() {
		BST<String, String> tree = new BinarySearchTree<String, String>();
		tree.put("aa", "aa");
		tree.put("zo", "zo");
		tree.put("mi", "mi");
		tree.put("pe", "pe");
		tree.put("pe", "pe");
		return tree;
	}

	private BST<Integer, Integer> createDecreasingTestTree() {
		BST<Integer, Integer> tree = new BinarySearchTree<Integer, Integer>();
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

	private BST<Integer, Integer> createIncreasingTestTree() {
		BST<Integer, Integer> tree = new BinarySearchTree<Integer, Integer>();
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
