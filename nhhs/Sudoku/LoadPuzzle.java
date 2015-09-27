/**
 * Used to load the 3 different Sudoku Puzzles.
 * 
 * @author GE
 * @version 1.0
 *
 */

/*#
 * There was a typo in the original LoadPuzzle; the easy puzzle was accessed with setPuzzle2 and the
 * medium with setPuzzle1. We emailed GE about this and then rectified it in LoadPuzzle, so
 * setPuzzle1 now accesses the easy puzzle and setPuzzle2 accesses the medium puzzle.
 */

public class LoadPuzzle {
	public int[][] puzzle;
	public LoadPuzzle(){}

	/**
	 * Easy Puzzle
	 * @return int[9][9] multidimensional array containing the selected puzzle
	 */
	private int[][] setPuzzle1(){
		//Easy
		puzzle = new int[9][9];
		puzzle[0][0]=1;
		puzzle[0][2]=9;
		puzzle[0][3]=5;
		puzzle[0][4]=4;
		puzzle[0][6]=8;
		puzzle[0][8]=6;
		puzzle[2][0]=5;
		puzzle[2][2]=3;
		puzzle[2][4]=7;
		puzzle[2][6]=4;
		puzzle[2][8]=1;
		puzzle[3][0]=9;
		puzzle[3][2]=5;
		puzzle[4][0]=8;
		puzzle[4][2]=4;
		puzzle[4][6]=1;
		puzzle[4][8]=3;
		puzzle[5][3]=1;
		puzzle[5][6]=5;
		puzzle[5][8]=8;
		puzzle[6][0]=4;
		puzzle[6][2]=8;	
		puzzle[6][4]=2;
		puzzle[6][6]=7;
		puzzle[6][8]=5;
		puzzle[8][0]=7;
		puzzle[8][2]=2;
		puzzle[8][4]=6;
		puzzle[8][5]=4;
		puzzle[8][6]=3;
		puzzle[8][8]=9;
		return puzzle;
	}
	/**
	 * Medium Puzzle
	 * @return int[9][9] multidimensional array containing the selected puzzle
	 */
	private int[][] setPuzzle2(){
		//Medium
		puzzle = new int[9][9];
		puzzle[0][1]= 5 ;
		puzzle[0][2]= 9 ;
		puzzle[0][4]= 4 ;
		puzzle[1][0]= 1 ;
		puzzle[1][4]= 5 ;
		puzzle[1][5]= 9 ;
		puzzle[1][7]= 6 ;
		puzzle[1][8]= 2 ;
		puzzle[2][4]= 2 ;
		puzzle[2][8]= 1 ;
		puzzle[3][0]= 6 ;
		puzzle[3][3]= 4 ;
		puzzle[3][6]= 5 ;
		puzzle[3][8]= 7 ;
		puzzle[4][1]= 2 ;
		puzzle[4][4]= 7 ;
		puzzle[4][7]= 1 ;
		puzzle[5][1]= 8 ;
		puzzle[5][5]= 5 ;
		puzzle[6][0]= 3 ;
		puzzle[6][7]= 7 ;
		puzzle[7][0]= 9 ;
		puzzle[7][3]= 5 ;
		puzzle[7][4]= 8 ;
		puzzle[7][8]= 6 ;
		puzzle[8][4]= 1 ;
		puzzle[8][6]= 8 ;
		puzzle[8][7]= 9 ;
		return puzzle;
	}
	/**
	 * Hard Puzzle
	 * @return int[9][9] multidimensional array containing the selected puzzle
	 */
	private int[][] setPuzzle3(){
		//Hard Puzzle
		puzzle = new int[9][9];
		puzzle[1][5]=3;
		puzzle[1][7]=8;
		puzzle[1][8]=5;
		puzzle[2][2]=1;
		puzzle[2][4]=2;
		puzzle[3][3]=5;
		puzzle[3][5]=7;
		puzzle[4][2]=4;
		puzzle[4][6]=1;
		puzzle[5][1]=9;
		puzzle[6][0]=5;
		puzzle[6][7]=7;
		puzzle[6][8]=3;
		puzzle[7][2]=2;
		puzzle[7][4]=1;
		puzzle[8][4]=4;
		puzzle[8][8]=9;
		return puzzle;
	}
	/**
	 * Returns puzzle type based on input number. 1 = Easy, 2 = Medium, 3 = Hard.
	 * @param x int value to select which puzzle to load
	 * @return int[9][9] multidimensional array containing the selected puzzle
	 */
	public int[][] setPuzzle(int x) {
		switch(x){
		case 1: return setPuzzle1();
		case 2: return setPuzzle2();
		case 3: return setPuzzle3();
		default: return new int[9][9];
		}
	}
}
