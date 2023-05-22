package Assignment;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	// creating the array for storage
	private int[][] edges;
	
	public Graph(int[][]edges) {
		// initializing edges
		this.edges = edges;
	}
	
	public int getDistance(int vertex1, int vertex2) {
		// both vertices must be in the graph
		if (vertex1 < edges.length && vertex2 < edges.length) {
			// will return distance if there's an edge, otherwise, returns the max int value
			return edges[vertex1][vertex2] != 0 ? edges[vertex1][vertex2] : Integer.MAX_VALUE;
		} else {
			// returns the max int value if either vertex is not in range
			return Integer.MAX_VALUE;
		}
	}
	
	public List<Integer> getNeighbors(int vertex){
		// create the array list that stores each neighbor
		List<Integer> neighbors = new ArrayList<>();
		// if vertex is within the graph
		if (vertex < edges.length) {
			// go through the row for the vertex
			for (int i = 0; i < edges[vertex].length; i++) {
				// if there is an edge, add connected vertex to list
				if (edges[vertex][i] != 0) {
					neighbors.add(i);
				}
			}
		}
		return neighbors;
	}

	public void print() {
			System.out.println("Graph connections and distance: ");
			// goes through vertices
			for (int i = 0; i < edges.length; i++) {
				System.out.print(i + ": ");
				// goes through each connection if there is an edge
				for (int j = 0; j < edges[i].length; j++) {
					if (edges[i][j] != 0) {
						System.out.print(j + "(" + edges[i][j] + ") ");
					}
				}
				System.out.println();
			}
	}
	
	public int getNumVertices() {
		// returns length of the edges array
		return edges.length;
	}
	
	public int getNumEdges() {
		int count = 0;
		// goes through the vertices
		for (int i = 0; i < edges.length; i++) {
			// goes through each vertices connection if there is an edge and goes up 1
			for (int j = 0; j < i; j++) {
				if (edges[i][j] != 0) {
					count++;
				}
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		int[][] edges = new int[15][15];
		
		// assigns the distance between each edge
		edges[0][1] = 14;
		edges[0][11] = 14;
		edges[0][12] = 9;
		edges[1][11] = 9;
		edges[1][10] = 17;
		edges[1][2] = 9;
		edges[2][10] = 13;
		edges[2][9] = 20;
		edges[2][3] = 5;
		edges[3][9] = 19;
		edges[3][4] = 17;
		edges[4][9] = 4;
		edges[4][5] = 16;
		edges[5][9] = 12;
		edges[5][7] = 9;
		edges[5][6] = 8;
		edges[6][7] = 6;
		edges[7][9] = 12;
		edges[7][8] = 7;
		edges[7][13] = 23;
		edges[7][14] = 10;
		edges[8][14] = 6;
		edges[8][10] = 5;
		edges[8][9] = 13;
		edges[9][10] = 7;
		edges[10][14] = 8;
		edges[10][11] = 18;
		edges[11][14] = 9;
		edges[11][12] = 15;
		edges[12][14] = 15;
		edges[12][13] = 11;
		
		// for loop that goes through vertices and their connections
		for (int i = 0; i < 15; i++) {
		    for (int j = 0; j < i; j++) {
		        edges[i][j] = edges[j][i];
		    }
		}
		
		// creates a graph object with the edges array
		Graph graph = new Graph(edges);
		
		System.out.println("The graph has " + graph.getNumVertices()+ " vertices");
		System.out.println("The graph has " + graph.getNumEdges() + " edges");
		graph.print();
	}

}
