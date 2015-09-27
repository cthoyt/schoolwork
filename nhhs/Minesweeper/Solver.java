public class Solver
{
    int[][] puzzle;
    
    public Solver(int[][] unsolvedPuzzle)
    {
        this.puzzle = unsolvedPuzzle;
        getNumAdjacentMines(0,0);
    }
    
    public int[][] getPuzzle()
    {
        return puzzle;
    }
    
    public void getNumAdjacentMines(int row, int column)
    {   
        if(0<=row && row<puzzle.length && 0<=column && column<puzzle[row].length)
        {
            if(puzzle[row][column] == Game.OPEN)
            {
                //Resets current position to 0 (from -2) so algorithm can count number of Adjacent mines
                puzzle[row][column] = 0;                             
                
                //Counts number of mines in 8 sides
                if(isMine(row+1,column-1)) { puzzle[row][column]++; } 
                if(isMine(row+1,column+0)) { puzzle[row][column]++; } 
                if(isMine(row+1,column+1)) { puzzle[row][column]++; } 
                if(isMine(row+0,column-1)) { puzzle[row][column]++; } 
                if(isMine(row+0,column+1)) { puzzle[row][column]++; }
                if(isMine(row-1,column-1)) { puzzle[row][column]++; } 
                if(isMine(row-1,column+0)) { puzzle[row][column]++; } 
                if(isMine(row-1,column+1)) { puzzle[row][column]++; } 
                
                //Recursive Calls
                getNumAdjacentMines(row+1,column);
                getNumAdjacentMines(row-1,column);
                getNumAdjacentMines(row,column-1);    
                getNumAdjacentMines(row,column+1);
            }
        }   
        //CHECKS NESTED OPEN SQUARES - STILL RECURSIVE!
        for(int i = 0; i < puzzle.length; i++)
            for(int j =0; j < puzzle[i].length; j++)
                if(puzzle[i][j] == Game.OPEN)
                    getNumAdjacentMines(i,j);
    }   
    
    public boolean isMine(int row, int column)
    {
        if(0<=row && row<puzzle.length && 0<=column && column<puzzle[row].length)
            if(puzzle[row][column] == Game.MINE)
                return true;
        return false;
    }
}
