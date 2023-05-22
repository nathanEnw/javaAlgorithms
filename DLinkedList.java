package Assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DLinkedList<T extends Comparable<T>> {

	public static void main(String[] args) throws FileNotFoundException {
		DLinkedList<String> lst1 = new DLinkedList<>();
		DLinkedList<String> lst2 = new DLinkedList<>();
		
		Scanner fin = new Scanner(new File("text1.in"));
		String str;
		
		while (fin.hasNext()) {
			str = fin.next();
			str = cleanUp(str);
			lst1.insertOrderUnique(str);
		}
		fin.close();
		
		fin = new Scanner(new File("text2.in"));
		while (fin.hasNext()) {
			str = fin.next();
			str = cleanUp(str);
			lst2.insertOrderUnique(str);
		}
		
		System.out.println("List 1: " + lst1);
		System.out.println("List 2: " + lst2);
		
		DLinkedList<String> combined = lst1.merge(lst2);
		
		System.out.println("\nAFTER MERGE");
		System.out.println("List 1: " + lst1);
		System.out.println("List 2: " + lst2);
		System.out.println("\n" + combined);
	}
	
	/**
	 * ASSIGNED
	 * @param str
	 * @return str in all lower case with LEADING and TRAILING non-alpha
	 * chars removed
	 */
	public static String cleanUp(String str) {
		// This line replaces any non-letter character with an empty string 
		str = str.replaceAll("^[^a-zA-Z]+|[^a-zA-Z]+$","");
		// Converts to lower case
		str = str.toLowerCase();
		return str;
	}
	
	//inner DNode class: PROVIDED
	private class DNode {
		
		private DNode next, prev;
		private T data;
		
		private DNode(T val) {
			this.data = val;
			next = prev = this;
		}
	}
	//DLinkedList fields: PROVIDED
	private DNode header;
	
	//create an empty list: PROVIDED
	public DLinkedList() {
		header = new DNode(null);
	}
	
	/**
	 * PROVIDED add
	 * 
	 * @param item return ref to newly inserted node
	 */
	public DNode add(T item) {
		//make a new node
		DNode newNode = new DNode(item);
		//update newNode
		newNode.prev = header;
		newNode.next = header.next;
		//update surrounding nodes
		header.next.prev = newNode;
		header.next = newNode;
		return newNode;
	}
	
	//PROVIDED
	public String toString() {
		String str = "[";
		DNode curr = header.next;
		while (curr != header) {
			str += curr.data + ", ";
			curr = curr.next;
		}
		str = str.substring(0, str.length() - 1);
		return str + "]";
	}
	
	/**
	 * ASSIGNED
	 * remove val from the list
	 * 
	 * @param val
	 * @return true if successful, false otherwise
	 */
	public boolean remove(T val) {
		// Starts at the first node after header
		DNode curr = header.next;
		// Traverse list until it reaches the header node
		while (curr != header) {
			// If a node contains data equal to the input value, it removes the node from the list
			if (curr.data.equals(val)) {
				// Replaces the next reference
				curr.prev.next = curr.next;
				// Replaces the nodes previous reference to skip current node
				curr.next.prev = curr.prev;
				return true;
			}
			// Move to the next node in the list
			curr = curr.next;
		}
		return false;
	}
	
	/**
	 * ASSIGNED
	 * 
	 * @param item
	 */
	public void insertOrder(T item) {
		// New node with input value
		DNode newNode = new DNode(item);
		// Starts at the first node in the list
		DNode curr = header.next;
		
		// Traverse the list until it reaches the header node or find node with a larger value
		while (curr != header && curr.data.compareTo(item) < 0) {
			curr = curr.next;
		}
		
		// Sets new nodes previous reference to the current nodes previous
		newNode.prev = curr.prev;
		// Sets the new nodes previous reference to the current node
		newNode.next = curr;
		// Set the previous nodes next reference to the new node
		curr.prev.next = newNode;
		// Set the current nodes previous reference to the new node
		curr.prev = newNode;
	}
	
	/**
	 * ASSIGNED
	 * 
	 * @param item
	 */
	public boolean insertOrderUnique(T item) {
		// Start at the first node in the list
		DNode curr = header.next;
		
		// Traverse the list until header is reached or finds a node with a larger value
		while (curr != header && curr.data.compareTo(item) < 0) {
			curr = curr.next;
		}
		
		// If finds a node with the same value as input value, does not insert the new node
		if (curr != header && curr.data.equals(item)) {
			return false;
		}
		
		// Insert the new before current node
		DNode newNode = new DNode(item);
		// Sets new nodes previous reference to current nodes previous
		newNode.prev = curr.prev;
		// Sets new nodes next reference to current node
		newNode.next = curr;
		// Sets previous nodes next reference to the new node
		curr.prev.next = newNode;
		// Sets the current nodes previous reference to the new node
		curr.prev = newNode;
		return true;
		}
	
	/**
	 * ASSIGNED
	 * PRE: this and rhs are sorted lists
	 * @param rhs
	 * @return list that contains this and rhs merged into a sorted list
	 * POST: returned list will not contain duplicates
	 */
	public DLinkedList<T> merge(DLinkedList<T> rhs) {
		// New linked list to hold result of the merge
		DLinkedList<T> result = new DLinkedList<T>();
		// Starts at the first node in both lists
		DNode curr1 = header.next;
		DNode curr2 = rhs.header.next;
		
		// Traverse both lists, comparing nodes and adding them to the result in order
		while (curr1 != header && curr2 != rhs.header) {
			if (curr1.data.compareTo(curr2.data)< 0) {
				result.add(curr1.data);
				curr1 = curr1.next;
			} else if (curr1.data.compareTo(curr2.data) > 0) {
				result.add(curr2.data);
				curr2 = curr2.next;
			} else {
				curr1 = curr1.next;
				curr2 = curr2.next;
			}
		}
		
		// Add remaining nodes from the first list to the result
		while (curr1 != header) {
			result.add(curr1.data);
			curr1 = curr1.next;
		}
		
		// Add remaining nodes from the second list to the result
		while (curr2 != rhs.header) {
			result.add(curr2.data);
			curr2 = curr2.next;
		}
		return result;
	}
}