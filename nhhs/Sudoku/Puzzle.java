/**
 * Included only to show where the timing code will be placed for the algorithm speed ranking.
 * This should NOT be used as part of your solution.
 * 
 * @author GE
 * @version 1.0
 */
public class Puzzle {
	public int[][] puzzle;
	public long start;
	
	/**
	 * Default constructor.
	 * Prints starting puzzle
	 */
	public Puzzle(){
		LoadPuzzle lp = new LoadPuzzle();
		puzzle = lp.setPuzzle(2);
		new PrintPuzzle(puzzle);
		Solve();
	}
	
	/**
	 * Solves the puzzle and records the speed of algorithm.
	 */
	public void Solve(){
		Solve s = new Solve();
		s.load(puzzle);
		start = System.currentTimeMillis();
		s.solve();
		System.out.println("Solver ran for "+(System.currentTimeMillis()-start)+" ms");
	}
}
