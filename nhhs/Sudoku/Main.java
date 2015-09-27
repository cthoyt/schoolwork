/**
 * Main.java
 * Drives the program by loading the puzzles, printing the originals, solving, outputting the solved puzzle, and printing the solve time
 * North Haven High School
 * Team The Fedoras
 * Teacher Marilynn Kramar
 * Students Charlie Hoyt, Lily Seropian, Mike Perrone, Vincent Jakiela, Victor Wang, and Prakhar Bansal
 * Email: cthoyt@gmail.com, lilyseropian@gmail.com, mike.j.perrone@gmail.com, vtjakiela@yahoo.com, wangvictor09@hotmail.com, prakhar_bansal2005@hotmail.com, Kramar.Marilynn@north-haven.k12.ct.us
 * @author The Fedoras
 * We pledge that our team has followed all rules for the case study.
 */

/*#
 * As each time we ran this program the outputted time was different, even when we averaged 1000 times with the same puzzle,
 * we hope you will run the programs multiple times when scoring the timing to get the most accurate results.
 */

/*#
 * There was a typo in the original LoadPuzzle; the easy puzzle was accessed with setPuzzle2 and the
 * medium with setPuzzle1. We emailed GE about this and then rectified it in LoadPuzzle, so
 * setPuzzle1 now accesses the easy puzzle and setPuzzle2 accesses the medium puzzle.
 */

public class Main
{
    /*#
     * There was a typo in the original LoadPuzzle; the easy puzzle was accessed with setPuzzle2 and the
     * medium with setPuzzle1. We emailed GE about this and then rectified it in LoadPuzzle, so
     * setPuzzle1 now accesses the easy puzzle and setPuzzle2 accesses the medium puzzle.
     */
    public static void main(String[] args)
    {
        long start=0, solveTime=0;
        LoadPuzzle lp = new LoadPuzzle();
        int[][] puzzle;

        Solve s = new Solve();
       
        for(int puzzleNumber = 1; puzzleNumber <= 3; puzzleNumber++)
        {
            solveTime = 0;
            puzzle = lp.setPuzzle(puzzleNumber);
            new PrintPuzzle(puzzle);
            s.load(puzzle);
            start = System.currentTimeMillis();
            s.solve();
            solveTime = System.currentTimeMillis()-start;
            new PrintPuzzle(s.getPuzzle());
            System.out.println("Solver ran for "+ solveTime +" ms");
        }
    }
}