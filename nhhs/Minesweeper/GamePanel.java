import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends Panel implements ActionListener
{
    sweeperButton[][] gridButtons;
    
    int[][] solvedPuzzle;
    boolean[][] revealed; 
    boolean[][] flagged;
   
    Bubbler b;
    WinDetector w;
    
    public static void main(String[] args)
    {
        JFrame gameFrame = new JFrame("Minesweeper");
        gameFrame.add(new GamePanel(9,9,10));
        gameFrame.setVisible(true);
        gameFrame.setSize(9*35,9*37-5);
        gameFrame.setResizable(false);
    }
    
    public GamePanel(int numRows, int numCols, int numMines)
    {
        super();
        
        Game.status.set(GameStatus.CONTINUE);
        
        solvedPuzzle = Game.generatePuzzle(numRows, numCols, numMines);
       
        b = new Bubbler(solvedPuzzle);
        revealed = b.getRevealed();    

        w = new WinDetector(solvedPuzzle,revealed);
        Game.status.set(w.getGameStatus());
        
        setLayout(new GridLayout(numRows,numCols));
        
        gridButtons = new sweeperButton[numRows][numCols];
        
        for(int i=0; i<numRows; i++)
            for(int j=0; j<numCols; j++)
            {
                gridButtons[i][j] = new sweeperButton(Game.openJPG,i,j);
                add(gridButtons[i][j]);
                gridButtons[i][j].addActionListener(this);
            }
        setSize(numCols*36,numRows*38);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        sweeperButton temp = ((sweeperButton)(e.getSource()));
        if(e.getModifiers() == 17) //Flag Reveal Mode
        {
            try{
                //System.out.println("Modified");
                temp.setIcon(Game.flagJPG);
                flagged[temp.getRow()][temp.getCol()] = true;
            }
            catch(Exception ex) {}
        }
        else //Regular reveal mode
        {
            b.revealLocation(temp.getRow(),temp.getCol());
            revealed = b.getRevealed();
            w.updateRevealed(revealed);
            updateButtonContents();
            Game.status.set(w.getGameStatus());
            switch(Game.status.get())
            {
                case GameStatus.GAMEOVER:   gameOverMessage();
                                            break;
                case GameStatus.WON:        winMessage();
                                            break;
            }
        }
    }
    
    public void updateButtonContents()
    {
        for(int i=0; i<revealed.length; i++)
            for(int j=0; j<revealed[i].length; j++)
                if(revealed[i][j])
                    switch(solvedPuzzle[i][j])
                    {
                        case Game.MINE: gridButtons[i][j].setIcon(Game.mineJPG);
                                        break;
                        case 0:         gridButtons[i][j].setEnabled(false);
                                        break;
                        default:        gridButtons[i][j].setIcon(new ImageIcon(Integer.toString(solvedPuzzle[i][j]) + ".png"));
                                        break;                
                    }           
    }
    
    public void gameOverMessage()
    {
        for(int i=0; i<revealed.length; i++)
            for(int j=0; j<revealed[i].length; j++) 
            {
                if(solvedPuzzle[i][j] == Game.MINE)
                    gridButtons[i][j].setIcon(Game.mineJPG);     
                gridButtons[i][j].setEnabled(false);
            }
        JOptionPane.showMessageDialog(null,"Game Over","Minesweeper",JOptionPane.OK_OPTION,Game.mineJPG);
        System.exit(0); //Quit runtime
    }
    
    public void winMessage()
    {
        JOptionPane.showMessageDialog(null,"Game Won!","Minesweeper",JOptionPane.OK_OPTION,Game.flagJPG);
        System.exit(0); //Quit runtime
    }
    
    private class sweeperButton extends JButton
    {
        private int row, col;
        public sweeperButton(ImageIcon icon, int row, int col) {
            super(icon);
            this.row = row;
            this.col = col;
        }
        public int getRow() {
            return row;
        }
        public int getCol() {
            return col;
        }
    }
}
