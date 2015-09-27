/**
 * Solve.java
 * Contains methods to load, solve, and print a Sudoku puzzle
 * All methods have the implied precondition that any locations given as parameters are valid on a 9x9 grid.
 * All numbers are referenced as 0-8 rather than 1-9. Conversion occurs in both directions.
 * North Haven High School
 * Team The Fedoras
 * Teacher Marilynn Kramar
 * Students Charlie Hoyt, Lily Seropian, Mike Perrone, Vincent Jakiela, Victor Wang, and Prakhar Bansal
 * Email: cthoyt@gmail.com, lilyseropian@gmail.com, mike.j.perrone@gmail.com, vtjakiela@yahoo.com, wangvictor09@hotmail.com, prakhar_bansal2005@hotmail.com, Kramar.Marilynn@north-haven.k12.ct.us
 * @author The Fedoras
 * @version 1.8 unrolled
 * We pledge that our team has followed all rules for the case study.
 */

/*#
 * Following are two versions of code: rolled and unrolled. Loop unrolling (writing out each iteration of a loop)
 * looks ugly, but is much faster (because it eliminates comparisons), so because part of the scoring is based on time,
 * we wrote the code with loops, then unrolled all that we could. The original code is included, commented out, for the
 * sake of readability. Please look at the commented versions for the documentation of how each section works. 
 * We also pledge that the commented versions do the same thing that the unrolled do.
 */

/*#
 * There was a typo in the original LoadPuzzle; the easy puzzle was accessed with setPuzzle2 and the
 * medium with setPuzzle1. We emailed GE about this and then rectified it in LoadPuzzle, so
 * setPuzzle1 now accesses the easy puzzle and setPuzzle2 accesses the medium puzzle.
 */

public class Solve implements SolveClass
{   
    /**
     * Values in the Sudoku grid represented as Cells rather than numbers
     */
    private static Cell[][] grid = new Cell[9][9];
    
    //original
    /*#public void load(int[][] puzzle)
    {
        Cell.numSolved = 0;
        for(int i=0; i<9; i+=1)
            for(int j=0; j<9; j+=1)
                //if the value at puzzle[i][j], create a default Cell (of undetermined value), else create a Cell with the value at grid[i][j]
                grid[i][j] = (puzzle[i][j] == 0) ? new Cell() : new Cell(puzzle[i][j] - 1);
    }*/
    
	/**
     * Creates a puzzle of Cells out of a puzzle of ints
     * @param puzzle the puzzle returned by the setPuzzle function in the LoadPuzzle class
     */
    public void load(int[][] puzzle)
    {
        Cell.numSolved = 0;
        grid[0][0] = (puzzle[0][0] == 0) ? new Cell() : new Cell(puzzle[0][0] - 1);
        grid[0][1] = (puzzle[0][1] == 0) ? new Cell() : new Cell(puzzle[0][1] - 1);
        grid[0][2] = (puzzle[0][2] == 0) ? new Cell() : new Cell(puzzle[0][2] - 1);
        grid[0][3] = (puzzle[0][3] == 0) ? new Cell() : new Cell(puzzle[0][3] - 1);
        grid[0][4] = (puzzle[0][4] == 0) ? new Cell() : new Cell(puzzle[0][4] - 1);
        grid[0][5] = (puzzle[0][5] == 0) ? new Cell() : new Cell(puzzle[0][5] - 1);
        grid[0][6] = (puzzle[0][6] == 0) ? new Cell() : new Cell(puzzle[0][6] - 1);
        grid[0][7] = (puzzle[0][7] == 0) ? new Cell() : new Cell(puzzle[0][7] - 1);
        grid[0][8] = (puzzle[0][8] == 0) ? new Cell() : new Cell(puzzle[0][8] - 1);
        
        grid[1][0] = (puzzle[1][0] == 0) ? new Cell() : new Cell(puzzle[1][0] - 1);
        grid[1][1] = (puzzle[1][1] == 0) ? new Cell() : new Cell(puzzle[1][1] - 1);
        grid[1][2] = (puzzle[1][2] == 0) ? new Cell() : new Cell(puzzle[1][2] - 1);
        grid[1][3] = (puzzle[1][3] == 0) ? new Cell() : new Cell(puzzle[1][3] - 1);
        grid[1][4] = (puzzle[1][4] == 0) ? new Cell() : new Cell(puzzle[1][4] - 1);
        grid[1][5] = (puzzle[1][5] == 0) ? new Cell() : new Cell(puzzle[1][5] - 1);
        grid[1][6] = (puzzle[1][6] == 0) ? new Cell() : new Cell(puzzle[1][6] - 1);
        grid[1][7] = (puzzle[1][7] == 0) ? new Cell() : new Cell(puzzle[1][7] - 1);
        grid[1][8] = (puzzle[1][8] == 0) ? new Cell() : new Cell(puzzle[1][8] - 1);
        
        grid[2][0] = (puzzle[2][0] == 0) ? new Cell() : new Cell(puzzle[2][0] - 1);
        grid[2][1] = (puzzle[2][1] == 0) ? new Cell() : new Cell(puzzle[2][1] - 1);
        grid[2][2] = (puzzle[2][2] == 0) ? new Cell() : new Cell(puzzle[2][2] - 1);
        grid[2][3] = (puzzle[2][3] == 0) ? new Cell() : new Cell(puzzle[2][3] - 1);
        grid[2][4] = (puzzle[2][4] == 0) ? new Cell() : new Cell(puzzle[2][4] - 1);
        grid[2][5] = (puzzle[2][5] == 0) ? new Cell() : new Cell(puzzle[2][5] - 1);
        grid[2][6] = (puzzle[2][6] == 0) ? new Cell() : new Cell(puzzle[2][6] - 1);
        grid[2][7] = (puzzle[2][7] == 0) ? new Cell() : new Cell(puzzle[2][7] - 1);
        grid[2][8] = (puzzle[2][8] == 0) ? new Cell() : new Cell(puzzle[2][8] - 1);
       
        grid[3][0] = (puzzle[3][0] == 0) ? new Cell() : new Cell(puzzle[3][0] - 1);
        grid[3][1] = (puzzle[3][1] == 0) ? new Cell() : new Cell(puzzle[3][1] - 1);
        grid[3][2] = (puzzle[3][2] == 0) ? new Cell() : new Cell(puzzle[3][2] - 1);
        grid[3][3] = (puzzle[3][3] == 0) ? new Cell() : new Cell(puzzle[3][3] - 1);
        grid[3][4] = (puzzle[3][4] == 0) ? new Cell() : new Cell(puzzle[3][4] - 1);
        grid[3][5] = (puzzle[3][5] == 0) ? new Cell() : new Cell(puzzle[3][5] - 1);
        grid[3][6] = (puzzle[3][6] == 0) ? new Cell() : new Cell(puzzle[3][6] - 1);
        grid[3][7] = (puzzle[3][7] == 0) ? new Cell() : new Cell(puzzle[3][7] - 1);
        grid[3][8] = (puzzle[3][8] == 0) ? new Cell() : new Cell(puzzle[3][8] - 1);
       
        grid[4][0] = (puzzle[4][0] == 0) ? new Cell() : new Cell(puzzle[4][0] - 1);
        grid[4][1] = (puzzle[4][1] == 0) ? new Cell() : new Cell(puzzle[4][1] - 1);
        grid[4][2] = (puzzle[4][2] == 0) ? new Cell() : new Cell(puzzle[4][2] - 1);
        grid[4][3] = (puzzle[4][3] == 0) ? new Cell() : new Cell(puzzle[4][3] - 1);
        grid[4][4] = (puzzle[4][4] == 0) ? new Cell() : new Cell(puzzle[4][4] - 1);
        grid[4][5] = (puzzle[4][5] == 0) ? new Cell() : new Cell(puzzle[4][5] - 1);
        grid[4][6] = (puzzle[4][6] == 0) ? new Cell() : new Cell(puzzle[4][6] - 1);
        grid[4][7] = (puzzle[4][7] == 0) ? new Cell() : new Cell(puzzle[4][7] - 1);
        grid[4][8] = (puzzle[4][8] == 0) ? new Cell() : new Cell(puzzle[4][8] - 1);
        
        grid[5][0] = (puzzle[5][0] == 0) ? new Cell() : new Cell(puzzle[5][0] - 1);
        grid[5][1] = (puzzle[5][1] == 0) ? new Cell() : new Cell(puzzle[5][1] - 1);
        grid[5][2] = (puzzle[5][2] == 0) ? new Cell() : new Cell(puzzle[5][2] - 1);
        grid[5][3] = (puzzle[5][3] == 0) ? new Cell() : new Cell(puzzle[5][3] - 1);
        grid[5][4] = (puzzle[5][4] == 0) ? new Cell() : new Cell(puzzle[5][4] - 1);
        grid[5][5] = (puzzle[5][5] == 0) ? new Cell() : new Cell(puzzle[5][5] - 1);
        grid[5][6] = (puzzle[5][6] == 0) ? new Cell() : new Cell(puzzle[5][6] - 1);
        grid[5][7] = (puzzle[5][7] == 0) ? new Cell() : new Cell(puzzle[5][7] - 1);
        grid[5][8] = (puzzle[5][8] == 0) ? new Cell() : new Cell(puzzle[5][8] - 1);
        
        grid[6][0] = (puzzle[6][0] == 0) ? new Cell() : new Cell(puzzle[6][0] - 1);
        grid[6][1] = (puzzle[6][1] == 0) ? new Cell() : new Cell(puzzle[6][1] - 1);
        grid[6][2] = (puzzle[6][2] == 0) ? new Cell() : new Cell(puzzle[6][2] - 1);
        grid[6][3] = (puzzle[6][3] == 0) ? new Cell() : new Cell(puzzle[6][3] - 1);
        grid[6][4] = (puzzle[6][4] == 0) ? new Cell() : new Cell(puzzle[6][4] - 1);
        grid[6][5] = (puzzle[6][5] == 0) ? new Cell() : new Cell(puzzle[6][5] - 1);
        grid[6][6] = (puzzle[6][6] == 0) ? new Cell() : new Cell(puzzle[6][6] - 1);
        grid[6][7] = (puzzle[6][7] == 0) ? new Cell() : new Cell(puzzle[6][7] - 1);
        grid[6][8] = (puzzle[6][8] == 0) ? new Cell() : new Cell(puzzle[6][8] - 1);
        
        grid[7][0] = (puzzle[7][0] == 0) ? new Cell() : new Cell(puzzle[7][0] - 1);
        grid[7][1] = (puzzle[7][1] == 0) ? new Cell() : new Cell(puzzle[7][1] - 1);
        grid[7][2] = (puzzle[7][2] == 0) ? new Cell() : new Cell(puzzle[7][2] - 1);
        grid[7][3] = (puzzle[7][3] == 0) ? new Cell() : new Cell(puzzle[7][3] - 1);
        grid[7][4] = (puzzle[7][4] == 0) ? new Cell() : new Cell(puzzle[7][4] - 1);
        grid[7][5] = (puzzle[7][5] == 0) ? new Cell() : new Cell(puzzle[7][5] - 1);
        grid[7][6] = (puzzle[7][6] == 0) ? new Cell() : new Cell(puzzle[7][6] - 1);
        grid[7][7] = (puzzle[7][7] == 0) ? new Cell() : new Cell(puzzle[7][7] - 1);
        grid[7][8] = (puzzle[7][8] == 0) ? new Cell() : new Cell(puzzle[7][8] - 1);
       
        grid[8][0] = (puzzle[8][0] == 0) ? new Cell() : new Cell(puzzle[8][0] - 1);
        grid[8][1] = (puzzle[8][1] == 0) ? new Cell() : new Cell(puzzle[8][1] - 1);
        grid[8][2] = (puzzle[8][2] == 0) ? new Cell() : new Cell(puzzle[8][2] - 1);
        grid[8][3] = (puzzle[8][3] == 0) ? new Cell() : new Cell(puzzle[8][3] - 1);
        grid[8][4] = (puzzle[8][4] == 0) ? new Cell() : new Cell(puzzle[8][4] - 1);
        grid[8][5] = (puzzle[8][5] == 0) ? new Cell() : new Cell(puzzle[8][5] - 1);
        grid[8][6] = (puzzle[8][6] == 0) ? new Cell() : new Cell(puzzle[8][6] - 1);
        grid[8][7] = (puzzle[8][7] == 0) ? new Cell() : new Cell(puzzle[8][7] - 1);
        grid[8][8] = (puzzle[8][8] == 0) ? new Cell() : new Cell(puzzle[8][8] - 1);
        
        
    }
    
    /**
     * Solves the puzzle
     * @see updateCellPossibilities
     * @see updateSingletons
     */
    public void solve()
    {
        //9x9 puzzle -> 81 Cells. numSolved is updated upon the constructuction of a Cell with a value and when processPossibilities sets the number.
        while(Cell.numSolved < 81)
        {
            updateCellPossibilities();
            updateSingletons();
        }
    }
    
    //original
    /*#public int[][] getPuzzle()
    {
        int[][] intPuzzle = new int[9][9];
        for(int i=0; i<9; i+=1)
            for(int j=0; j<9; j+=1)
                intPuzzle[i][j] = grid[i][j].toInt() + 1;
        return intPuzzle;
    }*/
	
