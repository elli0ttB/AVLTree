import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AVLTree<K extends Comparable<K>, V> extends BinarySearchTree<K, V> {
	ArrayList<BSTNode> putTrace = new ArrayList<BSTNode>();

	/**
	 * Adds a given value indexed with a given key to the tree according to the
	 * binary search structure
	 * 
	 * @param key
	 *            - the key of the given element
	 * @param value
	 *            - the value of the given element
	 */

	@Override
	public void put(K key, V value) {

		if (root == null) {
			root = new BSTNode(key, value);
		} else {
			ArrayList<BSTNode> putTrace = new ArrayList<BSTNode>();
			putHelper(root, key, value, putTrace);
			checkBalance(putTrace);
		}
		size++;
	}

	// each call to putHelper context to be the current information after the call
	private void putHelper(BSTNode n, K key, V value, List<BSTNode> putTrace) {
		putTrace.add(n);
		
		if (key.compareTo(n.key) > 0) {
			if (n.right == null) {
				n.right = new BSTNode(key, value);
				putTrace.add(n.right);
			} else {
				putHelper(n.right, key, value, putTrace);
			}
			
		} else {
			if (n.left == null) {
				n.left = new BSTNode(key, value);
			} else {
				putHelper(n.left, key, value, putTrace);
				putTrace.add(n.left);
			}
		}
	}
	
	private void checkBalance(List<BSTNode> putTrace){
		// h: height of `top.right`, which is equal to 2 + index in putTrace2
		for(int index = putTrace.size() - 3; index >= 0; index--){
			BSTNode current = putTrace.get(index);
			BSTNode child = getOtherChild(current, putTrace.get(index + 1));
			int h = putTrace.size() - index - 1;
			if (Math.abs((getHeight(child) - h)) > 1){
				balance(index, current, putTrace);
				return;
			}
		}
	}
	
	// use the balancing context to figure things out
	private void balance(int index, BSTNode unbalanced, List<BSTNode> putTrace){
		BSTNode child = putTrace.get(index + 1);
		BSTNode grandchild = putTrace.get(index + 2);
		boolean childIsLeft = unbalanced.left == child;
		boolean grandchildIsLeft = child.left == grandchild;
		
	//	System.out.println(child);
	//	System.out.println(grandchildIsLeft);
	
		if (childIsLeft && grandchildIsLeft){
			leftLeft(index, unbalanced, putTrace);
		} else if (childIsLeft && ! grandchildIsLeft){
			leftRight(index, unbalanced, putTrace);
		} else if (grandchildIsLeft){
			rightLeft(index, unbalanced, putTrace);
		} else if (child.right == grandchild){
			rightRight(index, unbalanced, putTrace);
		}// else throw new IllegalArgumentException();
	}
	
	private void leftLeft(int index, BSTNode unbalanced, List<BSTNode> putTrace){
		System.out.println("executed leftLeft");
		
		
	}
	private void leftRight(int index, BSTNode unbalanced, List<BSTNode> putTrace){
		System.out.println("executed leftRight");
		 
		
		
	}
	
	private void rightLeft(int index, BSTNode unbalanced, List<BSTNode> trace){
		System.out.println("executed rightLeft");
		BSTNode newRight = moveToLeftDotRight(unbalanced);
		if (unbalanced == root){
			root = newRight;
		} else {
			BSTNode parent = trace.get(index - 1);
			replace(parent, unbalanced, newRight);
		}
		rightRight(index, unbalanced, putTrace);
	}
	
	// needs the trace up to index
	private void rightRight(int index, BSTNode unbalanced, List<BSTNode> trace){
		System.out.println("executed rightRight");
		BSTNode newRight = moveToRightDotLeft(unbalanced);
		if (unbalanced == root){
			root = newRight;
		} else {
			BSTNode parent = trace.get(index - 1);
			replace(parent, unbalanced, newRight);
		}
	}

	private BSTNode getOtherChild(BSTNode parent, BSTNode child){
		if (parent.left == child){
			return parent.right;
		}
		if (parent.right == child){
			return parent.left;
		}
		
		throw new IllegalArgumentException();
	}
		
	private void replace(BSTNode parent, BSTNode oldChild, BSTNode newChild){
		if (oldChild == parent.right){
			parent.right = newChild;
		} else if (oldChild == parent.left){
			parent.left = newChild;
		} else{
			throw new IllegalArgumentException();
		}
	}
	
	public String toString(){
		if (root == null){
			return "()";
		}
		return root.toString();
	}
	
	/** replaces n.rightleft. with n, adding what was n.rightleft. to n
	 * @return the new subtree n should be replaced with
	 */
	private BSTNode moveToRightDotLeft(BSTNode n){
		BSTNode right = n.right;
		// swap n and n.right.left
		BSTNode tmp = right.left;
		right.left = n;
		n.right = tmp;
		return right;
	}
	
	private BSTNode moveToLeftDotRight(BSTNode n){
		BSTNode left = n.left;
		// swap n and n.right.left
		BSTNode tmp = left.right;
		left.right = n;
		n.left = tmp;
		return left;
	}
	
	
}
