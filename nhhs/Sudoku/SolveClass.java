/**
 * Interface must be used for all submissions.
 * 
 * @author GE
 * @version 1.0
 */
public interface SolveClass {

	/** Loads the initial Sudoku puzzle.
	 * @param puzzle 2D int array holding the Sudoku puzzle
	 */
	void load(int[][] puzzle); 
	
	/**
	 * Solves the puzzle.
	 */
	void solve(); 

	/**
	 * Gets the final Sudoku puzzle.
	 * @return int[][] returns the 2D int array holding the final puzzle
	 */
	int[][] getPuzzle();

}
