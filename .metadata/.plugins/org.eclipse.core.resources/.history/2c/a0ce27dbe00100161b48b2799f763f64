import static org.junit.Assert.*;

import org.junit.Test;

public class AVLTreeTest extends TestBinarySearchTree{

	@Override
	protected void makeTrees(){
		emptyTree= new AVLTree<Integer,String>();
		eightNodesTree= new AVLTree<Integer,String>();
	}
	
	@Test
	public void selfBalancingTestRightRight(){
		emptyTree.put(10, "foo");
		emptyTree.put(12, "foo");
		emptyTree.put(14, "foo");
		assertTrue(emptyTree.isBalanced());
	}
	
	
	
}
