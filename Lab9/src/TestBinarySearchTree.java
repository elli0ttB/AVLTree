
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class TestBinarySearchTree {
	protected BinarySearchTree<Integer,String> emptyTree;
	protected BinarySearchTree<Integer,String> eightNodesTree;
	
	protected void makeTrees(){
		emptyTree= new BinarySearchTree<Integer,String>();
		eightNodesTree= new BinarySearchTree<Integer,String>();
	}
	
	@Before
	public void setUp() {
		makeTrees();
		eightNodesTree.put(4,"apple");
		eightNodesTree.put(6,"banana");
		eightNodesTree.put(1,"strawberry");
		eightNodesTree.put(3,"kiwi");
		eightNodesTree.put(7,"lemon");
		eightNodesTree.put(10,"lime");
		eightNodesTree.put(6,"mango");
		eightNodesTree.put(8,"pear");
	}
	
	@Test
	public void TestEmpty() {
		assertTrue(emptyTree.isEmpty());
		assertEquals(0,emptyTree.size());
	}
	
	@Test
	public void TestNonEmpty() {
		emptyTree.put(4,"apple");
		assertFalse(emptyTree.isEmpty());
		assertEquals(1,emptyTree.size());
	}
	
	@Test
	public void TestPutGetRoot() {
		emptyTree.put(4,"apple");
		assertEquals("apple",emptyTree.get(4));
	}
	
	@Test
	public void TestGetFromEmpty() {
		assertNull(emptyTree.get(4));
	}	
	
	@Test
	public void TestGet() {
		assertEquals("strawberry",eightNodesTree.get(1));
		assertEquals("lemon",eightNodesTree.get(7));
	}	
	
	@Test
	public void TestGetNotThere() {
		assertNull(eightNodesTree.get(5));
	}	
	
	@Test (expected=NullPointerException.class)
	public void TestNullKey() {
		assertNull(eightNodesTree.get(null));
	}
	
	@Test
	public void TestSize() {
		assertEquals(8,eightNodesTree.size());
	}
	
	@Test
	public void TestClear() {
		eightNodesTree.clear();
		assertTrue(eightNodesTree.isEmpty());
		assertEquals(0,eightNodesTree.size());
		assertNull(eightNodesTree.get(4));
	}
	
	@Test
	public void TestRemoveRootOneChild() {
		emptyTree.put(4, "apple");
		emptyTree.put(1, "strawberry");
		emptyTree.remove(4);
		assertFalse(emptyTree.isEmpty());
		assertEquals(1,emptyTree.size());
		assertEquals("strawberry",emptyTree.get(1));
	}
	
	@Test
	public void TestRemoveRoot(){
		emptyTree.put(4, "Spongebob");
		assertEquals("Spongebob", emptyTree.remove(4));
		assertTrue(emptyTree.isEmpty());
	}
	
	public void TestRemoveRootWithChildren(){
		emptyTree.put(4, "Spongebob");
		emptyTree.put(4, "Spongebob");
		assertEquals("Spongebob", emptyTree.remove(4));
		assertEquals(1, emptyTree.size());
	}
	
	@Test
	public void TestRemoveElement7() {
		eightNodesTree.remove(7);
		assertEquals(null, eightNodesTree.get(7));
	
	}
	
	@Test
	public void TestRemoveRootWithNoRightChild(){
		emptyTree.put(4, "SpongeSquarepants");
		emptyTree.put(3, "Squidward");
		emptyTree.remove(4);
		assertEquals("Squidward", emptyTree.remove(3));
	}
	
	@Test
	public void TestRemoveRootWithNoLeftChild(){
		emptyTree.put(4, "SpongeSquarepants");
		emptyTree.put(5, "Squidward");
		emptyTree.remove(4);
		assertEquals("Squidward", emptyTree.remove(5));
	}
	
	
	@Test
	public void TestRemoveDuplicate() {
		eightNodesTree.remove(6);
		assertNotNull(eightNodesTree.get(6));
		assertEquals(7, eightNodesTree.size());
		
	}
	@Test
	public void TestRemoveAllElements() {
		// All keys are in the tree before they are removed
		assertEquals("strawberry",eightNodesTree.remove(1));
		assertEquals("kiwi",eightNodesTree.remove(3));
		assertEquals("apple",eightNodesTree.remove(4));
		String removed = eightNodesTree.remove(6);
		assertTrue(removed.equals("banana") || removed.equals("mango"));
		removed = eightNodesTree.remove(6);
		assertTrue(removed.equals("banana") || removed.equals("mango"));
		eightNodesTree.remove(6);
		assertEquals("lemon",eightNodesTree.remove(7));
		assertEquals("pear",eightNodesTree.remove(8));
		assertEquals("lime",eightNodesTree.remove(10));
		// The tree is empty at the end
		assertTrue(eightNodesTree.isEmpty());
		assertEquals(0, eightNodesTree.size());		
	}

	@Test
	public void TestRemoveOnEmptyTree() {
		assertNull(emptyTree.remove(0));
	}
	@Test (expected=NullPointerException.class)
	public void RemoveNullKey() {
		eightNodesTree.remove(null);
	}
	
	// to-do: tests for tree traversals
	//@Test
	public void testInOrder(){
	
		ArrayList<KVPair<Integer,String>> expected = new ArrayList<KVPair<Integer,String>>();
		int[] keys = {1,3,4,6,6,7,8,10};
		String[] vals = {"strawberry", "kiwi", "apple", "mango", "banana", "lemon", "pear", "lime" };
		for (int i=0; i<vals.length; i++){
			expected.add(new KVPair<Integer,String>(keys[i],vals[i]));
		}
		assertEquals(expected, eightNodesTree.inOrder());
		
	}
	
	@Test
	public void testIsBalancedNegative(){
		emptyTree= new BinarySearchTree<Integer,String>();
		emptyTree.put(10, "foo");
		emptyTree.put(12, "foo");
		emptyTree.put(14, "foo");
		assertFalse(emptyTree.isBalanced());
	}
	
	@Test
	public void testIsBalancedPostive(){
		emptyTree.put(10, "foo");
		emptyTree.put(9, "foo");
		emptyTree.put(11, "foo");
		assertTrue(emptyTree.isBalanced());
	}
	
}