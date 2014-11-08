/**
 * Triangulation.java
 * 
 * @author	Derek Brown <djb3718@rit.edu>
 *
 * Purpose:	Find the minimum possible length of triangulation for a convex
 * 		polygon.
 */

import java.util.Scanner;

public class Triangulation {
	
	// Attributes
	
	int size;
	double[] x;
	double[] y;
	double[][] S;
	
	// Constructor
	
	/**
	 * Constructor for creating a Triangulation instance, used to store 
	 * useful information needed for completing the algorithm, such as,
	 * the number of data points, the x-coordinates, the y-coordinates,
	 * and the solution array.
	 * 
	 * @param size	The number of vertices
	 * @param x	The array containing all of the x-coordinates
	 * @param y	The array containing all of the y-coordinates
	 */
	public Triangulation( int size, double[] x, double[] y ) {
		this.size = size;
		this.x = x;
		this.y = y;
		this.S = new double[size+1][size+1];
		for( int d = 1 ; d < size+1 ; d++ ) {
			S[d][d] = 0;
		}//end for
	}//end Triangulation constructor
	
	// Methods
	
	/**
	 * Helper function, used to calculate the length of a line segment.
	 * 
	 * @param i	The i-th vertex
	 * @param j	The j-th vertex
	 * 
	 * @return	0, if j-i < 2
	 * 		length of the line segment connecting the two vertices, 
	 * 		otherwise.
	 */
	public double dLength( int i, int j ) {
		if( j-i >= 2 ) {
			return Math.sqrt( Math.pow( x[i-1] - x[j-1], 2 ) +
				Math.pow( y[i-1] - y[j-1], 2 ) );
		}//end if
		return 0;
	}//end calculateSegmentLength
	
	/**
	 * The main algorithm logic, fills in the solution array.
	 */
	public void triangulation() {
		for( int l = 2 ; l <= size ; l++ ) {
			for( int i = 1 ; i <= size - l + 1 ; i++ ) {
				int j = i + l - 1;
				S[i][j] = Double.MAX_VALUE;
				for( int k = i ; k <= j - 1 ; k++ ) {
					double temp = S[i][k] + S[k+1][j] +
					dLength( i, k ) + dLength( k, j );
					if( temp < S[i][j] ) {
						S[i][j] = temp;
					}//end if
				}//end for k
			}//end for i
		}//end for l
	}//end triangulation

	/**
	 * The main logic for the program, reads in data from the user, creates
	 * an object to store the information and then begins the algorithm, the
	 * algorithm fills in the solution array and upon completion displays
	 * the result to the user.
	 * 
	 * @param args	Command line arguments, unused.
	 */
	public static void main( String[] args ) {
		Scanner sc = new Scanner( System.in );
		String input;
		input = sc.next();
		int numVals = Integer.parseInt( input );
		double valX;
		double valY;
		double[] x = new double[numVals];
		double[] y = new double[numVals];
		for( int i = 0 ; i < numVals ; i++ ) {
			input = sc.next();
			valX = Double.parseDouble( input );
			input = sc.next();
			valY = Double.parseDouble( input );
			x[i] = valX;
			y[i] = valY;
		}//end for
		sc.close();
		Triangulation T = new Triangulation( numVals, x, y );
		T.triangulation();
		System.out.printf( "%.4f\n", T.S[1][T.size] );
	}//end main
}//end Triangulation
