package Assignment;

import java.util.LinkedList;

public class MyStack<T> {
	private LinkedList<T> list;
	
	public MyStack() {
		list = new LinkedList<T>();
	}
	
	public void push(T v) {
		list.addFirst(v);
	}
	
	public int size() {
		return list.size();
	}
	
	public boolean empty() {
		return list.isEmpty();
	}
	
	public T top() {
		return list.getFirst();
	}
	
	public void pop() {
		list.removeFirst();
	}
}