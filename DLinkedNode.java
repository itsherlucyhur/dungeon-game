/**
 * This class represents the nodes of a doubly linked list 
 * that will be used to implement the priority queue.
 * @author chaejinhur
 *
 */

public class DLinkedNode<T> {
	// instance variables 
	private T dataItem; 
	private double priority; 
	private DLinkedNode<T> next; 
	private DLinkedNode<T> prev; 
	
	// creates a node storing the given data item and priority 
	public DLinkedNode (T data, double prio) {
		this.dataItem = data; 
		this.priority = prio; 
	}
	
	// creates an empty node with null dataItem and zero priority 
	public DLinkedNode() {
		dataItem = null; 
		priority = 0; 
	}
	
	// getter methods 
	public double getPriority() {
		return priority; 
	}
	
	public T getDataItem() {
		return dataItem; 
	}
	
	public DLinkedNode<T> getNext() {
		return next; 
	}
	
	public DLinkedNode<T> getPrev() {
		return prev; 
	}
	
	// setter methods
	public void setDataItem(T data) {
		this.dataItem = data; 
	}
	
	public void setNext(DLinkedNode<T> node) {
		this.next = node; 
	}
	
	public void setPrev(DLinkedNode<T> node) {
		this.prev = node; 
	}
	
	public void setPriority(double prio) {
		this.priority = prio; 
	}
	
}
