package Assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Driver {

	/**
	 * 
	 * @param start
	 * @param end
	 * find a path through the maze
	 * if found, output the path from start to end
	 * if not path exists, output a message stating so
	 * 
	 */
	// implement this method
	public static void depthFirst(MazeCell start, MazeCell end) {
		// Creating the stack to keep track of cells in maze
	    MyStack<MazeCell> stack = new MyStack<MazeCell>();
	    
	    // Marks the start of maze and adds it to the stack
	    start.visit();
	    stack.push(start);
	    
	    // 2-d representation of maze grid
	    MazeCell[][] grid = new MazeCell[start.getRow()+1][start.getCol()+1];

	    // As long as there are elements in the stack, it will continue searching for a path
	    while (!stack.empty()) {
	    	// Gets the top element from stack
	        MazeCell current = stack.top();
	        // If current element is the end element, then path was found
	        if (current.equals(start)) {
	            System.out.println("Found path that ends at " + end.getRow() + ", " + end.getCol());
	            return;
	        }
	        int row = current.getRow();
	        int col = current.getCol();
	        int direction = current.getDirection();

	        // Checks each direction to see if next element should be moved to
	        if (direction == 0 && row > 0 && grid[row - 1][col].unVisited()) {
	        	// If it is up and unvisited, move to it
	            MazeCell next = grid[row - 1][col];
	            current.advanceDirection();
	            next.visit();
	            stack.push(next);
	        } else if (direction == 1 && col < grid[0].length - 1 && grid[row][col + 1].unVisited()) {
	        	// If it is right and unvisited, move to it
	            MazeCell next = grid[row][col + 1];
	            current.advanceDirection();
	            next.visit();
	            stack.push(next);
	        } else if (direction == 2 && row < grid.length - 1 && grid[row + 1][col].unVisited()) {
	        	// If it is down and unvisited, move to it
	            MazeCell next = grid[row + 1][col];
	            current.advanceDirection();
	            next.visit();
	            stack.push(next);
	        } else if (direction == 3 && col > 0 && grid[row][col - 1].unVisited()) {
	        	// If it is left and unvisited, move to it
	            MazeCell next = grid[row][col - 1];
	            current.advanceDirection();
	            next.visit();
	            stack.push(next);
	            // Goes back one if there are no unvisited cells
	        } else {
	            stack.pop();
	            current.reset();
	        }
	    }
	    // Prints no path found if algorithm did not work properly
	    System.out.println("No path found.");
	}
	public static void main(String[] args) throws FileNotFoundException {
		
		// create the Maze from the file
		Scanner fin = new Scanner(new File("Maze.in"));
		
		// read in the rows and cols
		int rows = fin.nextInt();
		int cols = fin.nextInt();
		
		// create the maze
		int [][] grid = new int[rows][cols];
		
		// read in the data from the file to populate
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				grid[i][j] = fin.nextInt();
			}
		}
		
		// look at it
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (grid[i][j] == 3)
					System.out.print("S ");
				else if (grid[i][j] == 4)
					System.out.print("E ");
				else
					System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
		
		// make a 2-d array of cells
		MazeCell[][] cells = new MazeCell[rows][cols];
		
		// populate with MazeCell obj - default obj for walls
		MazeCell start = new MazeCell(), end = new MazeCell();
		
		// iterate over the grid, make cells and set coordinates
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				// make a new cell
				cells[i][j] = new MazeCell();
				// if it isn't a wall, set the coordinates
				if (grid[i][j] != 0) {
					cells[i][j].setCoordinates(i,j);
					//look for the start and end cells
					if (grid[i][j] == 3)
						start = cells[i][j];
					if (grid[i][j] == 4)
						end = cells[i][j];
				}
			}
		}
		
		// testing
		System.out.println("start:" + start + "end:" + end);
		
		// solve it!
		depthFirst(start,end);
	}
}
