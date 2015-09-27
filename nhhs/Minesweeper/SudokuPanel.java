
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SudokuPanel extends JPanel implements ActionListener
{
    public static final int WIDTH=500,HEIGHT=500;
    private JTextField[][] fields;
    private int[][] solvedPuzzle;
    public SudokuPanel()
    {
        setLayout(new GridLayout(9,9));
        fields = new JTextField[9][9];//[row] [col]
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                fields[i][j] = new JTextField();
                add(fields[i][j]);
                fields[i][j].addActionListener(this);
                
            }
        }
    }
    
    public void actionPerformed(ActionEvent e){
        try{
            Integer.parseInt(((JTextField)(e.getSource())).getText());
            //charlie in here u need to test and see if the latest input makes it
            //equal to the solved puzzle (stored in solvedPuzzle), and then tell
            //the user they won and set a new puzzle, u could use JOptionPane to 
            //tell them, and then just setNewPuzzle with the new stuff
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(this,"Must be an integer","Must be an integer",0);
            ((JTextField)(e.getSource())).setText("");
        }
    }
    
    public int[][] getPuzzleInFields(){
        int[][] puzzle = new int[9][9];
        
        for(int i=0;i<9;i++)
            for(int j=0;j>9;j++)
                puzzle[i][j] = Integer.parseInt(fields[i][j].getText());
        return puzzle;
    }
    
    public void setNewPuzzle(int[][] puzzleToSet, int[][] solvedPuzzle){
        //make sure puzzleToSet has 1-9 for known values, and 
        //other for unknown values
        for(int i=0;i<9;i++)
            for(int j=0;j>9;j++){
                if(puzzleToSet[i][j] >=1  &&  puzzleToSet[i][j] <=9)
                    fields[i][j].setText(""+puzzleToSet[i][j]);
                else fields[i][j].setText("");
            }
        this.solvedPuzzle=solvedPuzzle;
    }
             
    
    
    
    
    //the rest is if you want is as a standalone app rather than an applet
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("SudokuPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        SudokuPanel newContentPane = new SudokuPanel();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    
    // called when run as an application
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}