	/**
     * Returns the puzzle as an array of integers (translates from Cells)
     */
    public int[][] getPuzzle()
    {
        int[][] intPuzzle = new int[9][9];
        
        intPuzzle[0][0] = grid[0][0].toInt() + 1;
        intPuzzle[0][1] = grid[0][1].toInt() + 1;
        intPuzzle[0][2] = grid[0][2].toInt() + 1;
        intPuzzle[0][3] = grid[0][3].toInt() + 1;
        intPuzzle[0][4] = grid[0][4].toInt() + 1;
        intPuzzle[0][5] = grid[0][5].toInt() + 1;
        intPuzzle[0][6] = grid[0][6].toInt() + 1;
        intPuzzle[0][7] = grid[0][7].toInt() + 1;
        intPuzzle[0][8] = grid[0][8].toInt() + 1;
        
        intPuzzle[1][0] = grid[1][0].toInt() + 1;
        intPuzzle[1][1] = grid[1][1].toInt() + 1;
        intPuzzle[1][2] = grid[1][2].toInt() + 1;
        intPuzzle[1][3] = grid[1][3].toInt() + 1;
        intPuzzle[1][4] = grid[1][4].toInt() + 1;
        intPuzzle[1][5] = grid[1][5].toInt() + 1;
        intPuzzle[1][6] = grid[1][6].toInt() + 1;
        intPuzzle[1][7] = grid[1][7].toInt() + 1;
        intPuzzle[1][8] = grid[1][8].toInt() + 1;
        
        intPuzzle[2][0] = grid[2][0].toInt() + 1;
        intPuzzle[2][1] = grid[2][1].toInt() + 1;
        intPuzzle[2][2] = grid[2][2].toInt() + 1;
        intPuzzle[2][3] = grid[2][3].toInt() + 1;
        intPuzzle[2][4] = grid[2][4].toInt() + 1;
        intPuzzle[2][5] = grid[2][5].toInt() + 1;
        intPuzzle[2][6] = grid[2][6].toInt() + 1;
        intPuzzle[2][7] = grid[2][7].toInt() + 1;
        intPuzzle[2][8] = grid[2][8].toInt() + 1;
       
        intPuzzle[3][0] = grid[3][0].toInt() + 1;
        intPuzzle[3][1] = grid[3][1].toInt() + 1;
        intPuzzle[3][2] = grid[3][2].toInt() + 1;
        intPuzzle[3][3] = grid[3][3].toInt() + 1;
        intPuzzle[3][4] = grid[3][4].toInt() + 1;
        intPuzzle[3][5] = grid[3][5].toInt() + 1;
        intPuzzle[3][6] = grid[3][6].toInt() + 1;
        intPuzzle[3][7] = grid[3][7].toInt() + 1;
        intPuzzle[3][8] = grid[3][8].toInt() + 1;
       
        intPuzzle[4][0] = grid[4][0].toInt() + 1;
        intPuzzle[4][1] = grid[4][1].toInt() + 1;
        intPuzzle[4][2] = grid[4][2].toInt() + 1;
        intPuzzle[4][3] = grid[4][3].toInt() + 1;
        intPuzzle[4][4] = grid[4][4].toInt() + 1;
        intPuzzle[4][5] = grid[4][5].toInt() + 1;
        intPuzzle[4][6] = grid[4][6].toInt() + 1;
        intPuzzle[4][7] = grid[4][7].toInt() + 1;
        intPuzzle[4][8] = grid[4][8].toInt() + 1;
        
        intPuzzle[5][0] = grid[5][0].toInt() + 1;
        intPuzzle[5][1] = grid[5][1].toInt() + 1;
        intPuzzle[5][2] = grid[5][2].toInt() + 1;
        intPuzzle[5][3] = grid[5][3].toInt() + 1;
        intPuzzle[5][4] = grid[5][4].toInt() + 1;
        intPuzzle[5][5] = grid[5][5].toInt() + 1;
        intPuzzle[5][6] = grid[5][6].toInt() + 1;
        intPuzzle[5][7] = grid[5][7].toInt() + 1;
        intPuzzle[5][8] = grid[5][8].toInt() + 1;
        
        intPuzzle[6][0] = grid[6][0].toInt() + 1;
        intPuzzle[6][1] = grid[6][1].toInt() + 1;
        intPuzzle[6][2] = grid[6][2].toInt() + 1;
        intPuzzle[6][3] = grid[6][3].toInt() + 1;
        intPuzzle[6][4] = grid[6][4].toInt() + 1;
        intPuzzle[6][5] = grid[6][5].toInt() + 1;
        intPuzzle[6][6] = grid[6][6].toInt() + 1;
        intPuzzle[6][7] = grid[6][7].toInt() + 1;
        intPuzzle[6][8] = grid[6][8].toInt() + 1;
        
        intPuzzle[7][0] = grid[7][0].toInt() + 1;
        intPuzzle[7][1] = grid[7][1].toInt() + 1;
        intPuzzle[7][2] = grid[7][2].toInt() + 1;
        intPuzzle[7][3] = grid[7][3].toInt() + 1;
        intPuzzle[7][4] = grid[7][4].toInt() + 1;
        intPuzzle[7][5] = grid[7][5].toInt() + 1;
        intPuzzle[7][6] = grid[7][6].toInt() + 1;
        intPuzzle[7][7] = grid[7][7].toInt() + 1;
        intPuzzle[7][8] = grid[7][8].toInt() + 1;
       
        intPuzzle[8][0] = grid[8][0].toInt() + 1;
        intPuzzle[8][1] = grid[8][1].toInt() + 1;
        intPuzzle[8][2] = grid[8][2].toInt() + 1;
        intPuzzle[8][3] = grid[8][3].toInt() + 1;
        intPuzzle[8][4] = grid[8][4].toInt() + 1;
        intPuzzle[8][5] = grid[8][5].toInt() + 1;
        intPuzzle[8][6] = grid[8][6].toInt() + 1;
        intPuzzle[8][7] = grid[8][7].toInt() + 1;
        intPuzzle[8][8] = grid[8][8].toInt() + 1;
        
        return intPuzzle;
    }
    
    //original
    /*#private boolean[] aggregatePossibilities(int row,int col)
    {
        boolean[] x = new boolean[] {true, true, true, true, true, true, true, true, true};
        
        for(int i=0; i<9; i+=1)
        {
            //Traverses row
            if(grid[i][col].isSolved())
                //eliminates solved value
                x[grid[i][col].toInt()] = false;
            //Traverses Column
            if(grid[row][i].isSolved())
                x[grid[row][i].toInt()] = false;
        }
        
        //Traverses squares in the box
        //boxRow: the index of the row of the Cell's upper left corner
        //boxRow: the index of the column of the Cell's upper left corner
        //boxRowOffSet: the index of the row of the Cell's lower right corner
        //boxColOffset: the index of the column of the Cell's lower right corner
        int boxRow = row/3*3, boxCol = col/3*3, boxRowOffset = boxRow + 3, boxColOffset = boxCol + 3;
        
        for(int i=boxRow; i<boxRowOffset; i+=1)
           for(int j=boxCol; j<boxColOffset; j+=1)
               if(grid[i][j].isSolved())
                    x[grid[i][j].toInt()] = false;
                    
        return x;
    }*/
    
    /**
     * Finds all possible values that can go in the Cell specified by the parameters
     * @param row the row index of the Cell
     * @param col the column index of the Cell
     * @postcondition values in the same row, column, or box of the Cell are denoted false in the possibilities array
     * @returns boolean[9] array of remaining possibilities for that Cell
     */
    private boolean[] aggregatePossibilities(int row,int col)
    {
        boolean[] x = new boolean[] {true, true, true, true, true, true, true, true, true};
        
        //0-8 
        
            if(grid[0][col].isSolved())
                x[grid[0][col].toInt()] = false;
        
            if(grid[1][col].isSolved())
                x[grid[1][col].toInt()] = false;
        
            if(grid[2][col].isSolved())
                x[grid[2][col].toInt()] = false;
        
            if(grid[3][col].isSolved())
                x[grid[3][col].toInt()] = false;
        
            if(grid[4][col].isSolved())
                x[grid[4][col].toInt()] = false;
        
            if(grid[5][col].isSolved())
                x[grid[5][col].toInt()] = false;
        
            if(grid[6][col].isSolved())
                x[grid[6][col].toInt()] = false;
        
            if(grid[7][col].isSolved())
                x[grid[7][col].toInt()] = false;
        
            if(grid[8][col].isSolved())
                x[grid[8][col].toInt()] = false;
                
         
            if(grid[row][0].isSolved())
                x[grid[row][0].toInt()] = false;
         
            if(grid[row][1].isSolved())
                x[grid[row][1].toInt()] = false;
         
            if(grid[row][2].isSolved())
                x[grid[row][2].toInt()] = false;
         
            if(grid[row][3].isSolved())
                x[grid[row][3].toInt()] = false;
         
            if(grid[row][4].isSolved())
                x[grid[row][4].toInt()] = false;
         
            if(grid[row][5].isSolved())
                x[grid[row][5].toInt()] = false;
         
            if(grid[row][6].isSolved())
                x[grid[row][6].toInt()] = false;
         
            if(grid[row][7].isSolved())
                x[grid[row][7].toInt()] = false;
         
            if(grid[row][8].isSolved())
                x[grid[row][8].toInt()] = false;
        
        
        //Traverses squares in the box
        //boxRow: the index of the row of the Cell's upper left corner
        //boxRow: the index of the column of the Cell's upper left corner
        //boxRowOffSet: the index of the row of the Cell's lower right corner
        //boxColOffset: the index of the column of the Cell's lower right corner
        int boxRow = row/3*3, boxCol = col/3*3, boxRowOffset = boxRow + 3, boxColOffset = boxCol + 3;
        /*#would be way too difficult to unroll this part, would need to add many if statements and would take more time running, so not worth it*/
        for(int i=boxRow; i<boxRowOffset; i+=1)
           for(int j=boxCol; j<boxColOffset; j+=1)
               if(grid[i][j].isSolved())
                    x[grid[i][j].toInt()] = false;
                    
        return x;
    }
    
    //original
    /*#private void updateCellPossibilities()
    {
        for(int row = 0; row < 9; row+=1)
            for(int col = 0; col < 9; col+=1)   
                if(grid[row][col].isNotSolved())
                {
                    grid[row][col].updatePossibilities(aggregatePossibilities(row,col));
                    //Will set the Cell's value if only one possibility remains
                    grid[row][col].processPossibilityList();
                }
    }*/
	
