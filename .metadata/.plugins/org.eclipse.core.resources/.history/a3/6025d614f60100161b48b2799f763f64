import static org.junit.Assert.*;

import org.junit.Test;

public class AVLTreeTest extends TestBinarySearchTree{

	@Override
	protected void makeTrees(){
		emptyTree= new AVLTree<Integer,String>();
		eightNodesTree= new AVLTree<Integer,String>();
	}
	
	@Test
	public void selfBalancingTestRightRightRoot(){
		emptyTree.put(10, "A");
		emptyTree.put(12, "B");
		emptyTree.put(14, "C");
		assertTrue(emptyTree.isBalanced());
	}
	@Test
	public void selfBalancingTestRightRightNotRoot(){
		emptyTree.put(8, "A");
		emptyTree.put(15, "B");
		emptyTree.put(17, "B"); 
		emptyTree.put(9, "C");
		emptyTree.put(10, "D");
		System.out.println(emptyTree);
		assertTrue(emptyTree.isBalanced());
	}
	
	
	
	
	
}
