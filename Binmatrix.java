/*
 * Lab 1  CSIS225 Spring 2023
 * Binmatrix.java
 * 
 * Lightly modified from https://www.techiedelight.com/replace-occurrences-0-not-surrounded-1-binary-matrix/
 * @author Ira Goldstein
 * @version Spring 2023
 * 
 * An application that uses a recursive depth first search approach to replace all occurrences of zeros 
 * with ones where the connected zeros are not fully enclosed by ones from all sides 
 */
 
import java.util.Arrays;
 
public class Binmatrix {

	// Below arrays detail all eight possible movements
	private static final int[] row = {-1, -1, -1, 0, 0, 1, 1, 1};
	private static final int[] col = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	/** 
	* isValid checks to see  if (x, y) is a valid location
	*
	* @param x	x coordinate being checked
	* @param y	y coordinate being checked
	* @param M 	Width of the matrix
	* @param N 	Height of the matrix
	* @returns	boolean true if (x, y) is valid
	*/
	private static boolean isValid(int x, int y, int M, int N) {
		return (x >= 0 && x < M && y >= 0 && y < N);
	}

	/** 
	* DFS performs a rercursive depth first search of adjacent cells in the matrix.
	* Changes current cell to 1, then looks at adjacent cells. If that cell is 0, recurse.
	* Assumes that it is only called by cells that are zero.
	*
	* @param mat	an MxN matrix of zeros and ones
	* @param x		x coordinate of the current cell
	* @param y		y coordinate of the current cell
	*/ 
	private static void DFS(int[][] mat, int x, int y) {
		// `M × N` matrix
		int M = mat.length;
		int N = mat[0].length;
 
		// replace 0 by 1
		mat[x][y] = 1;
 
		// process all eight adjacent locations of the current cell and
		// recur for each valid location
		for (int k = 0; k < 8; k++)
		{
			int i = x + row[k];
			int j = y + col[k];
 
			// if the adjacent location at position `(i, j)` is
			// valid and has a value 0
			if (isValid(i, j, M, N) && mat[i][j] == 0) {
				DFS(mat, i, j);
			}
		}
	}

	/** 
	* replaceZeroes "walks" the first and last row, and the left and right
	* column checking to see if a cell contains a zero.  If it does, the matrix 
	* and the cell's coordinates are passed to DFS.
	*
	* @param mat	an MxN matrix of zeros and ones
	*/ 
	private static void replaceZeroes(int[][] mat)
	{
		// base case
		if (mat == null || mat.length == 0) {
			return;
		}
 
		// `M × N` matrix
		int M = mat.length;
		int N = mat[0].length;
 
		// check every element on the first and last column of the matrix
		for (int i = 0; i < M; i++)
		{
			if (mat[i][0] == 0) {
				DFS(mat, i, 0);
			}
			if (mat[i][N - 1] == 0) {
				DFS(mat, i, N - 1);
			}
		}
 
		// check every element on the first and last row of the matrix
		for (int j = 0; j < N - 1; j++)
		{
			if (mat[0][j] == 0) {
				DFS(mat, 0, j);
			}
 
			if (mat[M - 1][j] == 0) {
				DFS(mat, M - 1, j);
			}
		}
	}
 	/** 
	* Main method used to initialize the matrix and to call replaceZeroes.
	* Once done, it prints out the resulting matrix
	*
	* @param none
	*/
	public static void main(String[] args)
	{
		int[][] mat =
		{
			{ 1, 1, 1, 1, 0, 0, 1, 1, 1, 1 },
			{ 1, 0, 0, 1, 1, 0, 0, 1, 0, 1 },
			{ 1, 0, 0, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 0, 0, 1, 1, 0, 1 },
			{ 1, 1, 1, 1, 0, 0, 0, 1, 0, 1 },
			{ 1, 1, 0, 1, 1, 0, 1, 1, 0, 0 },
			{ 1, 0, 0, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 0, 0, 1, 1, 0, 0, 1, 0, 1 },
			{ 1, 1, 1, 0, 1, 0, 1, 0, 0, 1 },
			{ 1, 1, 1, 0, 1, 1, 1, 1, 1, 1 }
		};
 
		replaceZeroes(mat);
 
		for (var r: mat) {
			System.out.println(Arrays.toString(r));
		}
	}
}