    /**
     * Traverses puzzle, determining what values can go in each Cell and setting the value when only one is possible
     */
    private void updateCellPossibilities()
    {
          //0
        if(grid[0][0].isNotSolved()){
           grid[0][0].updatePossibilities(aggregatePossibilities(0,0));
           grid[0][0].processPossibilityList();}
          
        if(grid[0][1].isNotSolved()){
           grid[0][1].updatePossibilities(aggregatePossibilities(0,1));
           grid[0][1].processPossibilityList();}
          
        if(grid[0][2].isNotSolved()){
           grid[0][2].updatePossibilities(aggregatePossibilities(0,2));
           grid[0][2].processPossibilityList();}
          
        if(grid[0][3].isNotSolved()){
           grid[0][3].updatePossibilities(aggregatePossibilities(0,3));
           grid[0][3].processPossibilityList();}
          
        if(grid[0][4].isNotSolved()){
           grid[0][4].updatePossibilities(aggregatePossibilities(0,4));
           grid[0][4].processPossibilityList();}
          
        if(grid[0][5].isNotSolved()){
           grid[0][5].updatePossibilities(aggregatePossibilities(0,5));
           grid[0][5].processPossibilityList();}
          
        if(grid[0][6].isNotSolved()){
           grid[0][6].updatePossibilities(aggregatePossibilities(0,6));
           grid[0][6].processPossibilityList();}
          
        if(grid[0][7].isNotSolved()){
           grid[0][7].updatePossibilities(aggregatePossibilities(0,7));
           grid[0][7].processPossibilityList();}
           
        if(grid[0][8].isNotSolved()){
           grid[0][8].updatePossibilities(aggregatePossibilities(0,8));
           grid[0][8].processPossibilityList();}
           
          //1
        if(grid[1][0].isNotSolved()){
           grid[1][0].updatePossibilities(aggregatePossibilities(1,0));
           grid[1][0].processPossibilityList();}
          
        if(grid[1][1].isNotSolved()){
           grid[1][1].updatePossibilities(aggregatePossibilities(1,1));
           grid[1][1].processPossibilityList();}
          
        if(grid[1][2].isNotSolved()){
           grid[1][2].updatePossibilities(aggregatePossibilities(1,2));
           grid[1][2].processPossibilityList();}
          
        if(grid[1][3].isNotSolved()){
           grid[1][3].updatePossibilities(aggregatePossibilities(1,3));
           grid[1][3].processPossibilityList();}
          
        if(grid[1][4].isNotSolved()){
           grid[1][4].updatePossibilities(aggregatePossibilities(1,4));
           grid[1][4].processPossibilityList();}
          
        if(grid[1][5].isNotSolved()){
           grid[1][5].updatePossibilities(aggregatePossibilities(1,5));
           grid[1][5].processPossibilityList();}
          
        if(grid[1][6].isNotSolved()){
           grid[1][6].updatePossibilities(aggregatePossibilities(1,6));
           grid[1][6].processPossibilityList();}
          
        if(grid[1][7].isNotSolved()){
           grid[1][7].updatePossibilities(aggregatePossibilities(1,7));
           grid[1][7].processPossibilityList();}
          
        if(grid[1][8].isNotSolved()){
           grid[1][8].updatePossibilities(aggregatePossibilities(1,8));
           grid[1][8].processPossibilityList();}
           
          //2
        if(grid[2][0].isNotSolved()){
           grid[2][0].updatePossibilities(aggregatePossibilities(2,0));
           grid[2][0].processPossibilityList();}
          
        if(grid[2][1].isNotSolved()){
           grid[2][1].updatePossibilities(aggregatePossibilities(2,1));
           grid[2][1].processPossibilityList();}
          
        if(grid[2][2].isNotSolved()){
           grid[2][2].updatePossibilities(aggregatePossibilities(2,2));
           grid[2][2].processPossibilityList();}
          
        if(grid[2][3].isNotSolved()){
           grid[2][3].updatePossibilities(aggregatePossibilities(2,3));
           grid[2][3].processPossibilityList();}
          
        if(grid[2][4].isNotSolved()){
           grid[2][4].updatePossibilities(aggregatePossibilities(2,4));
           grid[2][4].processPossibilityList();}
          
        if(grid[2][5].isNotSolved()){
           grid[2][5].updatePossibilities(aggregatePossibilities(2,5));
           grid[2][5].processPossibilityList();}
          
        if(grid[2][6].isNotSolved()){
           grid[2][6].updatePossibilities(aggregatePossibilities(2,6));
           grid[2][6].processPossibilityList();}
          
        if(grid[2][7].isNotSolved()){
           grid[2][7].updatePossibilities(aggregatePossibilities(2,7));
           grid[2][7].processPossibilityList();}
          
        if(grid[2][8].isNotSolved()){
           grid[2][8].updatePossibilities(aggregatePossibilities(2,8));
           grid[2][8].processPossibilityList();}
           
          //3
        if(grid[3][0].isNotSolved()){
           grid[3][0].updatePossibilities(aggregatePossibilities(3,0));
           grid[3][0].processPossibilityList();}
          
        if(grid[3][1].isNotSolved()){
           grid[3][1].updatePossibilities(aggregatePossibilities(3,1));
           grid[3][1].processPossibilityList();}
          
        if(grid[3][2].isNotSolved()){
           grid[3][2].updatePossibilities(aggregatePossibilities(3,2));
           grid[3][2].processPossibilityList();}
          
        if(grid[3][3].isNotSolved()){
           grid[3][3].updatePossibilities(aggregatePossibilities(3,3));
           grid[3][3].processPossibilityList();}
          
        if(grid[3][4].isNotSolved()){
           grid[3][4].updatePossibilities(aggregatePossibilities(3,4));
           grid[3][4].processPossibilityList();}
          
        if(grid[3][5].isNotSolved()){
           grid[3][5].updatePossibilities(aggregatePossibilities(3,5));
           grid[3][5].processPossibilityList();}
          
        if(grid[3][6].isNotSolved()){
           grid[3][6].updatePossibilities(aggregatePossibilities(3,6));
           grid[3][6].processPossibilityList();}
          
        if(grid[3][7].isNotSolved()){
           grid[3][7].updatePossibilities(aggregatePossibilities(3,7));
           grid[3][7].processPossibilityList();}
          
        if(grid[3][8].isNotSolved()){
           grid[3][8].updatePossibilities(aggregatePossibilities(3,8));
           grid[3][8].processPossibilityList();}
           
          //4
        if(grid[4][0].isNotSolved()){
           grid[4][0].updatePossibilities(aggregatePossibilities(4,0));
           grid[4][0].processPossibilityList();}
          
        if(grid[4][1].isNotSolved()){
           grid[4][1].updatePossibilities(aggregatePossibilities(4,1));
           grid[4][1].processPossibilityList();}
          
        if(grid[4][2].isNotSolved()){
           grid[4][2].updatePossibilities(aggregatePossibilities(4,2));
           grid[4][2].processPossibilityList();}
          
        if(grid[4][3].isNotSolved()){
           grid[4][3].updatePossibilities(aggregatePossibilities(4,3));
           grid[4][3].processPossibilityList();}
          
        if(grid[4][4].isNotSolved()){
           grid[4][4].updatePossibilities(aggregatePossibilities(4,4));
           grid[4][4].processPossibilityList();}
          
        if(grid[4][5].isNotSolved()){
           grid[4][5].updatePossibilities(aggregatePossibilities(4,5));
           grid[4][5].processPossibilityList();}
          
        if(grid[4][6].isNotSolved()){
           grid[4][6].updatePossibilities(aggregatePossibilities(4,6));
           grid[4][6].processPossibilityList();}
          
        if(grid[4][7].isNotSolved()){
           grid[4][7].updatePossibilities(aggregatePossibilities(4,7));
           grid[4][7].processPossibilityList();}
          
        if(grid[4][8].isNotSolved()){
           grid[4][8].updatePossibilities(aggregatePossibilities(4,8));
           grid[4][8].processPossibilityList();}
           
          //5
        if(grid[5][0].isNotSolved()){
           grid[5][0].updatePossibilities(aggregatePossibilities(5,0));
           grid[5][0].processPossibilityList();}
          
        if(grid[5][1].isNotSolved()){
           grid[5][1].updatePossibilities(aggregatePossibilities(5,1));
           grid[5][1].processPossibilityList();}
          
        if(grid[5][2].isNotSolved()){
           grid[5][2].updatePossibilities(aggregatePossibilities(5,2));
           grid[5][2].processPossibilityList();}
          
        if(grid[5][3].isNotSolved()){
           grid[5][3].updatePossibilities(aggregatePossibilities(5,3));
           grid[5][3].processPossibilityList();}
          
        if(grid[5][4].isNotSolved()){
           grid[5][4].updatePossibilities(aggregatePossibilities(5,4));
           grid[5][4].processPossibilityList();}
          
        if(grid[5][5].isNotSolved()){
           grid[5][5].updatePossibilities(aggregatePossibilities(5,5));
           grid[5][5].processPossibilityList();}
          
        if(grid[5][6].isNotSolved()){
           grid[5][6].updatePossibilities(aggregatePossibilities(5,6));
           grid[5][6].processPossibilityList();}
          
        if(grid[5][7].isNotSolved()){
           grid[5][7].updatePossibilities(aggregatePossibilities(5,7));
           grid[5][7].processPossibilityList();}
          
        if(grid[5][8].isNotSolved()){
           grid[5][8].updatePossibilities(aggregatePossibilities(5,8));
           grid[5][8].processPossibilityList();}
           
          //6
        if(grid[6][0].isNotSolved()){
           grid[6][0].updatePossibilities(aggregatePossibilities(6,0));
           grid[6][0].processPossibilityList();}
          
        if(grid[6][1].isNotSolved()){
           grid[6][1].updatePossibilities(aggregatePossibilities(6,1));
           grid[6][1].processPossibilityList();}
          
        if(grid[6][2].isNotSolved()){
           grid[6][2].updatePossibilities(aggregatePossibilities(6,2));
           grid[6][2].processPossibilityList();}
          
        if(grid[6][3].isNotSolved()){
           grid[6][3].updatePossibilities(aggregatePossibilities(6,3));
           grid[6][3].processPossibilityList();}
          
        if(grid[6][4].isNotSolved()){
           grid[6][4].updatePossibilities(aggregatePossibilities(6,4));
           grid[6][4].processPossibilityList();}
          
        if(grid[6][5].isNotSolved()){
           grid[6][5].updatePossibilities(aggregatePossibilities(6,5));
           grid[6][5].processPossibilityList();}
          
        if(grid[6][6].isNotSolved()){
           grid[6][6].updatePossibilities(aggregatePossibilities(6,6));
           grid[6][6].processPossibilityList();}
          
        if(grid[6][7].isNotSolved()){
           grid[6][7].updatePossibilities(aggregatePossibilities(6,7));
           grid[6][7].processPossibilityList();}
          
        if(grid[6][8].isNotSolved()){
           grid[6][8].updatePossibilities(aggregatePossibilities(6,8));
           grid[6][8].processPossibilityList();}
           
          //7
        if(grid[7][0].isNotSolved()){
           grid[7][0].updatePossibilities(aggregatePossibilities(7,0));
           grid[7][0].processPossibilityList();}
          
        if(grid[7][1].isNotSolved()){
           grid[7][1].updatePossibilities(aggregatePossibilities(7,1));
           grid[7][1].processPossibilityList();}
          
        if(grid[7][2].isNotSolved()){
           grid[7][2].updatePossibilities(aggregatePossibilities(7,2));
           grid[7][2].processPossibilityList();}
          
        if(grid[7][3].isNotSolved()){
           grid[7][3].updatePossibilities(aggregatePossibilities(7,3));
           grid[7][3].processPossibilityList();}
          
        if(grid[7][4].isNotSolved()){
           grid[7][4].updatePossibilities(aggregatePossibilities(7,4));
           grid[7][4].processPossibilityList();}
          
        if(grid[7][5].isNotSolved()){
           grid[7][5].updatePossibilities(aggregatePossibilities(7,5));
           grid[7][5].processPossibilityList();}
          
        if(grid[7][6].isNotSolved()){
           grid[7][6].updatePossibilities(aggregatePossibilities(7,6));
           grid[7][6].processPossibilityList();}
          
        if(grid[7][7].isNotSolved()){
           grid[7][7].updatePossibilities(aggregatePossibilities(7,7));
           grid[7][7].processPossibilityList();}
          
        if(grid[7][8].isNotSolved()){
           grid[7][8].updatePossibilities(aggregatePossibilities(7,8));
           grid[7][8].processPossibilityList();}
           
          //8
        if(grid[8][0].isNotSolved()){
           grid[8][0].updatePossibilities(aggregatePossibilities(8,0));
           grid[8][0].processPossibilityList();}
          
        if(grid[8][1].isNotSolved()){
           grid[8][1].updatePossibilities(aggregatePossibilities(8,1));
           grid[8][1].processPossibilityList();}
          
        if(grid[8][2].isNotSolved()){
           grid[8][2].updatePossibilities(aggregatePossibilities(8,2));
           grid[8][2].processPossibilityList();}
          
        if(grid[8][3].isNotSolved()){
           grid[8][3].updatePossibilities(aggregatePossibilities(8,3));
           grid[8][3].processPossibilityList();}
          
        if(grid[8][4].isNotSolved()){
           grid[8][4].updatePossibilities(aggregatePossibilities(8,4));
           grid[8][4].processPossibilityList();}
          
        if(grid[8][5].isNotSolved()){
           grid[8][5].updatePossibilities(aggregatePossibilities(8,5));
           grid[8][5].processPossibilityList();}
          
        if(grid[8][6].isNotSolved()){
           grid[8][6].updatePossibilities(aggregatePossibilities(8,6));
           grid[8][6].processPossibilityList();}
          
        if(grid[8][7].isNotSolved()){
           grid[8][7].updatePossibilities(aggregatePossibilities(8,7));
           grid[8][7].processPossibilityList();}
          
        if(grid[8][8].isNotSolved()){
           grid[8][8].updatePossibilities(aggregatePossibilities(8,8));
           grid[8][8].processPossibilityList();}
    }
    
    //original
    /*#private void updateSingletons()
    {
        for(int numToCheck=0; numToCheck<9; numToCheck+=1)
        {
            fillVerticalSingletons(numToCheck);
            fillHorizontalSingletons(numToCheck);  
        }
        
        //passes the upper left hand corners of each box to fillBoxSingletons
        for(int row = 0; row<9; row += 3)
            for(int col=0; col<9; col+=3)
                fillBoxSingletons(row, col);
    }*/
	
	/**
     * If there is only one place in a row, column, or box that a number can go, that number is assigned there.
     */
    private void updateSingletons()
    {   
        fillVerticalSingletons(0);
        fillHorizontalSingletons(0);
        
        fillVerticalSingletons(1);
        fillHorizontalSingletons(1);
        
        fillVerticalSingletons(2);
        fillHorizontalSingletons(2);
        
        fillVerticalSingletons(3);
        fillHorizontalSingletons(3);
        
        fillVerticalSingletons(4);
        fillHorizontalSingletons(4);
        
        fillVerticalSingletons(5);
        fillHorizontalSingletons(5);
        
        fillVerticalSingletons(6);
        fillHorizontalSingletons(6);
        
        fillVerticalSingletons(7);
        fillHorizontalSingletons(7);
        
        fillVerticalSingletons(8);
        fillHorizontalSingletons(8);  
        
        fillBoxSingletons(0,0);
        fillBoxSingletons(0,3);
        fillBoxSingletons(0,6);
        fillBoxSingletons(3,0);
        fillBoxSingletons(3,3);
        fillBoxSingletons(3,6);
        fillBoxSingletons(6,0);
        fillBoxSingletons(6,3);
        fillBoxSingletons(6,6);
    }
    
    //original
    /*#private void fillBoxSingletons(int row, int col)
    {
        //index: potential number, value = number of places it can go
        int[] possibleNums = new int[9];        
        int rowOffset = row + 3;
        int colOffset = col + 3;
        
            //traverses grid
            for(int i=row; i<rowOffset; i+=1)
                for(int j=col; j<colOffset; j+=1)
                    if(grid[i][j].isNotSolved())
                    {
                        grid[i][j].updatePossibilities(aggregatePossibilities(i,j));
                        //traverses possible values
                        for(int numToCheck=0; numToCheck<9; numToCheck+=1)
                            //if the value of that Cell has not been determined and numToCheck can go there
                            if(grid[i][j].isNotSolved() && grid[i][j].getPossibility(numToCheck))
                                possibleNums[numToCheck]+=1; 
                    }
        
        for(int i=0; i<9; i+=1)
            //if there is only one Cell where that number can go in the box
            if(possibleNums[i] == 1)
                //finds that Cell
                for(int j=row; j<rowOffset; j+=1)
                    for(int k=col; k<colOffset; k+=1)
                        if(grid[j][k].isNotSolved() && grid[j][k].getPossibility(i))
                            //sets that Cell
                            grid[j][k] = new Cell(i);
    }*/
	
    /**
     * If there is only one place in the box that a number can go, assigns that number there
     * @param row the row index of the upper left corner of the box
     * @param col the column index of the upper left corner of the box
     */
    private void fillBoxSingletons(int row, int col)
    {
        //index: potential number, value = number of places it can go
        int[] possibleNums = new int[9];        
        int rowOffset = row + 3;
        int colOffset = col + 3;
        
        /*# This loop also cannot easily be unrolled due to the dynamic nature of the loop termination value, so we left it alone*/
        
        //traverses grid
        for(int i=row; i<rowOffset; i+=1)
            for(int j=col; j<colOffset; j+=1)
                if(grid[i][j].isNotSolved())
                {
                    grid[i][j].updatePossibilities(aggregatePossibilities(i,j));
                    //traverses possible values
                    for(int numToCheck=0; numToCheck<9; numToCheck+=1)
                        //if the value of that Cell has not been determined and numToCheck can go there
                        if(grid[i][j].isNotSolved() && grid[i][j].getPossibility(numToCheck))
                            possibleNums[numToCheck]+=1; 
                }
                
                

        if(possibleNums[0] == 1)
            for(int j=row; j<rowOffset; j+=1)
                for(int k=col; k<colOffset; k+=1)
                    if(grid[j][k].isNotSolved() && grid[j][k].getPossibility(0))
                        grid[j][k] = new Cell(0);

        if(possibleNums[1] == 1)
            for(int j=row; j<rowOffset; j+=1)
                for(int k=col; k<colOffset; k+=1)
                    if(grid[j][k].isNotSolved() && grid[j][k].getPossibility(1))
                        grid[j][k] = new Cell(1);

        if(possibleNums[2] == 1)
            for(int j=row; j<rowOffset; j+=1)
                for(int k=col; k<colOffset; k+=1)
                    if(grid[j][k].isNotSolved() && grid[j][k].getPossibility(2))
                        grid[j][k] = new Cell(2);

        if(possibleNums[3] == 1)
            for(int j=row; j<rowOffset; j+=1)
                for(int k=col; k<colOffset; k+=1)
                    if(grid[j][k].isNotSolved() && grid[j][k].getPossibility(3))
                        grid[j][k] = new Cell(3);

        if(possibleNums[4] == 1)
            for(int j=row; j<rowOffset; j+=1)
                for(int k=col; k<colOffset; k+=1)
                    if(grid[j][k].isNotSolved() && grid[j][k].getPossibility(4))
                        grid[j][k] = new Cell(4);

        if(possibleNums[5] == 1)
            for(int j=row; j<rowOffset; j+=1)
                for(int k=col; k<colOffset; k+=1)
                    if(grid[j][k].isNotSolved() && grid[j][k].getPossibility(5))
                        grid[j][k] = new Cell(5);

        if(possibleNums[6] == 1)
            for(int j=row; j<rowOffset; j+=1)
                for(int k=col; k<colOffset; k+=1)
                    if(grid[j][k].isNotSolved() && grid[j][k].getPossibility(6))
                        grid[j][k] = new Cell(6);

        if(possibleNums[7] == 1)
            for(int j=row; j<rowOffset; j+=1)
                for(int k=col; k<colOffset; k+=1)
                    if(grid[j][k].isNotSolved()&& grid[j][k].getPossibility(7))
                        grid[j][k] = new Cell(7);

        if(possibleNums[8] == 1)
            for(int j=row; j<rowOffset; j+=1)
                for(int k=col; k<colOffset; k+=1)
                    if(grid[j][k].isNotSolved() && grid[j][k].getPossibility(8))
                        grid[j][k] = new Cell(8);
    }

