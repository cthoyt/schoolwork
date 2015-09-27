/**
 * Cell.java
 * Contains information about and operations for an individual Cell in a Sudoku puzzle
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

public class Cell
{
    private static boolean[] trues = new boolean[] {true, true, true, true, true, true, true, true, true};
    private static boolean[] falses = new boolean[] {false,false,false,false,false,false,false,false,false};
    
    /**
     * The number of Cells in the Sudoku program that have been solved. Used to determine if the puzzle is solved.
     */
    public static int numSolved = 0;
    private boolean[] possibilities;
    private int num;
    
    /**
     * Creates a Cell whose value is unknown.
     */
    public Cell()
    {
        //Before checking around, any number can go in the Cell
        possibilities = trues;
        num = -1;
    }
    
    /**
     * Creates a Cell whose value is already determined
     * @param int numIn contains the value of the cell
     */
    public Cell(int numIn)
    {    
        num = numIn;
        //Except for the Cell's value, none of the values in possibilities can be true
        possibilities = falses;
        possibilities[num] = true;
        numSolved++;
    }
    
    /**
     * Checks if the Cell's value has been set
     * @return boolean true if the Cell's value has not been found yet
     */
    public boolean isNotSolved()
    {
        return (num == -1);
    }
    
    /**
     * Checks if the Cell's value has been set
     * @return boolean true if the Cell's value has been found (or was initially entered)
     */
    public boolean isSolved()
    {
        return (num != -1);
    }
    
    /**
     * Gets the value of the Cell as an integer
     * @return int num (Cell's value 0-8)
     */
    public int toInt()
    {
        return num;
    }
    
    /**
     * Gets a list detailing whether the value at each index + 1 can go in that Cell
     * @return boolean[9] where returnedArray[0] = true means that a 1 can go in that spot in the Cell
     */
    public boolean[] getPossibilities()
    {
        return possibilities;
    }
    
    /**
     * Gets a particular item from the possibility list at index i
     * @see getPossibilities()
     * @return boolean true means that i+1 can go in that cell
     */
    public boolean getPossibility(int i)
    {
        return possibilities[i];
    }
    
    /**
     * Updates the list detailing whether the value at each index + 1 can go in that Cell
     * @param boolean[9] contains new values
     */
    public void updatePossibilities(boolean[] input)
    {
        possibilities = input;
    }
    
    
    //original
    /*#public void processPossibilityList()
    {
        int numPossible = 0;
        //If 2 or more values are possible, the cell cannot be solved. Therefore, the loop can terminate.
        for(int i=0; i<9 && numPossible < 2; i++)
        {
            if(possibilities[i])
            {
                numPossible++;
                //if there is only one true, this will set num to its correct value
                num = i;
            }
        }
        
        //if there was more than one true, this resets num
        if(numPossible > 1)
            num = -1;
        else
            numSolved++;
    }*/
    /**
     * Sets the cell's value to index for only remaining true value in array of possibilities.                                             
     * If it solves the Cell, increments numSolved.
     */
    public void processPossibilityList()
    {
        int numPossible = 0;
        if(possibilities[0])
        {
            numPossible++;
                num = 0;
        }
        if(possibilities[1])
        {
            numPossible++;
            num = 1;
        }
        if(numPossible < 2)
        {
            if(possibilities[2])
            {
                numPossible++;
                num = 2;
            }
        }
        if(numPossible < 2)
        {
            if(possibilities[3])
            {
                numPossible++;
                num = 3;
            }
        }
        if(numPossible < 2)
        {
            if(possibilities[4])
            {
                numPossible++;
                num = 4;
            }
        }
        if(numPossible < 2)
        {
            if(possibilities[5])
            {
                numPossible++;
                num = 5;
            }
        }
        if(numPossible < 2)
        {
            if(possibilities[6])
            {
                numPossible++;
                num = 6;
            }
        }
        if(numPossible < 2)
        {
            if(possibilities[7])
            {
                numPossible++;
                num = 7;
            }
        }
        if(numPossible < 2)
        {
            if(possibilities[8])
            {
                numPossible++;
                num = 8;
            }
        }
        
        if(numPossible > 1)
            num = -1;
        else
            numSolved++;
    }
}