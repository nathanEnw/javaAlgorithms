package project;

public class SinglyLinkedList<T extends Comparable<T>>{

	// Declare head and data
	public Node<T> head;
	public int data;
	
	// Empty new Singly Linked List
	public SinglyLinkedList() {
		head= null;
		data= 0;
	}
	
	// Copies the Singly Linked List
	public SinglyLinkedList(SinglyLinkedList<T> aList) {
		head = null;
		data = 0;
		Node<T> current = aList.head;
		while (current != null) {
			// Inserts a new node at the end of the list
			insert(current.getSize());
			current = current.getNext();
		}
	}
	
	// Gets the first element in the list
	public T front() {
		if(head == null) {
			return null;
		}
		return head.getSize();
	}
	
	// Gets the last element in the list
	public T back() {
		if (head == null) {
			return null;
		}
		Node<T> current = head;
		while (current.getNext() != null) {
			current = current.getNext();
		}
		return current.getSize();
	}
	
	// Inserts a new node at the end of the list
	public void insert(T val) {
		Node<T> newNode = new Node<>(val);
		if (head == null) {
			head = newNode;
		} else {
			Node<T> current = head;
			while (current.getNext() != null) {
				current = current.getNext();
			}
			current.setNext(newNode);
		}
		data++;
	}
	
	// Removes the first element in the list
	public void removeFront() {
		if (head == null) {
			return;
		}
		head = head.getNext();
		data--;
	}
	
	// Removes the last element in the list
	public void removeBack() {
		if (this.head == null) {
			return;
		}
		if (head.getNext() == null) {
			head = null;
			data--;
			return;
		}
		Node<T> current = head;
		while (current.getNext().getNext() != null) {
			current = current.getNext();
		}
		current.setNext(null);
		data--;
	}
	
	// Checks if the list is empty 
	public boolean empty() {
		return head == null;
	}
	
	// Number of elements in the list
	public int data() {
		return data;
	}
	
	// Reverses the order of the list
	public void reverseOrder() {
		if (head == null || head.getNext() == null) {
			return;
		}
		Node<T> prev = null;
		Node<T> current = head;
		while (current != null) {
			Node<T> next = current.getNext();
			current.setNext(prev);
			prev = current;
			current = next;
		}
		head = prev;
	}
	
	// Merges the two lists into one sorted linked list
	public void merge(SinglyLinkedList<T> aList) {
		// If the list is empty, nothing happens
		if (aList.head == null) {
			return;
		}
		// If the list is empty, sets the head of the list to be the head of the given list
		if (head == null) {
			head = aList.head;
			data = aList.data;
			return;
		}
		Node<T> current1 = head;
		Node<T> current2 = aList.head;
		Node<T> prev = null;
		// Traverses both lists and compares the elements until the end of one is reached
		while (current1 != null && current2 != null) {
			// If the current element is smaller than the current element in the other list, moves to the next element
			if (current1.getSize().compareTo(current2.getSize()) < 0){
				prev = current1;
				current1 = current1.getNext();
			} else {
				Node<T> next = current2.getNext();
				if (prev == null) {
					head = current2;
				}else {
					prev.setNext(current2);
					prev = current2;
					current2 = next;
					data++;
				}
			}
			// Add any remaining elements to the back of the list
			if (current2 != null) {
				prev.setNext(current2);
				while (current2.getNext() != null) {
					current2 = current2.getNext();
				}
				// Updates the number of elements in the current list to reflect new elements added
				data += aList.data - (current2 == aList.head ? 1 : 0);
			}
		}
	}
	
	public static class Node<T>{
		private T size;
		private Node<T> next;
		
		// Initialize the node with size
		public Node(T size) {
			this.size = size;
			next = null;
		}
		
		// Getter to return size of the node
		public T getSize() {
			return size;
		}
		
		// Getter to return the reference of the next node
		public Node<T> getNext(){
			return next;
		}
		
		// Setter to set the reference of the next node
		public void setNext(Node<T> next) {
			this.next = next;
		}
	}
	
	// Print method that allows me to print each list before merging
	public void print() {
		// Reference to the head node
		Node<T> current = head;
		// Loop through the linked list until it reaches the end
		while (current != null) {
			// Prints the size of each node
			System.out.print(current.getSize() + " ");
			// Updates the reference to the next node
			current = current.getNext();
		}
		System.out.println();
	}
	public static void main(String[] args) {
		SinglyLinkedList<Integer> myList = new SinglyLinkedList<>();
		
		myList.insert(1);
		myList.insert(2);
		myList.insert(3);
		
		System.out.println("List: ");
		myList.print();
		
		System.out.println("Front: " + myList.front());
		System.out.println("Back: " + myList.back());
		
		System.out.println("Number of elements: " + myList.data());
		
		myList.reverseOrder();
		System.out.println("Reversed list: ");
		myList.print();
		
		myList.removeFront();
		myList.removeBack();
		
		System.out.println("Number of elements after removal: " + myList.data());
		System.out.println("Empty list? " + myList.empty());
		
		myList.reverseOrder();
		
		SinglyLinkedList<Integer> secondList = new SinglyLinkedList<>();
		secondList.insert(4);
		secondList.insert(5);
		secondList.insert(6);
		
		System.out.println("Second list: ");
		secondList.print();
		
		myList.merge(secondList);
		System.out.println("Merged lists: ");
		Node<Integer> current = myList.head;
		while (current != null) {
			System.out.print(current.getSize() + " ");
			current = current.getNext();
		}
	}

}