    //original
    /*#private void fillVerticalSingletons(int col)
    {
        //possibleNums[0] holds the number of places a 1 can go in the column (as all values are index + 1), etc.
        int[] possibleNums = new int[9];        
        
        //checks all possible values
        for(int numToCheck=0; numToCheck<9; numToCheck+=1)
            //traverses column
            for(int row=0; row<9; row+=1)
                if(grid[row][col].isNotSolved())
                {
                    grid[row][col].updatePossibilities(aggregatePossibilities(row,col));
                    grid[row][col].processPossibilityList();
                    if(grid[row][col].isNotSolved() && grid[row][col].getPossibility(numToCheck))
                        possibleNums[numToCheck]+=1;
                }
        
        //assigns singletons
        for(int numToCheck=0; numToCheck<9; numToCheck+=1)
            //if there is only one Cell in which that number can go
            if(possibleNums[numToCheck] == 1)
                //finds that Cell
                for(int row=0; row<9; row+=1)
                    if(grid[row][col].isNotSolved() && grid[row][col].getPossibility(numToCheck))
                        grid[row][col] = new Cell(numToCheck);
    }*/
	
    /**
     * If there is only one place in the column that a number can go, assigns that number there
     * @param col the column's index
     */
    private void fillVerticalSingletons(int col)
    {
        //possibleNums[0] holds the number of places a 1 can go in the column (as all values are index + 1), etc.
        int[] possibleNums = new int[9];        
        
           //numToCheck is outside, row is inside
        //0
        if(grid[0][col].isNotSolved()){
            grid[0][col].updatePossibilities(aggregatePossibilities(0,col));
            grid[0][col].processPossibilityList();
            if(grid[0][col].isNotSolved() && grid[0][col].getPossibility(0))
                possibleNums[0]+=1;}
                
        if(grid[1][col].isNotSolved()){
            grid[1][col].updatePossibilities(aggregatePossibilities(1,col));
            grid[1][col].processPossibilityList();
            if(grid[1][col].isNotSolved() && grid[1][col].getPossibility(0))
                possibleNums[0]+=1;}
                
        if(grid[2][col].isNotSolved()){
            grid[2][col].updatePossibilities(aggregatePossibilities(2,col));
            grid[2][col].processPossibilityList();
            if(grid[2][col].isNotSolved() && grid[2][col].getPossibility(0))
                possibleNums[0]+=1;}
                
        if(grid[3][col].isNotSolved()){
            grid[3][col].updatePossibilities(aggregatePossibilities(3,col));
            grid[3][col].processPossibilityList();
            if(grid[3][col].isNotSolved() && grid[3][col].getPossibility(0))
                possibleNums[0]+=1;}
                
        if(grid[4][col].isNotSolved()){
            grid[4][col].updatePossibilities(aggregatePossibilities(4,col));
            grid[4][col].processPossibilityList();
            if(grid[4][col].isNotSolved() && grid[4][col].getPossibility(0))
                possibleNums[0]+=1;}
                
        if(grid[5][col].isNotSolved()){
            grid[5][col].updatePossibilities(aggregatePossibilities(5,col));
            grid[5][col].processPossibilityList();
            if(grid[5][col].isNotSolved() && grid[5][col].getPossibility(0))
                possibleNums[0]+=1;}
                
        if(grid[6][col].isNotSolved()){
            grid[6][col].updatePossibilities(aggregatePossibilities(6,col));
            grid[6][col].processPossibilityList();
            if(grid[6][col].isNotSolved() && grid[6][col].getPossibility(0))
                possibleNums[0]+=1;}
                
        if(grid[7][col].isNotSolved()){
            grid[7][col].updatePossibilities(aggregatePossibilities(7,col));
            grid[7][col].processPossibilityList();
            if(grid[7][col].isNotSolved() && grid[7][col].getPossibility(0))
                possibleNums[0]+=1;}
                
        if(grid[8][col].isNotSolved()){
            grid[8][col].updatePossibilities(aggregatePossibilities(8,col));
            grid[8][col].processPossibilityList();
            if(grid[8][col].isNotSolved() && grid[8][col].getPossibility(0))
                possibleNums[0]+=1;}
        //1
                
        if(grid[0][col].isNotSolved()){
            grid[0][col].updatePossibilities(aggregatePossibilities(0,col));
            grid[0][col].processPossibilityList();
            if(grid[0][col].isNotSolved() && grid[0][col].getPossibility(1))
                possibleNums[1]+=1;}
                
        if(grid[1][col].isNotSolved()){
            grid[1][col].updatePossibilities(aggregatePossibilities(1,col));
            grid[1][col].processPossibilityList();
            if(grid[1][col].isNotSolved() && grid[1][col].getPossibility(1))
                possibleNums[1]+=1;}
                
        if(grid[2][col].isNotSolved()){
            grid[2][col].updatePossibilities(aggregatePossibilities(2,col));
            grid[2][col].processPossibilityList();
            if(grid[2][col].isNotSolved() && grid[2][col].getPossibility(1))
                possibleNums[1]+=1;}
                
        if(grid[3][col].isNotSolved()){
            grid[3][col].updatePossibilities(aggregatePossibilities(3,col));
            grid[3][col].processPossibilityList();
            if(grid[3][col].isNotSolved() && grid[3][col].getPossibility(1))
                possibleNums[1]+=1;}
                
        if(grid[4][col].isNotSolved()){
            grid[4][col].updatePossibilities(aggregatePossibilities(4,col));
            grid[4][col].processPossibilityList();
            if(grid[4][col].isNotSolved() && grid[4][col].getPossibility(1))
                possibleNums[1]+=1;}
                
        if(grid[5][col].isNotSolved()){
            grid[5][col].updatePossibilities(aggregatePossibilities(5,col));
            grid[5][col].processPossibilityList();
            if(grid[5][col].isNotSolved() && grid[5][col].getPossibility(1))
                possibleNums[1]+=1;}
                
        if(grid[6][col].isNotSolved()){
            grid[6][col].updatePossibilities(aggregatePossibilities(6,col));
            grid[6][col].processPossibilityList();
            if(grid[6][col].isNotSolved() && grid[6][col].getPossibility(1))
                possibleNums[1]+=1;}
                
        if(grid[7][col].isNotSolved()){
            grid[7][col].updatePossibilities(aggregatePossibilities(7,col));
            grid[7][col].processPossibilityList();
            if(grid[7][col].isNotSolved() && grid[7][col].getPossibility(1))
                possibleNums[1]+=1;}
                
        if(grid[8][col].isNotSolved()){
            grid[8][col].updatePossibilities(aggregatePossibilities(8,col));
            grid[8][col].processPossibilityList();
            if(grid[8][col].isNotSolved() && grid[8][col].getPossibility(1))
                possibleNums[1]+=1;}
                //2
                
        if(grid[0][col].isNotSolved()){
            grid[0][col].updatePossibilities(aggregatePossibilities(0,col));
            grid[0][col].processPossibilityList();
            if(grid[0][col].isNotSolved() && grid[0][col].getPossibility(2))
                possibleNums[2]+=1;}
                
        if(grid[1][col].isNotSolved()){
            grid[1][col].updatePossibilities(aggregatePossibilities(1,col));
            grid[1][col].processPossibilityList();
            if(grid[1][col].isNotSolved() && grid[1][col].getPossibility(2))
                possibleNums[2]+=1;}
                
        if(grid[2][col].isNotSolved()){
            grid[2][col].updatePossibilities(aggregatePossibilities(2,col));
            grid[2][col].processPossibilityList();
            if(grid[2][col].isNotSolved() && grid[2][col].getPossibility(2))
                possibleNums[2]+=1;}
                
        if(grid[3][col].isNotSolved()){
            grid[3][col].updatePossibilities(aggregatePossibilities(3,col));
            grid[3][col].processPossibilityList();
            if(grid[3][col].isNotSolved() && grid[3][col].getPossibility(2))
                possibleNums[2]+=1;}
                
        if(grid[4][col].isNotSolved()){
            grid[4][col].updatePossibilities(aggregatePossibilities(4,col));
            grid[4][col].processPossibilityList();
            if(grid[4][col].isNotSolved() && grid[4][col].getPossibility(2))
                possibleNums[2]+=1;}
                
        if(grid[5][col].isNotSolved()){
            grid[5][col].updatePossibilities(aggregatePossibilities(5,col));
            grid[5][col].processPossibilityList();
            if(grid[5][col].isNotSolved() && grid[5][col].getPossibility(2))
                possibleNums[2]+=1;}
                
        if(grid[6][col].isNotSolved()){
            grid[6][col].updatePossibilities(aggregatePossibilities(6,col));
            grid[6][col].processPossibilityList();
            if(grid[6][col].isNotSolved() && grid[6][col].getPossibility(2))
                possibleNums[2]+=1;}
                
        if(grid[7][col].isNotSolved()){
            grid[7][col].updatePossibilities(aggregatePossibilities(7,col));
            grid[7][col].processPossibilityList();
            if(grid[7][col].isNotSolved() && grid[7][col].getPossibility(2))
                possibleNums[2]+=1;}
                
        if(grid[8][col].isNotSolved()){
            grid[8][col].updatePossibilities(aggregatePossibilities(8,col));
            grid[8][col].processPossibilityList();
            if(grid[8][col].isNotSolved() && grid[8][col].getPossibility(2))
                possibleNums[2]+=1;}
                //3
                
        if(grid[0][col].isNotSolved()){
            grid[0][col].updatePossibilities(aggregatePossibilities(0,col));
            grid[0][col].processPossibilityList();
            if(grid[0][col].isNotSolved() && grid[0][col].getPossibility(3))
                possibleNums[3]+=1;}
                
        if(grid[1][col].isNotSolved()){
            grid[1][col].updatePossibilities(aggregatePossibilities(1,col));
            grid[1][col].processPossibilityList();
            if(grid[1][col].isNotSolved() && grid[1][col].getPossibility(3))
                possibleNums[3]+=1;}
                
        if(grid[2][col].isNotSolved()){
            grid[2][col].updatePossibilities(aggregatePossibilities(2,col));
            grid[2][col].processPossibilityList();
            if(grid[2][col].isNotSolved() && grid[2][col].getPossibility(3))
                possibleNums[3]+=1;}
                
        if(grid[3][col].isNotSolved()){
            grid[3][col].updatePossibilities(aggregatePossibilities(3,col));
            grid[3][col].processPossibilityList();
            if(grid[3][col].isNotSolved() && grid[3][col].getPossibility(3))
                possibleNums[3]+=1;}
                
        if(grid[4][col].isNotSolved()){
            grid[4][col].updatePossibilities(aggregatePossibilities(4,col));
            grid[4][col].processPossibilityList();
            if(grid[4][col].isNotSolved() && grid[4][col].getPossibility(3))
                possibleNums[3]+=1;}
                
        if(grid[5][col].isNotSolved()){
            grid[5][col].updatePossibilities(aggregatePossibilities(5,col));
            grid[5][col].processPossibilityList();
            if(grid[5][col].isNotSolved() && grid[5][col].getPossibility(3))
                possibleNums[3]+=1;}
                
        if(grid[6][col].isNotSolved()){
            grid[6][col].updatePossibilities(aggregatePossibilities(6,col));
            grid[6][col].processPossibilityList();
            if(grid[6][col].isNotSolved() && grid[6][col].getPossibility(3))
                possibleNums[3]+=1;}
                
        if(grid[7][col].isNotSolved()){
            grid[7][col].updatePossibilities(aggregatePossibilities(7,col));
            grid[7][col].processPossibilityList();
            if(grid[7][col].isNotSolved() && grid[7][col].getPossibility(3))
                possibleNums[3]+=1;}
                
        if(grid[8][col].isNotSolved()){
            grid[8][col].updatePossibilities(aggregatePossibilities(8,col));
            grid[8][col].processPossibilityList();
            if(grid[8][col].isNotSolved() && grid[8][col].getPossibility(3))
                possibleNums[3]+=1;}
                //4
                
        if(grid[0][col].isNotSolved()){
            grid[0][col].updatePossibilities(aggregatePossibilities(0,col));
            grid[0][col].processPossibilityList();
            if(grid[0][col].isNotSolved() && grid[0][col].getPossibility(4))
                possibleNums[4]+=1;}
                
        if(grid[1][col].isNotSolved()){
            grid[1][col].updatePossibilities(aggregatePossibilities(1,col));
            grid[1][col].processPossibilityList();
            if(grid[1][col].isNotSolved() && grid[1][col].getPossibility(4))
                possibleNums[4]+=1;}
                
        if(grid[2][col].isNotSolved()){
            grid[2][col].updatePossibilities(aggregatePossibilities(2,col));
            grid[2][col].processPossibilityList();
            if(grid[2][col].isNotSolved() && grid[2][col].getPossibility(4))
                possibleNums[4]+=1;}
                
        if(grid[3][col].isNotSolved()){
            grid[3][col].updatePossibilities(aggregatePossibilities(3,col));
            grid[3][col].processPossibilityList();
            if(grid[3][col].isNotSolved() && grid[3][col].getPossibility(4))
                possibleNums[4]+=1;}
                
        if(grid[4][col].isNotSolved()){
            grid[4][col].updatePossibilities(aggregatePossibilities(4,col));
            grid[4][col].processPossibilityList();
            if(grid[4][col].isNotSolved() && grid[4][col].getPossibility(4))
                possibleNums[4]+=1;}
                
        if(grid[5][col].isNotSolved()){
            grid[5][col].updatePossibilities(aggregatePossibilities(5,col));
            grid[5][col].processPossibilityList();
            if(grid[5][col].isNotSolved() && grid[5][col].getPossibility(4))
                possibleNums[4]+=1;}
                
        if(grid[6][col].isNotSolved()){
            grid[6][col].updatePossibilities(aggregatePossibilities(6,col));
            grid[6][col].processPossibilityList();
            if(grid[6][col].isNotSolved() && grid[6][col].getPossibility(4))
                possibleNums[4]+=1;}
                
        if(grid[7][col].isNotSolved()){
            grid[7][col].updatePossibilities(aggregatePossibilities(7,col));
            grid[7][col].processPossibilityList();
            if(grid[7][col].isNotSolved() && grid[7][col].getPossibility(4))
                possibleNums[4]+=1;}
                
        if(grid[8][col].isNotSolved()){
            grid[8][col].updatePossibilities(aggregatePossibilities(8,col));
            grid[8][col].processPossibilityList();
            if(grid[8][col].isNotSolved() && grid[8][col].getPossibility(4))
                possibleNums[4]+=1;}
                //5
                
        if(grid[0][col].isNotSolved()){
            grid[0][col].updatePossibilities(aggregatePossibilities(0,col));
            grid[0][col].processPossibilityList();
            if(grid[0][col].isNotSolved() && grid[0][col].getPossibility(5))
                possibleNums[5]+=1;}
                
        if(grid[1][col].isNotSolved()){
            grid[1][col].updatePossibilities(aggregatePossibilities(1,col));
            grid[1][col].processPossibilityList();
            if(grid[1][col].isNotSolved() && grid[1][col].getPossibility(5))
                possibleNums[5]+=1;}
                
        if(grid[2][col].isNotSolved()){
            grid[2][col].updatePossibilities(aggregatePossibilities(2,col));
            grid[2][col].processPossibilityList();
            if(grid[2][col].isNotSolved() && grid[2][col].getPossibility(5))
                possibleNums[5]+=1;}
                
        if(grid[3][col].isNotSolved()){
            grid[3][col].updatePossibilities(aggregatePossibilities(3,col));
            grid[3][col].processPossibilityList();
            if(grid[3][col].isNotSolved() && grid[3][col].getPossibility(5))
                possibleNums[5]+=1;}
                
        if(grid[4][col].isNotSolved()){
            grid[4][col].updatePossibilities(aggregatePossibilities(4,col));
            grid[4][col].processPossibilityList();
            if(grid[4][col].isNotSolved() && grid[4][col].getPossibility(5))
                possibleNums[5]+=1;}
                
        if(grid[5][col].isNotSolved()){
            grid[5][col].updatePossibilities(aggregatePossibilities(5,col));
            grid[5][col].processPossibilityList();
            if(grid[5][col].isNotSolved() && grid[5][col].getPossibility(5))
                possibleNums[5]+=1;}
                
        if(grid[6][col].isNotSolved()){
            grid[6][col].updatePossibilities(aggregatePossibilities(6,col));
            grid[6][col].processPossibilityList();
            if(grid[6][col].isNotSolved() && grid[6][col].getPossibility(5))
                possibleNums[5]+=1;}
                
        if(grid[7][col].isNotSolved()){
            grid[7][col].updatePossibilities(aggregatePossibilities(7,col));
            grid[7][col].processPossibilityList();
            if(grid[7][col].isNotSolved() && grid[7][col].getPossibility(5))
                possibleNums[5]+=1;}
                
        if(grid[8][col].isNotSolved()){
            grid[8][col].updatePossibilities(aggregatePossibilities(8,col));
            grid[8][col].processPossibilityList();
            if(grid[8][col].isNotSolved() && grid[8][col].getPossibility(5))
                possibleNums[5]+=1;}
                //6
                
        if(grid[0][col].isNotSolved()){
            grid[0][col].updatePossibilities(aggregatePossibilities(0,col));
            grid[0][col].processPossibilityList();
            if(grid[0][col].isNotSolved() && grid[0][col].getPossibility(6))
                possibleNums[6]+=1;}
                
        if(grid[1][col].isNotSolved()){
            grid[1][col].updatePossibilities(aggregatePossibilities(1,col));
            grid[1][col].processPossibilityList();
            if(grid[1][col].isNotSolved() && grid[1][col].getPossibility(6))
                possibleNums[6]+=1;}
                
        if(grid[2][col].isNotSolved()){
            grid[2][col].updatePossibilities(aggregatePossibilities(2,col));
            grid[2][col].processPossibilityList();
            if(grid[2][col].isNotSolved() && grid[2][col].getPossibility(6))
                possibleNums[6]+=1;}
                
        if(grid[3][col].isNotSolved()){
            grid[3][col].updatePossibilities(aggregatePossibilities(3,col));
            grid[3][col].processPossibilityList();
            if(grid[3][col].isNotSolved() && grid[3][col].getPossibility(6))
                possibleNums[6]+=1;}
                
        if(grid[4][col].isNotSolved()){
            grid[4][col].updatePossibilities(aggregatePossibilities(4,col));
            grid[4][col].processPossibilityList();
            if(grid[4][col].isNotSolved() && grid[4][col].getPossibility(6))
                possibleNums[6]+=1;}
                
        if(grid[5][col].isNotSolved()){
            grid[5][col].updatePossibilities(aggregatePossibilities(5,col));
            grid[5][col].processPossibilityList();
            if(grid[5][col].isNotSolved() && grid[5][col].getPossibility(6))
                possibleNums[6]+=1;}
                
        if(grid[6][col].isNotSolved()){
            grid[6][col].updatePossibilities(aggregatePossibilities(6,col));
            grid[6][col].processPossibilityList();
            if(grid[6][col].isNotSolved() && grid[6][col].getPossibility(6))
                possibleNums[6]+=1;}
                
        if(grid[7][col].isNotSolved()){
            grid[7][col].updatePossibilities(aggregatePossibilities(7,col));
            grid[7][col].processPossibilityList();
            if(grid[7][col].isNotSolved() && grid[7][col].getPossibility(6))
                possibleNums[6]+=1;}
                
        if(grid[8][col].isNotSolved()){
            grid[8][col].updatePossibilities(aggregatePossibilities(8,col));
            grid[8][col].processPossibilityList();
            if(grid[8][col].isNotSolved() && grid[8][col].getPossibility(6))
                possibleNums[6]+=1;}
                //7
                
        if(grid[0][col].isNotSolved()){
            grid[0][col].updatePossibilities(aggregatePossibilities(0,col));
            grid[0][col].processPossibilityList();
            if(grid[0][col].isNotSolved() && grid[0][col].getPossibility(7))
                possibleNums[7]+=1;}
                
        if(grid[1][col].isNotSolved()){
            grid[1][col].updatePossibilities(aggregatePossibilities(1,col));
            grid[1][col].processPossibilityList();
            if(grid[1][col].isNotSolved() && grid[1][col].getPossibility(7))
                possibleNums[7]+=1;}
                
        if(grid[2][col].isNotSolved()){
            grid[2][col].updatePossibilities(aggregatePossibilities(2,col));
            grid[2][col].processPossibilityList();
            if(grid[2][col].isNotSolved() && grid[2][col].getPossibility(7))
                possibleNums[7]+=1;}
                
        if(grid[3][col].isNotSolved()){
            grid[3][col].updatePossibilities(aggregatePossibilities(3,col));
            grid[3][col].processPossibilityList();
            if(grid[3][col].isNotSolved() && grid[3][col].getPossibility(7))
                possibleNums[7]+=1;}
                
        if(grid[4][col].isNotSolved()){
            grid[4][col].updatePossibilities(aggregatePossibilities(4,col));
            grid[4][col].processPossibilityList();
            if(grid[4][col].isNotSolved() && grid[4][col].getPossibility(7))
                possibleNums[7]+=1;}
                
        if(grid[5][col].isNotSolved()){
            grid[5][col].updatePossibilities(aggregatePossibilities(5,col));
            grid[5][col].processPossibilityList();
            if(grid[5][col].isNotSolved() && grid[5][col].getPossibility(7))
                possibleNums[7]+=1;}
                
        if(grid[6][col].isNotSolved()){
            grid[6][col].updatePossibilities(aggregatePossibilities(6,col));
            grid[6][col].processPossibilityList();
            if(grid[6][col].isNotSolved() && grid[6][col].getPossibility(7))
                possibleNums[7]+=1;}
                
        if(grid[7][col].isNotSolved()){
            grid[7][col].updatePossibilities(aggregatePossibilities(7,col));
            grid[7][col].processPossibilityList();
            if(grid[7][col].isNotSolved() && grid[7][col].getPossibility(7))
                possibleNums[7]+=1;}
                
        if(grid[8][col].isNotSolved()){
            grid[8][col].updatePossibilities(aggregatePossibilities(8,col));
            grid[8][col].processPossibilityList();
            if(grid[8][col].isNotSolved() && grid[8][col].getPossibility(7))
                possibleNums[7]+=1;}
                //8
                
        if(grid[0][col].isNotSolved()){
            grid[0][col].updatePossibilities(aggregatePossibilities(0,col));
            grid[0][col].processPossibilityList();
            if(grid[0][col].isNotSolved() && grid[0][col].getPossibility(8))
                possibleNums[8]+=1;}
                
        if(grid[1][col].isNotSolved()){
            grid[1][col].updatePossibilities(aggregatePossibilities(1,col));
            grid[1][col].processPossibilityList();
            if(grid[1][col].isNotSolved() && grid[1][col].getPossibility(8))
                possibleNums[8]+=1;}
                
        if(grid[2][col].isNotSolved()){
            grid[2][col].updatePossibilities(aggregatePossibilities(2,col));
            grid[2][col].processPossibilityList();
            if(grid[2][col].isNotSolved() && grid[2][col].getPossibility(8))
                possibleNums[8]+=1;}
                
        if(grid[3][col].isNotSolved()){
            grid[3][col].updatePossibilities(aggregatePossibilities(3,col));
            grid[3][col].processPossibilityList();
            if(grid[3][col].isNotSolved() && grid[3][col].getPossibility(8))
                possibleNums[8]+=1;}
                
        if(grid[4][col].isNotSolved()){
            grid[4][col].updatePossibilities(aggregatePossibilities(4,col));
            grid[4][col].processPossibilityList();
            if(grid[4][col].isNotSolved() && grid[4][col].getPossibility(8))
                possibleNums[8]+=1;}
                
        if(grid[5][col].isNotSolved()){
            grid[5][col].updatePossibilities(aggregatePossibilities(5,col));
            grid[5][col].processPossibilityList();
            if(grid[5][col].isNotSolved() && grid[5][col].getPossibility(8))
                possibleNums[8]+=1;}
                
        if(grid[6][col].isNotSolved()){
            grid[6][col].updatePossibilities(aggregatePossibilities(6,col));
            grid[6][col].processPossibilityList();
            if(grid[6][col].isNotSolved() && grid[6][col].getPossibility(8))
                possibleNums[8]+=1;}
                
        if(grid[7][col].isNotSolved()){
            grid[7][col].updatePossibilities(aggregatePossibilities(7,col));
            grid[7][col].processPossibilityList();
            if(grid[7][col].isNotSolved() && grid[7][col].getPossibility(8))
                possibleNums[8]+=1;}
                
        if(grid[8][col].isNotSolved()){
            grid[8][col].updatePossibilities(aggregatePossibilities(8,col));
            grid[8][col].processPossibilityList();
            if(grid[8][col].isNotSolved() && grid[8][col].getPossibility(8))
                possibleNums[8]+=1;}
        
                        
                        

        if(possibleNums[0] == 1){
            if(grid[0][col].isNotSolved() && grid[0][col].getPossibility(0))
                grid[0][col] = new Cell(0);
           
            if(grid[1][col].isNotSolved() && grid[1][col].getPossibility(0))
                grid[1][col] = new Cell(0);
           
            if(grid[2][col].isNotSolved() && grid[2][col].getPossibility(0))
                grid[2][col] = new Cell(0);
           
            if(grid[3][col].isNotSolved() && grid[3][col].getPossibility(0))
                grid[3][col] = new Cell(0);
           
            if(grid[4][col].isNotSolved() && grid[4][col].getPossibility(0))
                grid[4][col] = new Cell(0);
           
            if(grid[5][col].isNotSolved() && grid[5][col].getPossibility(0))
                grid[5][col] = new Cell(0);
           
            if(grid[6][col].isNotSolved() && grid[6][col].getPossibility(0))
                grid[6][col] = new Cell(0);
           
            if(grid[7][col].isNotSolved() && grid[7][col].getPossibility(0))
                grid[7][col] = new Cell(0);
           
            if(grid[8][col].isNotSolved() && grid[8][col].getPossibility(0))
                grid[8][col] = new Cell(0);
        }

        if(possibleNums[1] == 1){
            if(grid[0][col].isNotSolved() && grid[0][col].getPossibility(1))
                grid[0][col] = new Cell(1);
           
            if(grid[1][col].isNotSolved() && grid[1][col].getPossibility(1))
                grid[1][col] = new Cell(1);
           
            if(grid[2][col].isNotSolved() && grid[2][col].getPossibility(1))
                grid[2][col] = new Cell(1);
           
            if(grid[3][col].isNotSolved() && grid[3][col].getPossibility(1))
                grid[3][col] = new Cell(1);
           
            if(grid[4][col].isNotSolved() && grid[4][col].getPossibility(1))
                grid[4][col] = new Cell(1);
           
            if(grid[5][col].isNotSolved() && grid[5][col].getPossibility(1))
                grid[5][col] = new Cell(1);
           
            if(grid[6][col].isNotSolved() && grid[6][col].getPossibility(1))
                grid[6][col] = new Cell(1);
           
            if(grid[7][col].isNotSolved() && grid[7][col].getPossibility(1))
                grid[7][col] = new Cell(1);
           
            if(grid[8][col].isNotSolved() && grid[8][col].getPossibility(1))
                grid[8][col] = new Cell(1);
        }

        if(possibleNums[2] == 1){
            if(grid[0][col].isNotSolved() && grid[0][col].getPossibility(2))
                grid[0][col] = new Cell(2);
           
            if(grid[1][col].isNotSolved() && grid[1][col].getPossibility(2))
                grid[1][col] = new Cell(2);
           
            if(grid[2][col].isNotSolved() && grid[2][col].getPossibility(2))
                grid[2][col] = new Cell(2);
           
            if(grid[3][col].isNotSolved() && grid[3][col].getPossibility(2))
                grid[3][col] = new Cell(2);
           
            if(grid[4][col].isNotSolved() && grid[4][col].getPossibility(2))
                grid[4][col] = new Cell(2);
           
            if(grid[5][col].isNotSolved() && grid[5][col].getPossibility(2))
                grid[5][col] = new Cell(2);
           
            if(grid[6][col].isNotSolved() && grid[6][col].getPossibility(2))
                grid[6][col] = new Cell(2);
           
            if(grid[7][col].isNotSolved() && grid[7][col].getPossibility(2))
                grid[7][col] = new Cell(2);
           
            if(grid[8][col].isNotSolved() && grid[8][col].getPossibility(2))
                grid[8][col] = new Cell(2);
        }

        if(possibleNums[3] == 1){
            if(grid[0][col].isNotSolved() && grid[0][col].getPossibility(3))
                grid[0][col] = new Cell(3);
           
            if(grid[1][col].isNotSolved() && grid[1][col].getPossibility(3))
                grid[1][col] = new Cell(3);
           
            if(grid[2][col].isNotSolved() && grid[2][col].getPossibility(3))
                grid[2][col] = new Cell(3);
           
            if(grid[3][col].isNotSolved() && grid[3][col].getPossibility(3))
                grid[3][col] = new Cell(3);
           
            if(grid[4][col].isNotSolved() && grid[4][col].getPossibility(3))
                grid[4][col] = new Cell(3);
           
            if(grid[5][col].isNotSolved() && grid[5][col].getPossibility(3))
                grid[5][col] = new Cell(3);
           
            if(grid[6][col].isNotSolved() && grid[6][col].getPossibility(3))
                grid[6][col] = new Cell(3);
           
            if(grid[7][col].isNotSolved() && grid[7][col].getPossibility(3))
                grid[7][col] = new Cell(3);
           
            if(grid[8][col].isNotSolved() && grid[8][col].getPossibility(3))
                grid[8][col] = new Cell(3);
        }

        if(possibleNums[4] == 1){
            if(grid[0][col].isNotSolved() && grid[0][col].getPossibility(4))
                grid[0][col] = new Cell(4);
           
            if(grid[1][col].isNotSolved() && grid[1][col].getPossibility(4))
                grid[1][col] = new Cell(4);
           
            if(grid[2][col].isNotSolved() && grid[2][col].getPossibility(4))
                grid[2][col] = new Cell(4);
           
            if(grid[3][col].isNotSolved() && grid[3][col].getPossibility(4))
                grid[3][col] = new Cell(4);
           
            if(grid[4][col].isNotSolved() && grid[4][col].getPossibility(4))
                grid[4][col] = new Cell(4);
           
            if(grid[5][col].isNotSolved() && grid[5][col].getPossibility(4))
                grid[5][col] = new Cell(4);
           
            if(grid[6][col].isNotSolved() && grid[6][col].getPossibility(4))
                grid[6][col] = new Cell(4);
           
            if(grid[7][col].isNotSolved() && grid[7][col].getPossibility(4))
                grid[7][col] = new Cell(4);
           
            if(grid[8][col].isNotSolved() && grid[8][col].getPossibility(4))
                grid[8][col] = new Cell(4);
        }

        if(possibleNums[5] == 1){
            if(grid[0][col].isNotSolved() && grid[0][col].getPossibility(5))
                grid[0][col] = new Cell(5);
           
            if(grid[1][col].isNotSolved() && grid[1][col].getPossibility(5))
                grid[1][col] = new Cell(5);
           
            if(grid[2][col].isNotSolved() && grid[2][col].getPossibility(5))
                grid[2][col] = new Cell(5);
           
            if(grid[3][col].isNotSolved() && grid[3][col].getPossibility(5))
                grid[3][col] = new Cell(5);
           
            if(grid[4][col].isNotSolved() && grid[4][col].getPossibility(5))
                grid[4][col] = new Cell(5);
           
            if(grid[5][col].isNotSolved() && grid[5][col].getPossibility(5))
                grid[5][col] = new Cell(5);
           
            if(grid[6][col].isNotSolved() && grid[6][col].getPossibility(5))
                grid[6][col] = new Cell(5);
           
            if(grid[7][col].isNotSolved() && grid[7][col].getPossibility(5))
                grid[7][col] = new Cell(5);
           
            if(grid[8][col].isNotSolved() && grid[8][col].getPossibility(5))
                grid[8][col] = new Cell(5);
        }

        if(possibleNums[6] == 1){
            if(grid[0][col].isNotSolved() && grid[0][col].getPossibility(6))
                grid[0][col] = new Cell(6);
           
            if(grid[1][col].isNotSolved() && grid[1][col].getPossibility(6))
                grid[1][col] = new Cell(6);
           
            if(grid[2][col].isNotSolved() && grid[2][col].getPossibility(6))
                grid[2][col] = new Cell(6);
           
            if(grid[3][col].isNotSolved() && grid[3][col].getPossibility(6))
                grid[3][col] = new Cell(6);
           
            if(grid[4][col].isNotSolved() && grid[4][col].getPossibility(6))
                grid[4][col] = new Cell(6);
           
            if(grid[5][col].isNotSolved() && grid[5][col].getPossibility(6))
                grid[5][col] = new Cell(6);
           
            if(grid[6][col].isNotSolved() && grid[6][col].getPossibility(6))
                grid[6][col] = new Cell(6);
           
            if(grid[7][col].isNotSolved() && grid[7][col].getPossibility(6))
                grid[7][col] = new Cell(6);
           
            if(grid[8][col].isNotSolved() && grid[8][col].getPossibility(6))
                grid[8][col] = new Cell(6);
        }

        if(possibleNums[7] == 1){
            if(grid[0][col].isNotSolved() && grid[0][col].getPossibility(7))
                grid[0][col] = new Cell(7);
           
            if(grid[1][col].isNotSolved() && grid[1][col].getPossibility(7))
                grid[1][col] = new Cell(7);
           
            if(grid[2][col].isNotSolved() && grid[2][col].getPossibility(7))
                grid[2][col] = new Cell(7);
           
            if(grid[3][col].isNotSolved() && grid[3][col].getPossibility(7))
                grid[3][col] = new Cell(7);
           
            if(grid[4][col].isNotSolved() && grid[4][col].getPossibility(7))
                grid[4][col] = new Cell(7);
           
            if(grid[5][col].isNotSolved() && grid[5][col].getPossibility(7))
                grid[5][col] = new Cell(7);
           
            if(grid[6][col].isNotSolved() && grid[6][col].getPossibility(7))
                grid[6][col] = new Cell(7);
           
            if(grid[7][col].isNotSolved() && grid[7][col].getPossibility(7))
                grid[7][col] = new Cell(7);
           
            if(grid[8][col].isNotSolved() && grid[8][col].getPossibility(7))
                grid[8][col] = new Cell(7);
        }

        if(possibleNums[8] == 1){
            if(grid[0][col].isNotSolved() && grid[0][col].getPossibility(8))
                grid[0][col] = new Cell(8);
           
            if(grid[1][col].isNotSolved() && grid[1][col].getPossibility(8))
                grid[1][col] = new Cell(8);
           
            if(grid[2][col].isNotSolved() && grid[2][col].getPossibility(8))
                grid[2][col] = new Cell(8);
           
            if(grid[3][col].isNotSolved() && grid[3][col].getPossibility(8))
                grid[3][col] = new Cell(8);
           
            if(grid[4][col].isNotSolved() && grid[4][col].getPossibility(8))
                grid[4][col] = new Cell(8);
           
            if(grid[5][col].isNotSolved() && grid[5][col].getPossibility(8))
                grid[5][col] = new Cell(8);
           
            if(grid[6][col].isNotSolved() && grid[6][col].getPossibility(8))
                grid[6][col] = new Cell(8);
           
            if(grid[7][col].isNotSolved() && grid[7][col].getPossibility(8))
                grid[7][col] = new Cell(8);
           
            if(grid[8][col].isNotSolved() && grid[8][col].getPossibility(8))
                grid[8][col] = new Cell(8);
        }
    }
    
