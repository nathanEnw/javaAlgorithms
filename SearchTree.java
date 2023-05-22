package Assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SearchTree {

	// root of the search
	private Node root;
	
	private class Node {
		// words in tree
		String key;
		// left subtree
		Node left;
		// right subtree
		Node right;
	
	public Node (String key) {
		this.key = key;
		left = null;
		right = null;
		}
	}
	
	// starting the binary search tree
	public SearchTree() {
		root = null;
	}
	
	public void insert (String key) {
		root = insert(root, key);
	}
	
	private Node insert (Node node, String key) {
		// if node is null, creates a new node
		if (node == null) {
			node = new Node(key);
			// if key is less than nodes key, inserts to the left subtree
		} else if (key.compareTo(node.key) < 0) {
			node.left = insert(node.left, key);
			// if key is more than nodes key, inserts to the right subtree
		} else if (key.compareTo(node.key) > 0) {
			node.right = insert(node.right, key);
		}
		return node;
	}
	
	public boolean contains(String key) {
		// initialize count of elements
		int count = 0;
		// starts from the root
		Node node = root;
		// while there are nodes
		while (node != null) {
			count++;
			// if key matches current nodes key return true
			if (key.compareTo(node.key)== 0) {
				System.out.println("Inspected " + count + " elements");
				return true;
				// if key is less than current nodes key, move to left subtree
			} else if (key.compareTo(node.key) < 0) {
				node = node.left;
			} else  {
				// moves to the right subtree otherwise
				node = node.right;
			}
		}
		// if not found return false
		System.out.println("Inspected " + count + " elements");
		return false;
	}
	
	public void inOrder() {
		inOrder(root);
	}
	
	private void inOrder(Node node) {
		if (node != null) {
			// recursively traverse left subtree
			inOrder(node.left);
			System.out.print(node.key + " ");
			// recursively traverse right subtree
			inOrder(node.right);
		}
	}
	
	public void remove(String key) {
		root = remove(root, key);
	}
	
	private Node remove(Node node, String key) {
		// if node is null, not in tree
		if (node == null) {
			return null;
			// if key is less than current node, remove key from left subtree
		} else if (key.compareTo(node.key)< 0) {
			node.left = remove(node.left, key);
			// if key is more than current node, remove key from right subtree
		} else if (key.compareTo(node.key)> 0) {
			node.right = remove(node.right, key);
		} else {
			// otherwise if there are no subtrees, remove current node and set it to null
			if (node.left == null && node.right == null) {
				node = null;
				// if current node only has a right subtree, replace current node with its right subtree
			} else if (node.left == null) {
				node = node.right;
				// if current node only has a left subtree, replace current node with its left subtree
			} else if (node.right == null) {
				node = node.left;
			} else {
				// find minimum node in right subtree
				Node minNode = findMin(node.right);
				node.key = minNode.key;
				node.right = remove(node.right, minNode.key);
			}
		}
		return node;
	}
	
	private Node findMin(Node node) {
		// traverses left subtree
		while (node.left != null) {
			// moves to the left subtree
			node = node.left;
		}
		return node;
	}
	
	public static void main(String[] args) {
		// new binary search tree
		SearchTree st = new SearchTree();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            // reads the first line in the input
            String line = reader.readLine();
            // non word characters
            Pattern pattern = Pattern.compile("\\W+");
            // continues while there are lines to read
            while (line != null) {
            	// split line into words
                String[] keys = pattern.split(line);
                // iterate over each word
                for (String key : keys) {
                	// if key isn't empty
                    if (!key.isEmpty()) {
                    	// converts all to lower case
                        st.insert(key.toLowerCase());
                    }
                }
                line = reader.readLine();
            }
            // close reader
            reader.close();
            // catches IOException
        } catch (IOException e) {
        	// prints stack trace
            e.printStackTrace();
        }
        // prints the tree in in-order traversal
        st.inOrder();
        System.out.println();
        // can now read user input
        Scanner scanner = new Scanner(System.in);
        // initialize empty key
        String key = "";
        // if key is not -1
        while (!key.equals("-1")) {
            System.out.println("Enter string, enter -1 to quit: ");
            key = scanner.next();
            if (!key.equals("-1")) {
                if (st.contains(key)) {
                    System.out.println("'" + key + "'" + " located");
                } else {
                    System.out.println("'" + key + "'" + " not in tree");
                }
            }
        }

        // reset key to empty string
        key = "";
        // while key does not equal -1
        while (!key.equals("-1")) {
        	System.out.println();
            System.out.println("Enter string to remove, enter -1 to quit: ");
            key = scanner.next();
            // if key is not -1
            if (!key.equals("-1")) {
            	// remove key from tree
                st.remove(key);
                System.out.println();
                // print tree's keys in in-order traversal after the removal
                st.inOrder();
            }
        }
    }
}
