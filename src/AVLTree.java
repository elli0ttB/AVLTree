import BinarySearchTree.BSTNode;

public class AVLTree<K extends Comparable<K>, V> extends BinarySearchTree<K, V> {

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
			putHelper(root, key, value);
		}
		size++;
	}

	// find node where we added
	// go through parents
	// for each parent, if height(left) - height(right) then go to cases to
	// balance it
	//

	// If a tree is balanced and we are adding a single a node, we can never get
	// to a situation where
	// there we have only 1 child and that child has 2 children.
	// this is what we return to parent calls
	private class BalancingContext {
		BSTNode firstChild;
		BSTNode grandChild;
		boolean subTreeBalanced;
		int height;
		
		public void setContext(BSTNode firstChild, BSTNode grandChild, boolean subTreeBalanced, int height){
			this.firstChild = firstChild;
			this.grandChild = grandChild;
			this.subTreeBalanced = subTreeBalanced;
			this.height = height;
		}
	}
	
	BalancingContext context = new BalancingContext();

	// each call to putHelper context to be the current information after the call
	private void putHelper(BSTNode n, K key, V value) {
		
		
		// RIGHT
		
		if (key.compareTo(n.key) > 0) {
			if (n.right == null) {
				n.right = new BSTNode(key, value);
				context.setContext(n, n.right, true, 1);
			} else {
				putHelper(n.right, key, value);
				if (context.subTreeBalanced) {
					if (!isBalancedHelper(n)) {
						context.subTreeBalanced = false;
						balance();
					// the whole subtree is balanced, so look higher
					} else {
						context.setContext(n, n.right, true, context.height + 1);
					}
				} // if not balanced, we do nothing
			}
			
		// LEFT	
			
		} else {
			if (n.left == null) {
				n.left = new BSTNode(key, value);
			} else {
				putHelper(n.left, key, value);
				if (context.subTreeBalanced) {
					if (!isBalancedHelper(n)) {
						// do balancing
						context.subTreeBalanced = false;
						balance();
					} else {
						context.setContext(n, n.left, true, context.height + 1);
					}
				}
			}
		}
	}
	
	public enum Problem{
		LEFTLEFT, RIGHTRIGHT, LEFTRIGHT, RIGHTLEFT;
	}
	
	// use the balancing context to figure things out
	private void balance(BSTNode grandparent, BSTNode parent, BSTNode unbalanced, Problem problem){
		
		switch(problem){
		case LEFTRIGHT:
		case LEFTLEFT:
			break;
		case RIGHTLEFT:
		case RIGHTRIGHT:
			// we add unbalanced's parent to it's left subtree 
			// the grandparent replaces the parent with us
			
			
			addToLeftSubTreeOfRightChild(parent);
		}
	}
	
	private void replace(BSTNode parent, BSTNode oldChild, BSTNode newChild){
		if (oldChild == parent.right){
			parent.right = newChild;
		} else {
			parent.left = newChild;
		}
	}
	
	public void addToLeftSubTreeOfRightChild(BSTNode n){
		BSTNode left = n.right.left;
		n.right.left = n;
		n.right = left;
	}
	
	
	public V remove(K key) {
		if (key == null) {
			throw new NullPointerException();
		}
		if (root == null) {
			return null;
		}
		int compare = key.compareTo(root.key);
		if (compare == 0) {
			return removeRoot();
		}

		// main loop for searching for node to replace
		BSTNode parent = root;
		BSTNode current = continueSearch(parent, compare);
		while (current != null) {
			compare = key.compareTo(current.key);
			if (compare == 0) {
				return removeNodeAndCleanup(current, parent);
			}
			parent = current;
			current = continueSearch(current, compare);
		}
		// node not found
		return null;
	}

	private V removeRoot() {
		V result = root.value;
		if (root.right != null) {
			addLefttoRight(root);
			root = root.right;
		} else {
			root = root.left;
		}
		size--;
		return result;
	}

	private BSTNode continueSearch(BSTNode node, int compare){
		return compare < 0 ? node.left : node.right;
	}

	private V removeNodeAndCleanup(BSTNode toRemove, BSTNode parent) {
		V result = toRemove.value;
		BSTNode replacementChild;
		if (toRemove.right != null) {
			addLefttoRight(toRemove);
			replacementChild = toRemove.right;
		} else {
			replacementChild = toRemove.left;
		}

		if (toRemove == parent.right) {
			parent.right = replacementChild;
		} else {
			parent.left = replacementChild;
		}
		size--;
		return result;
	}

}