    //original
    /*#private void fillHorizontalSingletons(int row)
    {
        int[] possibleNums = new int[9];        

        for(int numToCheck=0; numToCheck<9; numToCheck+=1)
            for(int col=0; col<9; col+=1)
                if(grid[row][col].isNotSolved())
                {
                    grid[row][col].updatePossibilities(aggregatePossibilities(row,col));
                    grid[row][col].processPossibilityList();
                    if(grid[row][col].isNotSolved() && grid[row][col].getPossibility(numToCheck))
                        possibleNums[numToCheck]+=1; 
                }
                
        
        for(int numToCheck=0; numToCheck<9; numToCheck+=1)
            if(possibleNums[numToCheck] == 1)
                for(int col=0; col<9; col+=1)
                    if(grid[row][col].isNotSolved() && grid[row][col].getPossibility(numToCheck))
                        grid[row][col] = new Cell(numToCheck);
                    
    }*/
	
	/**
     * If there is only one place in the row that a number can go, assigns that number there
     * @param row the row's index
     * @x see fillVerticalSingletons for code explanation (works the same way)
     */
    private void fillHorizontalSingletons(int row)
    {
       int[] possibleNums = new int[9];

        //LOOP NUMBER ONE
        
        // numToCheck = 0
        
        if(grid[row][0].isNotSolved()) {
            grid[row][0].updatePossibilities(aggregatePossibilities(row,0));
            grid[row][0].processPossibilityList();
            if(grid[row][0].isNotSolved() && grid[row][0].getPossibility(0))
                possibleNums[0]+=1; }
        if(grid[row][1].isNotSolved()) {
            grid[row][1].updatePossibilities(aggregatePossibilities(row,1));
            grid[row][1].processPossibilityList();
            if(grid[row][1].isNotSolved() && grid[row][1].getPossibility(0))
                possibleNums[0]+=1; }
        if(grid[row][2].isNotSolved()) {
            grid[row][2].updatePossibilities(aggregatePossibilities(row,2));
            grid[row][2].processPossibilityList();
            if(grid[row][2].isNotSolved() && grid[row][2].getPossibility(0))
                possibleNums[0]+=1; }
        if(grid[row][3].isNotSolved()) {
            grid[row][3].updatePossibilities(aggregatePossibilities(row,3));
            grid[row][3].processPossibilityList();
            if(grid[row][3].isNotSolved() && grid[row][3].getPossibility(0))
                possibleNums[0]+=1; }
        if(grid[row][4].isNotSolved()) {
            grid[row][4].updatePossibilities(aggregatePossibilities(row,4));
            grid[row][4].processPossibilityList();
            if(grid[row][4].isNotSolved() && grid[row][4].getPossibility(0))
                possibleNums[0]+=1; }
        if(grid[row][5].isNotSolved()) {
            grid[row][5].updatePossibilities(aggregatePossibilities(row,5));
            grid[row][5].processPossibilityList();
            if(grid[row][5].isNotSolved() && grid[row][5].getPossibility(0))
                possibleNums[0]+=1; }
        if(grid[row][6].isNotSolved()) {
            grid[row][6].updatePossibilities(aggregatePossibilities(row,6));
            grid[row][6].processPossibilityList();
            if(grid[row][6].isNotSolved() && grid[row][6].getPossibility(0))
                possibleNums[0]+=1; }
        if(grid[row][7].isNotSolved()) {
            grid[row][7].updatePossibilities(aggregatePossibilities(row,7));
            grid[row][7].processPossibilityList();
            if(grid[row][7].isNotSolved() && grid[row][7].getPossibility(0))
                possibleNums[0]+=1; }
        if(grid[row][8].isNotSolved()) {
            grid[row][8].updatePossibilities(aggregatePossibilities(row,8));
            grid[row][8].processPossibilityList();
            if(grid[row][8].isNotSolved() && grid[row][8].getPossibility(0))
                possibleNums[0]+=1; }
        
        // numToCheck = 1
        if(grid[row][0].isNotSolved()) {
            grid[row][0].updatePossibilities(aggregatePossibilities(row,0));
            grid[row][0].processPossibilityList();
            if(grid[row][0].isNotSolved() && grid[row][0].getPossibility(1))
                possibleNums[1]+=1; }
        if(grid[row][1].isNotSolved()) {
            grid[row][1].updatePossibilities(aggregatePossibilities(row,1));
            grid[row][1].processPossibilityList();
            if(grid[row][1].isNotSolved() && grid[row][1].getPossibility(1))
                possibleNums[1]+=1; }
        if(grid[row][2].isNotSolved()) {
            grid[row][2].updatePossibilities(aggregatePossibilities(row,2));
            grid[row][2].processPossibilityList();
            if(grid[row][2].isNotSolved() && grid[row][2].getPossibility(1))
                possibleNums[1]+=1; }
        if(grid[row][3].isNotSolved()) {
            grid[row][3].updatePossibilities(aggregatePossibilities(row,3));
            grid[row][3].processPossibilityList();
            if(grid[row][3].isNotSolved() && grid[row][3].getPossibility(1))
                possibleNums[1]+=1; }
        if(grid[row][4].isNotSolved()) {
            grid[row][4].updatePossibilities(aggregatePossibilities(row,4));
            grid[row][4].processPossibilityList();
            if(grid[row][4].isNotSolved() && grid[row][4].getPossibility(1))
                possibleNums[1]+=1; }
        if(grid[row][5].isNotSolved()) {
            grid[row][5].updatePossibilities(aggregatePossibilities(row,5));
            grid[row][5].processPossibilityList();
            if(grid[row][5].isNotSolved() && grid[row][5].getPossibility(1))
                possibleNums[1]+=1; }
        if(grid[row][6].isNotSolved()) {
            grid[row][6].updatePossibilities(aggregatePossibilities(row,6));
            grid[row][6].processPossibilityList();
            if(grid[row][6].isNotSolved() && grid[row][6].getPossibility(1))
                possibleNums[1]+=1; }
        if(grid[row][7].isNotSolved()) {
            grid[row][7].updatePossibilities(aggregatePossibilities(row,7));
            grid[row][7].processPossibilityList();
            if(grid[row][7].isNotSolved() && grid[row][7].getPossibility(1))
                possibleNums[1]+=1; }
        if(grid[row][8].isNotSolved()) {
            grid[row][8].updatePossibilities(aggregatePossibilities(row,8));
            grid[row][8].processPossibilityList();
            if(grid[row][8].isNotSolved() && grid[row][8].getPossibility(1))
                possibleNums[1]+=1; }
        
        // numToCheck = 2
        if(grid[row][0].isNotSolved()) {
            grid[row][0].updatePossibilities(aggregatePossibilities(row,0));
            grid[row][0].processPossibilityList();
            if(grid[row][0].isNotSolved() && grid[row][0].getPossibility(2))
                possibleNums[2]+=1; }
        if(grid[row][1].isNotSolved()) {
            grid[row][1].updatePossibilities(aggregatePossibilities(row,1));
            grid[row][1].processPossibilityList();
            if(grid[row][1].isNotSolved() && grid[row][1].getPossibility(2))
                possibleNums[2]+=1; }
        if(grid[row][2].isNotSolved()) {
            grid[row][2].updatePossibilities(aggregatePossibilities(row,2));
            grid[row][2].processPossibilityList();
            if(grid[row][2].isNotSolved() && grid[row][2].getPossibility(2))
                possibleNums[2]+=1; }
        if(grid[row][3].isNotSolved()) {
            grid[row][3].updatePossibilities(aggregatePossibilities(row,3));
            grid[row][3].processPossibilityList();
            if(grid[row][3].isNotSolved() && grid[row][3].getPossibility(2))
                possibleNums[2]+=1; }
        if(grid[row][4].isNotSolved()) {
            grid[row][4].updatePossibilities(aggregatePossibilities(row,4));
            grid[row][4].processPossibilityList();
            if(grid[row][4].isNotSolved() && grid[row][4].getPossibility(2))
                possibleNums[2]+=1; }
        if(grid[row][5].isNotSolved()) {
            grid[row][5].updatePossibilities(aggregatePossibilities(row,5));
            grid[row][5].processPossibilityList();
            if(grid[row][5].isNotSolved() && grid[row][5].getPossibility(2))
            possibleNums[2]+=1; }
        if(grid[row][6].isNotSolved()) {
            grid[row][6].updatePossibilities(aggregatePossibilities(row,6));
            grid[row][6].processPossibilityList();
            if(grid[row][6].isNotSolved() && grid[row][6].getPossibility(2))
                possibleNums[2]+=1; }
        if(grid[row][7].isNotSolved()) {
            grid[row][7].updatePossibilities(aggregatePossibilities(row,7));
            grid[row][7].processPossibilityList();
            if(grid[row][7].isNotSolved() && grid[row][7].getPossibility(2))
                possibleNums[2]+=1; }
        if(grid[row][8].isNotSolved()) {
            grid[row][8].updatePossibilities(aggregatePossibilities(row,8));
            grid[row][8].processPossibilityList();
            if(grid[row][8].isNotSolved() && grid[row][8].getPossibility(2))
                possibleNums[2]+=1; }
        
        // numToCheck = 3
        if(grid[row][0].isNotSolved()) {
            grid[row][0].updatePossibilities(aggregatePossibilities(row,0));
            grid[row][0].processPossibilityList();
            if(grid[row][0].isNotSolved() && grid[row][0].getPossibility(3))
                possibleNums[3]+=1; }
        if(grid[row][1].isNotSolved()) {
            grid[row][1].updatePossibilities(aggregatePossibilities(row,1));
            grid[row][1].processPossibilityList();
            if(grid[row][1].isNotSolved() && grid[row][1].getPossibility(3))
                possibleNums[3]+=1; }
        if(grid[row][2].isNotSolved()) {
            grid[row][2].updatePossibilities(aggregatePossibilities(row,2));
            grid[row][2].processPossibilityList();
            if(grid[row][2].isNotSolved() && grid[row][2].getPossibility(3))
                possibleNums[3]+=1; }
        if(grid[row][3].isNotSolved()) {
            grid[row][3].updatePossibilities(aggregatePossibilities(row,3));
            grid[row][3].processPossibilityList();
            if(grid[row][3].isNotSolved() && grid[row][3].getPossibility(3))
                possibleNums[3]+=1; }
        if(grid[row][4].isNotSolved()) {
            grid[row][4].updatePossibilities(aggregatePossibilities(row,4));
            grid[row][4].processPossibilityList();
            if(grid[row][4].isNotSolved() && grid[row][4].getPossibility(3))
                possibleNums[3]+=1; }
        if(grid[row][5].isNotSolved()) {
            grid[row][5].updatePossibilities(aggregatePossibilities(row,5));
            grid[row][5].processPossibilityList();
            if(grid[row][5].isNotSolved() && grid[row][5].getPossibility(3))
                possibleNums[3]+=1; }
        if(grid[row][6].isNotSolved()) {
            grid[row][6].updatePossibilities(aggregatePossibilities(row,6));
            grid[row][6].processPossibilityList();
                if(grid[row][6].isNotSolved() && grid[row][6].getPossibility(3))
                    possibleNums[3]+=1; }
        if(grid[row][7].isNotSolved()) {
            grid[row][7].updatePossibilities(aggregatePossibilities(row,7));
            grid[row][7].processPossibilityList();
                if(grid[row][7].isNotSolved() && grid[row][7].getPossibility(3))
                    possibleNums[3]+=1; }
        if(grid[row][8].isNotSolved()) {
            grid[row][8].updatePossibilities(aggregatePossibilities(row,8));
            grid[row][8].processPossibilityList();
               if(grid[row][8].isNotSolved() && grid[row][8].getPossibility(3))
                    possibleNums[3]+=1; }
        
        // numToCheck = 4
        if(grid[row][0].isNotSolved()) {
            grid[row][0].updatePossibilities(aggregatePossibilities(row,0));
            grid[row][0].processPossibilityList();
            if(grid[row][0].isNotSolved() && grid[row][0].getPossibility(4))
                possibleNums[4]+=1; }
        if(grid[row][1].isNotSolved()) {
            grid[row][1].updatePossibilities(aggregatePossibilities(row,1));
            grid[row][1].processPossibilityList();
            if(grid[row][1].isNotSolved() && grid[row][1].getPossibility(4))
                possibleNums[4]+=1; }
        if(grid[row][2].isNotSolved()) {
            grid[row][2].updatePossibilities(aggregatePossibilities(row,2));
            grid[row][2].processPossibilityList();
            if(grid[row][2].isNotSolved() && grid[row][2].getPossibility(4))
                possibleNums[4]+=1; }
        if(grid[row][3].isNotSolved()) {
            grid[row][3].updatePossibilities(aggregatePossibilities(row,3));
            grid[row][3].processPossibilityList();
            if(grid[row][3].isNotSolved() && grid[row][3].getPossibility(4))
                possibleNums[4]+=1; }
        if(grid[row][4].isNotSolved()) {
            grid[row][4].updatePossibilities(aggregatePossibilities(row,4));
            grid[row][4].processPossibilityList();
            if(grid[row][4].isNotSolved() && grid[row][4].getPossibility(4))
                possibleNums[4]+=1; }
        if(grid[row][5].isNotSolved()) {
            grid[row][5].updatePossibilities(aggregatePossibilities(row,5));
            grid[row][5].processPossibilityList();
            if(grid[row][5].isNotSolved() && grid[row][5].getPossibility(4))
                possibleNums[4]+=1; }
        if(grid[row][6].isNotSolved()) {
            grid[row][6].updatePossibilities(aggregatePossibilities(row,6));
            grid[row][6].processPossibilityList();
            if(grid[row][6].isNotSolved() && grid[row][6].getPossibility(4))
                possibleNums[4]+=1; }
        if(grid[row][7].isNotSolved()) {
            grid[row][7].updatePossibilities(aggregatePossibilities(row,7));
            grid[row][7].processPossibilityList();
            if(grid[row][7].isNotSolved() && grid[row][7].getPossibility(4))
                possibleNums[4]+=1; }
        if(grid[row][8].isNotSolved()) {
            grid[row][8].updatePossibilities(aggregatePossibilities(row,8));
            grid[row][8].processPossibilityList();
            if(grid[row][8].isNotSolved() && grid[row][8].getPossibility(4))
                possibleNums[4]+=1; }
        
        // numToCheck = 5
        if(grid[row][0].isNotSolved()) {
            grid[row][0].updatePossibilities(aggregatePossibilities(row,0));
            grid[row][0].processPossibilityList();
            if(grid[row][0].isNotSolved() && grid[row][0].getPossibility(5))
                possibleNums[5]+=1; }
        if(grid[row][1].isNotSolved()) {
            grid[row][1].updatePossibilities(aggregatePossibilities(row,1));
            grid[row][1].processPossibilityList();
            if(grid[row][1].isNotSolved() && grid[row][1].getPossibility(5))
                possibleNums[5]+=1; }
        if(grid[row][2].isNotSolved()) {
            grid[row][2].updatePossibilities(aggregatePossibilities(row,2));
            grid[row][2].processPossibilityList();
            if(grid[row][2].isNotSolved() && grid[row][2].getPossibility(5))
                possibleNums[5]+=1; }
        if(grid[row][3].isNotSolved()) {
            grid[row][3].updatePossibilities(aggregatePossibilities(row,3));
            grid[row][3].processPossibilityList();
            if(grid[row][3].isNotSolved() && grid[row][3].getPossibility(5))
                possibleNums[5]+=1; }
        if(grid[row][4].isNotSolved()) {
            grid[row][4].updatePossibilities(aggregatePossibilities(row,4));
            grid[row][4].processPossibilityList();
            if(grid[row][4].isNotSolved() && grid[row][4].getPossibility(5))
                possibleNums[5]+=1; }
        if(grid[row][5].isNotSolved()) {
            grid[row][5].updatePossibilities(aggregatePossibilities(row,5));
            grid[row][5].processPossibilityList();
            if(grid[row][5].isNotSolved() && grid[row][5].getPossibility(5))
                possibleNums[5]+=1; }
        if(grid[row][6].isNotSolved()) {
            grid[row][6].updatePossibilities(aggregatePossibilities(row,6));
            grid[row][6].processPossibilityList();
            if(grid[row][6].isNotSolved() && grid[row][6].getPossibility(5))
                possibleNums[5]+=1; }
        if(grid[row][7].isNotSolved()) {
            grid[row][7].updatePossibilities(aggregatePossibilities(row,7));
            grid[row][7].processPossibilityList();
            if(grid[row][7].isNotSolved() && grid[row][7].getPossibility(5))
                possibleNums[5]+=1; }
        if(grid[row][8].isNotSolved()) {
            grid[row][8].updatePossibilities(aggregatePossibilities(row,8));
            grid[row][8].processPossibilityList();
            if(grid[row][8].isNotSolved() && grid[row][8].getPossibility(5))
                possibleNums[5]+=1; }
        
        // numToCheck = 6
        if(grid[row][0].isNotSolved()) {
            grid[row][0].updatePossibilities(aggregatePossibilities(row,0));
            grid[row][0].processPossibilityList();
            if(grid[row][0].isNotSolved() && grid[row][0].getPossibility(6))
                possibleNums[6]+=1; }
        if(grid[row][1].isNotSolved()) {
            grid[row][1].updatePossibilities(aggregatePossibilities(row,1));
            grid[row][1].processPossibilityList();
            if(grid[row][1].isNotSolved() && grid[row][1].getPossibility(6))
                possibleNums[6]+=1; }
        if(grid[row][2].isNotSolved()) {
            grid[row][2].updatePossibilities(aggregatePossibilities(row,2));
            grid[row][2].processPossibilityList();
            if(grid[row][2].isNotSolved() && grid[row][2].getPossibility(6))
                possibleNums[6]+=1; }
        if(grid[row][3].isNotSolved()) {
            grid[row][3].updatePossibilities(aggregatePossibilities(row,3));
            grid[row][3].processPossibilityList();
            if(grid[row][3].isNotSolved() && grid[row][3].getPossibility(6))
                possibleNums[6]+=1; }
        if(grid[row][4].isNotSolved()) {
            grid[row][4].updatePossibilities(aggregatePossibilities(row,4));
            grid[row][4].processPossibilityList();
            if(grid[row][4].isNotSolved() && grid[row][4].getPossibility(6))
                possibleNums[6]+=1; }
        if(grid[row][5].isNotSolved()) {
            grid[row][5].updatePossibilities(aggregatePossibilities(row,5));
            grid[row][5].processPossibilityList();
            if(grid[row][5].isNotSolved() && grid[row][5].getPossibility(6))
                possibleNums[6]+=1; }
        if(grid[row][6].isNotSolved()) {
            grid[row][6].updatePossibilities(aggregatePossibilities(row,6));
            grid[row][6].processPossibilityList();
            if(grid[row][6].isNotSolved() && grid[row][6].getPossibility(6))
                possibleNums[6]+=1; }
        if(grid[row][7].isNotSolved()) {
            grid[row][7].updatePossibilities(aggregatePossibilities(row,7));
            grid[row][7].processPossibilityList();
            if(grid[row][7].isNotSolved() && grid[row][7].getPossibility(6))
                possibleNums[6]+=1; }
        if(grid[row][8].isNotSolved()) {
            grid[row][8].updatePossibilities(aggregatePossibilities(row,8));
            grid[row][8].processPossibilityList();
            if(grid[row][8].isNotSolved() && grid[row][8].getPossibility(6))
                possibleNums[6]+=1; }
        
        // numToCheck = 7
        if(grid[row][0].isNotSolved()) {
            grid[row][0].updatePossibilities(aggregatePossibilities(row,0));
            grid[row][0].processPossibilityList();
            if(grid[row][0].isNotSolved() && grid[row][0].getPossibility(7))
                possibleNums[7]+=1; }
        if(grid[row][1].isNotSolved()) {
            grid[row][1].updatePossibilities(aggregatePossibilities(row,1));
            grid[row][1].processPossibilityList();
            if(grid[row][1].isNotSolved() && grid[row][1].getPossibility(7))
                possibleNums[7]+=1; }
        if(grid[row][2].isNotSolved()) {
            grid[row][2].updatePossibilities(aggregatePossibilities(row,2));
            grid[row][2].processPossibilityList();
            if(grid[row][2].isNotSolved() && grid[row][2].getPossibility(7))
                possibleNums[7]+=1; }
        if(grid[row][3].isNotSolved()) {
            grid[row][3].updatePossibilities(aggregatePossibilities(row,3));
            grid[row][3].processPossibilityList();
            if(grid[row][3].isNotSolved() && grid[row][3].getPossibility(7))
                possibleNums[7]+=1; }
        if(grid[row][4].isNotSolved()) {
            grid[row][4].updatePossibilities(aggregatePossibilities(row,4));
            grid[row][4].processPossibilityList();
            if(grid[row][4].isNotSolved() && grid[row][4].getPossibility(7))
                possibleNums[7]+=1; }
        if(grid[row][5].isNotSolved()) {
            grid[row][5].updatePossibilities(aggregatePossibilities(row,5));
            grid[row][5].processPossibilityList();
            if(grid[row][5].isNotSolved() && grid[row][5].getPossibility(7))
                possibleNums[7]+=1; }
        if(grid[row][6].isNotSolved()) {
            grid[row][6].updatePossibilities(aggregatePossibilities(row,6));
            grid[row][6].processPossibilityList();
            if(grid[row][6].isNotSolved() && grid[row][6].getPossibility(7))
                possibleNums[7]+=1; }
        if(grid[row][7].isNotSolved()) {
            grid[row][7].updatePossibilities(aggregatePossibilities(row,7));
            grid[row][7].processPossibilityList();
            if(grid[row][7].isNotSolved() && grid[row][7].getPossibility(7))
                possibleNums[7]+=1; }
        if(grid[row][8].isNotSolved()) {
            grid[row][8].updatePossibilities(aggregatePossibilities(row,8));
            grid[row][8].processPossibilityList();
            if(grid[row][8].isNotSolved() && grid[row][8].getPossibility(7))
                possibleNums[7]+=1; }
        
        // numToCheck = 8
        if(grid[row][0].isNotSolved()) {
            grid[row][0].updatePossibilities(aggregatePossibilities(row,0));
            grid[row][0].processPossibilityList();
            if(grid[row][0].isNotSolved() && grid[row][0].getPossibility(8))
                possibleNums[8]+=1; }
        if(grid[row][1].isNotSolved()) {
            grid[row][1].updatePossibilities(aggregatePossibilities(row,1));
            grid[row][1].processPossibilityList();
            if(grid[row][1].isNotSolved() && grid[row][1].getPossibility(8))
                possibleNums[8]+=1; }
        if(grid[row][2].isNotSolved()) {
            grid[row][2].updatePossibilities(aggregatePossibilities(row,2));
            grid[row][2].processPossibilityList();
            if(grid[row][2].isNotSolved() && grid[row][2].getPossibility(8))
                possibleNums[8]+=1; }
        if(grid[row][3].isNotSolved()) {
            grid[row][3].updatePossibilities(aggregatePossibilities(row,3));
            grid[row][3].processPossibilityList();
            if(grid[row][3].isNotSolved() && grid[row][3].getPossibility(8))
                possibleNums[8]+=1; }
        if(grid[row][4].isNotSolved()) {
            grid[row][4].updatePossibilities(aggregatePossibilities(row,4));
            grid[row][4].processPossibilityList();
            if(grid[row][4].isNotSolved() && grid[row][4].getPossibility(8))
                possibleNums[8]+=1; }
        if(grid[row][5].isNotSolved()) {
            grid[row][5].updatePossibilities(aggregatePossibilities(row,5));
            grid[row][5].processPossibilityList();
            if(grid[row][5].isNotSolved() && grid[row][5].getPossibility(8))
                possibleNums[8]+=1; }
        if(grid[row][6].isNotSolved()) {
            grid[row][6].updatePossibilities(aggregatePossibilities(row,6));
            grid[row][6].processPossibilityList();
            if(grid[row][6].isNotSolved() && grid[row][6].getPossibility(8))
                possibleNums[8]+=1; }
        if(grid[row][7].isNotSolved()) {
            grid[row][7].updatePossibilities(aggregatePossibilities(row,7));
            grid[row][7].processPossibilityList();
            if(grid[row][7].isNotSolved() && grid[row][7].getPossibility(8))
                possibleNums[8]+=1; }
        if(grid[row][8].isNotSolved()) {
            grid[row][8].updatePossibilities(aggregatePossibilities(row,8));
            grid[row][8].processPossibilityList();
            if(grid[row][8].isNotSolved() && grid[row][8].getPossibility(8))
                possibleNums[8]+=1; }
        
        
        //LOOP NUMBER TWO
        
        //numCheck = 0
        
        if(possibleNums[0] == 1)
        {
            if(grid[row][0].isNotSolved() && grid[row][0].getPossibility(0))
                grid[row][0] = new Cell(0);
            if(grid[row][1].isNotSolved() && grid[row][1].getPossibility(0))
                grid[row][1] = new Cell(0);
            if(grid[row][2].isNotSolved() && grid[row][2].getPossibility(0))
                grid[row][2] = new Cell(0);
            if(grid[row][3].isNotSolved() && grid[row][3].getPossibility(0))
                grid[row][3] = new Cell(0);
            if(grid[row][4].isNotSolved() && grid[row][4].getPossibility(0))
                grid[row][4] = new Cell(0);
            if(grid[row][5].isNotSolved() && grid[row][5].getPossibility(0))
                grid[row][5] = new Cell(0);
            if(grid[row][6].isNotSolved() && grid[row][6].getPossibility(0))
                grid[row][6] = new Cell(0);
            if(grid[row][7].isNotSolved() && grid[row][7].getPossibility(0))
                grid[row][7] = new Cell(0);
            if(grid[row][8].isNotSolved() && grid[row][8].getPossibility(0))
                grid[row][8] = new Cell(0);
        }
        //numCheck = 1
        if(possibleNums[1] == 1)
        {
            if(grid[row][0].isNotSolved() && grid[row][0].getPossibility(1))
                grid[row][0] = new Cell(1);
            if(grid[row][1].isNotSolved() && grid[row][1].getPossibility(1))
                grid[row][1] = new Cell(1);
            if(grid[row][2].isNotSolved() && grid[row][2].getPossibility(1))
                grid[row][2] = new Cell(1);
            if(grid[row][3].isNotSolved() && grid[row][3].getPossibility(1))
                grid[row][3] = new Cell(1);
            if(grid[row][4].isNotSolved() && grid[row][4].getPossibility(1))
                grid[row][4] = new Cell(1);
            if(grid[row][5].isNotSolved() && grid[row][5].getPossibility(1))
                grid[row][5] = new Cell(1);
            if(grid[row][6].isNotSolved() && grid[row][6].getPossibility(1))
                grid[row][6] = new Cell(1);
            if(grid[row][7].isNotSolved() && grid[row][7].getPossibility(1))
                grid[row][7] = new Cell(1);
            if(grid[row][8].isNotSolved() && grid[row][8].getPossibility(1))
                grid[row][8] = new Cell(1);
        }
        //numCheck = 2
        if(possibleNums[2] == 1)
        {
            if(grid[row][0].isNotSolved() && grid[row][0].getPossibility(2))
                grid[row][0] = new Cell(2);
            if(grid[row][1].isNotSolved() && grid[row][1].getPossibility(2))
                grid[row][1] = new Cell(2);
            if(grid[row][2].isNotSolved() && grid[row][2].getPossibility(2))
                grid[row][2] = new Cell(2);
            if(grid[row][3].isNotSolved() && grid[row][3].getPossibility(2))
                grid[row][3] = new Cell(2);
            if(grid[row][4].isNotSolved() && grid[row][4].getPossibility(2))
                grid[row][4] = new Cell(2);
            if(grid[row][5].isNotSolved() && grid[row][5].getPossibility(2))
                grid[row][5] = new Cell(2);
            if(grid[row][6].isNotSolved() && grid[row][6].getPossibility(2))
                grid[row][6] = new Cell(2);
            if(grid[row][7].isNotSolved() && grid[row][7].getPossibility(2))
                grid[row][7] = new Cell(2);
            if(grid[row][8].isNotSolved() && grid[row][8].getPossibility(2))
                grid[row][8] = new Cell(2);
        }
        //numCheck = 3
        if(possibleNums[3] == 1)
        {
            if(grid[row][0].isNotSolved() && grid[row][0].getPossibility(3))
                grid[row][0] = new Cell(3);
            if(grid[row][1].isNotSolved() && grid[row][1].getPossibility(3))
                grid[row][1] = new Cell(3);
            if(grid[row][2].isNotSolved() && grid[row][2].getPossibility(3))
                grid[row][2] = new Cell(3);
            if(grid[row][3].isNotSolved() && grid[row][3].getPossibility(3))
                grid[row][3] = new Cell(3);
            if(grid[row][4].isNotSolved() && grid[row][4].getPossibility(3))
                grid[row][4] = new Cell(3);
            if(grid[row][5].isNotSolved() && grid[row][5].getPossibility(3))
                grid[row][5] = new Cell(3);
            if(grid[row][6].isNotSolved() && grid[row][6].getPossibility(3))
                grid[row][6] = new Cell(3);
            if(grid[row][7].isNotSolved() && grid[row][7].getPossibility(3))
                grid[row][7] = new Cell(3);
            if(grid[row][8].isNotSolved() && grid[row][8].getPossibility(3))
                grid[row][8] = new Cell(3);
        }
        //numCheck = 4
        if(possibleNums[4] == 1)
        {
            if(grid[row][0].isNotSolved() && grid[row][0].getPossibility(4))
                grid[row][0] = new Cell(4);
            if(grid[row][1].isNotSolved() && grid[row][1].getPossibility(4))
                grid[row][1] = new Cell(4);
            if(grid[row][2].isNotSolved() && grid[row][2].getPossibility(4))
                grid[row][2] = new Cell(4);
            if(grid[row][3].isNotSolved() && grid[row][3].getPossibility(4))
                grid[row][3] = new Cell(4);
            if(grid[row][4].isNotSolved() && grid[row][4].getPossibility(4))
                grid[row][4] = new Cell(4);
            if(grid[row][5].isNotSolved() && grid[row][5].getPossibility(4))
                grid[row][5] = new Cell(4);
            if(grid[row][6].isNotSolved() && grid[row][6].getPossibility(4))
                grid[row][6] = new Cell(4);
            if(grid[row][7].isNotSolved() && grid[row][7].getPossibility(4))
                grid[row][7] = new Cell(4);
            if(grid[row][8].isNotSolved() && grid[row][8].getPossibility(4))
                grid[row][8] = new Cell(4);
        }
        //numCheck = 5
        if(possibleNums[5] == 1)
        {
            if(grid[row][0].isNotSolved() && grid[row][0].getPossibility(5))
                grid[row][0] = new Cell(5);
            if(grid[row][1].isNotSolved() && grid[row][1].getPossibility(5))
                grid[row][1] = new Cell(5);
            if(grid[row][2].isNotSolved() && grid[row][2].getPossibility(5))
                grid[row][2] = new Cell(5);
            if(grid[row][3].isNotSolved() && grid[row][3].getPossibility(5))
                grid[row][3] = new Cell(5);
            if(grid[row][4].isNotSolved() && grid[row][4].getPossibility(5))
                grid[row][4] = new Cell(5);
            if(grid[row][5].isNotSolved() && grid[row][5].getPossibility(5))
                grid[row][5] = new Cell(5);
            if(grid[row][6].isNotSolved() && grid[row][6].getPossibility(5))
                grid[row][6] = new Cell(5);
            if(grid[row][7].isNotSolved() && grid[row][7].getPossibility(5))
                grid[row][7] = new Cell(5);
            if(grid[row][8].isNotSolved() && grid[row][8].getPossibility(5))
                grid[row][8] = new Cell(5);
        }
            //numCheck = 6
        if(possibleNums[6] == 1)
        {
            if(grid[row][0].isNotSolved() && grid[row][0].getPossibility(6))
                grid[row][0] = new Cell(6);
            if(grid[row][1].isNotSolved() && grid[row][1].getPossibility(6))
                grid[row][1] = new Cell(6);
            if(grid[row][2].isNotSolved() && grid[row][2].getPossibility(6))
                grid[row][2] = new Cell(6);
            if(grid[row][3].isNotSolved() && grid[row][3].getPossibility(6))
                grid[row][3] = new Cell(6);
            if(grid[row][4].isNotSolved() && grid[row][4].getPossibility(6))
                grid[row][4] = new Cell(6);
            if(grid[row][5].isNotSolved() && grid[row][5].getPossibility(6))
                grid[row][5] = new Cell(6);
            if(grid[row][6].isNotSolved() && grid[row][6].getPossibility(6))
                grid[row][6] = new Cell(6);
            if(grid[row][7].isNotSolved() && grid[row][7].getPossibility(6))
                grid[row][7] = new Cell(6);
            if(grid[row][8].isNotSolved() && grid[row][8].getPossibility(6))
                grid[row][8] = new Cell(6);
        }
        //numCheck = 7
        if(possibleNums[7] == 1)
        {
            if(grid[row][0].isNotSolved() && grid[row][0].getPossibility(7))
                grid[row][0] = new Cell(7);
            if(grid[row][1].isNotSolved() && grid[row][1].getPossibility(7))
                grid[row][1] = new Cell(7);
            if(grid[row][2].isNotSolved() && grid[row][2].getPossibility(7))
                grid[row][2] = new Cell(7);
            if(grid[row][3].isNotSolved() && grid[row][3].getPossibility(7))
                grid[row][3] = new Cell(7);
            if(grid[row][4].isNotSolved() && grid[row][4].getPossibility(7))
                grid[row][4] = new Cell(7);
            if(grid[row][5].isNotSolved() && grid[row][5].getPossibility(7))
                grid[row][5] = new Cell(7);
            if(grid[row][6].isNotSolved() && grid[row][6].getPossibility(7))
                grid[row][6] = new Cell(7);
            if(grid[row][7].isNotSolved() && grid[row][7].getPossibility(7))
                grid[row][7] = new Cell(7);
            if(grid[row][8].isNotSolved() && grid[row][8].getPossibility(7))
                grid[row][8] = new Cell(7);
        }
        //numCheck = 8
        if(possibleNums[8] == 1)
        {
            if(grid[row][0].isNotSolved() && grid[row][0].getPossibility(8))
                grid[row][0] = new Cell(8);
            if(grid[row][1].isNotSolved() && grid[row][1].getPossibility(8))
                grid[row][1] = new Cell(8);
            if(grid[row][2].isNotSolved() && grid[row][2].getPossibility(8))
                grid[row][2] = new Cell(8);
            if(grid[row][3].isNotSolved() && grid[row][3].getPossibility(8))
                grid[row][3] = new Cell(8);
            if(grid[row][4].isNotSolved() && grid[row][4].getPossibility(8))
                grid[row][4] = new Cell(8);
            if(grid[row][5].isNotSolved() && grid[row][5].getPossibility(8))
                grid[row][5] = new Cell(8);
            if(grid[row][6].isNotSolved() && grid[row][6].getPossibility(8))
                grid[row][6] = new Cell(8);
            if(grid[row][7].isNotSolved() && grid[row][7].getPossibility(8))
                grid[row][7] = new Cell(8);
            if(grid[row][8].isNotSolved() && grid[row][8].getPossibility(8))
                grid[row][8] = new Cell(8);
        }
    }
}