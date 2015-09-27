
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.awt.event.*;

public class UserInterface extends JApplet implements MouseListener
{
    int lastClickX, lastClickY, myHeight, myWidth;
    BufferedImage cell, cellWithFlag;
    
    int[][] unsolvedPuzzle;
    int[][] solvedPuzzle;
    boolean[][] revealed; 
    boolean[][] flagged;
    
    Solver s;
    Bubbler b;
    
    public void init()
    {
        // this is a workaround for a security conflict with some browsers
        // including some versions of Netscape & Internet Explorer which do 
        // not allow access to the AWT system event queue which JApplets do 
        // on startup to check access. May not be necessary with your browser. 
        JRootPane rootPane = this.getRootPane();    
        rootPane.putClientProperty("defeatSystemEventQueueCheck", Boolean.TRUE);
        addMouseListener(this);
        try{
            cell = ImageIO.read(new File("cell.jpg"));
            cellWithFlag = ImageIO.read(new File("cellWithFlag.jpg"));
        }
        catch(Exception e) {}
        
        
        unsolvedPuzzle = Game.generatePuzzle(9,9,10);
        
        s = new Solver(unsolvedPuzzle);
        solvedPuzzle = s.getPuzzle();
        
        b = new Bubbler(solvedPuzzle);
        revealed = b.getRevealed();
        
        setSize(20*9,20*9);
        
    }
    
    public void start()
    {
        
    }


    /**
     * Paint method for applet.
     * 
     * @param  g   the Graphics object for this applet
     */
    public void paint(Graphics g)
    {

        //g.drawImage(cell,0,0,null);
        drawPuzzle(g);
    }
    
    public void drawPuzzle(Graphics g)
    {
        for(int row = 0; row < revealed.length; row++)
            for(int col = 0; col < revealed[row].length; col++)
            {
                if(revealed[row][col])
                {
                    if(flagged[row][col]) {
                        g.drawImage(cellWithFlag,col*20,row*20,null);
                    }
                    else if(solvedPuzzle[row][col] > 0)
                    {
                        g.drawImage(cell,col*20,row*20,null);
                        g.setColor(Color.black);
                        g.drawString(Integer.toString(solvedPuzzle[row][col]),col*20 + 5, row*20 + 5);
                    }  
                }
                else
                    g.drawImage(cell,col*20,row*20,null);
                    
                //DEBUG    
                if(solvedPuzzle[row][col] > 0)
                    {
                        g.drawImage(cell,col*20,row*20,null);
                        g.setColor(Color.black);
                        g.drawString(Integer.toString(solvedPuzzle[row][col]),col*20 + 5, row*20 + 15);
                    }      
            }
        }
    public void mouseClicked(MouseEvent e)
    {
        lastClickX = e.getX();
        lastClickY = e.getY();
        
        //RUN ALL ALGORITHMS
        repaint();
    }
    
    public void mousePressed(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
}
