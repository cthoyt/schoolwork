/**
 * Class to print a Sudoku Puzzle to console.
 * 
 * @author GE
 * @version 1.0
 */
public class PrintPuzzle {
	/**
	 * Default constructor. Prints Sudoku puzzle.
	 * @param puzzle the 2D int array to print
	 */
	public PrintPuzzle(int[][] puzzle) {

		for (int r = 0; r < 9; ++r) {
			if (r % 3 == 0)
				System.out.println(" -----------------------");
			for (int c = 0; c < 9; ++c) {
				if (c % 3 == 0)
					System.out.print("| ");
				if(puzzle[r][c]==0)
					System.out.print(" ");
				else
					System.out.print(Integer.toString(puzzle[r][c]));
				System.out.print(" ");
			}
			System.out.println("|");
		}
		System.out.println(" -----------------------");
	}
}
