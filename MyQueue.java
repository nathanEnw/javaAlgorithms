package Assignment;

import java.util.LinkedList;

public class MyQueue<T> {
	private LinkedList<T> list;
	
	public MyQueue() {
		list = new LinkedList<T>();
	}
	
	public void push(T v) {
		list.addLast(v);
	}
	
	public void pop() {
		list.removeFirst();
	}
	
	public boolean empty() {
		return list.isEmpty();
	}
	
	public int size() {
		return list.size();
	}
	
	public T front() {
		return list.getFirst();
	}
}
