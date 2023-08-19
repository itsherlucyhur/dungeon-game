/**
 * This class computes the shortest path from 
 * the initial chamber to the exit. 
 * @author chaejinhur
 *
 */
public class FindShortestPath {
	// creates an object passing in the name of input file 
	public static void findShortestPath(Dungeon dungeon) {
			// initialize empty priority queue
			DLPriorityQueue<Hexagon> queue = new DLPriorityQueue<>(); 
			// get the starting chamber from dungeon object and add it to priority queue 
			Hexagon start = dungeon.getStart();
			start.getDistanceToStart(); 
			queue.add(start, 0); 
			start.markEnqueued();	// mark the chamber as enqueued 
			
			// while the priority queue isn't empty: 
			while (!queue.isEmpty()) {
				Hexagon current = queue.removeMin(); 	// remove smallest priority
				current.markDequeued(); 	// mark it as dequeued 
				
				// if current chamber is the exit: 
				if (current.isExit()) {
					return; 
				}
				
				// if current chamber has a dragon or the neighbours have a dragon: 
				if (current.isDragon() && current.getNeighbour(0).isDragon()) {
					continue; 
				}
				
				for (int i = 0; i <= 5; i++) {
					Hexagon neighbour = current.getNeighbour(i);
					if (neighbour != null && !neighbour.isWall() && !neighbour.isMarkedDequeued()) {
						int D = 1 + current.getDistanceToStart(); 
							// if distance of neighbour to initial chamber is larger than D: 
							if (neighbour.getDistanceToStart() > D) {
								neighbour.setDistanceToStart(D);		// set initial chamber to D
								neighbour.setPredecessor(current); 
								if (neighbour.isMarkedEnqueued() && D != 0) {
									queue.updatePriority(neighbour, neighbour.getDistanceToStart() + neighbour.getDistanceToExit(dungeon)); 
								}
								else {
									queue.add(neighbour, neighbour.getDistanceToStart() + neighbour.getDistanceToExit(dungeon));
									neighbour.markEnqueued(); 
								}
							}
						}
					}
				}
		}
	
	public static void main(String[] args) {
		try {
			if (args.length < 1) 
				throw new Exception("No input file specified"); 
			String dungeonFileName = args[0];
            Dungeon dungeon = new Dungeon(dungeonFileName);
            findShortestPath(dungeon);
		} 
		catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
		}
	}
}

	