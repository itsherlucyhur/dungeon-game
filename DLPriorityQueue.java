
public class DLPriorityQueue<T> implements PriorityQueueADT<T> {
	// private instance variables
	private DLinkedNode<T> front; 	// reference to the first node of doubly linked list 
	private DLinkedNode<T> rear; 	// reference to last node of doubly linked list 
	private int count; 				// number of data items in priority queue 

	public DLPriorityQueue() {
		count = 0; 			// initialize count	
		front = rear = null; // empty list 
	}
	
	// adds priority queue to given dataItem with its associated priority 
	public void add (T dataItem, double priority) {
		DLinkedNode<T> newNode = new DLinkedNode<>(dataItem, priority); 
		if (front == null) {	// when queue is empty 
			front = rear = newNode; 
		}
		// add data item with smallest priority to front 
		else if (priority <= front.getPriority()) { 
			newNode.setNext(front); 
			front.setPrev(newNode); 
			front = newNode; // add to front 
		} 
		// add data item with highest priority to rear 
		else if (priority >= rear.getPriority()) {
			rear.setNext(newNode); 
			newNode.setPrev(rear); 
			rear = newNode; 	// add to rear 
		} 
		else { 
			DLinkedNode<T> current = front.getNext(); 
	        while (current != null && current.getPriority() < priority) {
				current = current.getNext(); 
			} 
			newNode.setNext(current); 
			newNode.setPrev(current.getPrev());
	        current.getPrev().setNext(newNode);
	        current.setPrev(newNode);
		}
		count++; 
	} 	
	
	// changes the priority of given dataItem to the new value 
	public void updatePriority(T dataItem, double newPriority) throws InvalidElementException {
		DLinkedNode<T> current = front; 
		boolean found = false; 
		
		while (current != null) {
			if (current.getDataItem().equals(dataItem)) {
				found = true; 
				break; 
			}
			current = current.getNext(); 
		}
		
		if (found == true) {
			// remove current item from doubly linked list
			if (current == front) {
				front = current.getNext(); 
				if (front != null) {
					front.setPrev(null); 
				}
				else {
					rear = null; 
				}
			}
			else if (current == rear) {
				rear = current.getPrev(); 
				rear.setNext(null); 
			}
			else {
				current.getPrev().setNext(current.getNext()); 
				current.getNext().setPrev(current.getPrev());
			}
			
			count--; 	
			add(dataItem, newPriority);		// update new priority to list 
		}
		
		if (found == false) {
			throw new InvalidElementException("Data not found in priority queue");
		}
	}
	
		
	// removes and returns the data item in the priority queue w smallest priority 
	public T removeMin() throws EmptyPriorityQueueException {
		if (count == 0) {
			throw new EmptyPriorityQueueException("Empty queue"); 
		}
		
	    DLinkedNode<T> min = front;
	    DLinkedNode<T> current = front.getNext();
	    
	    if (current == null) {
	    	front = rear = null; 
	    }
	    else {
	    	while (current != null) {
		        if (current.getPriority() < min.getPriority()) {
		            min = current;
		        }
		        current = current.getNext();
		    }
	    	
	    	if (min == front) {
		        front = front.getNext();
		        front.setPrev(null); 
			} else if (min == rear) {
				rear = rear.getPrev();
				rear.setNext(null);
			} else {
				min.getPrev().setNext(min);
				min.getNext().setPrev(min.getPrev());
			}
	    }
	    
		count--;
		return min.getDataItem();
	}
	
	public boolean isEmpty() {
		// checks if priority queue is empty 
		return (count == 0); 
	}
	
	public int size() {
		// returns the number of data items in the priority queue 
		return count; 
	}
	
	// string representation of priority queue 
	public String toString() {
		String s = ""; 
		DLinkedNode<T> current = front; 
		while (current != null) {
			// concatenate data items and return them into strings 
			s += current.getDataItem().toString(); 
			current = current.getNext(); 
		}
		return s;  
	}
	
	public DLinkedNode<T> getRear() {
		return rear; 
	}
	
}
