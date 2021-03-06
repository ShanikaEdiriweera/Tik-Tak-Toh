//notes - 
//add a notice saying whose chance at the first
//disable the buttons at the start and add graphics
//add the line to cross when win
//is OOP concepts ok keeping clickcount chanceplayer1 and player1first in the window
//is it ok to make the game runnable instead of the Jframe and run it in a new thread
//how to freeze the window when game over window is started
//do we need a new thread to game over window?
//do we have acess to  


package tiktaktoh;

import org.apache.log4j.Logger;
/**
 *
 * @author SHAEDI
 */
public class Game implements Runnable{
    //private JButton[][] buttons;
    
    protected int clickCount = 0;         //clicks in a game
    protected boolean chancePlayer1 = true;       //variable to decide whose turn and which mark to appear-S
    protected boolean player1First = true;      //who is starting the game
    
    //game info
    protected boolean singlePlayerMode = true;
    protected int player1Score =0;
    protected int player2Score =0;
    //protected int compScore=0;
    protected int tied =0;
    protected String winner="n";
    
    Game currentGame =this;           //is this correct?
    protected TikTakTohWindow window;     //game has a window
    
    private GameOverDialogBox gameOverWindow;   //dialogbox
    
    protected Computer computer;
    
    private static Logger logger = Logger.getLogger(Game.class);

    
    public Game(){    
        computer = new Computer(this);  // making the new computer when stating the game
        
    }
    
    protected int gameWin(){
        
        if(((window.buttons[0][0].getText().equals(window.buttons[0][1].getText())) && (window.buttons[0][0].getText().equals(window.buttons[0][2].getText()))) || 
            ((window.buttons[1][0].getText().equals(window.buttons[1][1].getText())) && (window.buttons[1][0].getText().equals(window.buttons[1][2].getText()))) ||
                ((window.buttons[2][0].getText().equals(window.buttons[2][1].getText())) && (window.buttons[2][0].getText().equals(window.buttons[2][2].getText()))) ||
                ((window.buttons[0][0].getText().equals(window.buttons[1][0].getText())) && (window.buttons[0][0].getText().equals(window.buttons[2][0].getText()))) ||
                ((window.buttons[0][1].getText().equals(window.buttons[1][1].getText())) && (window.buttons[0][1].getText().equals(window.buttons[2][1].getText()))) ||
                ((window.buttons[0][2].getText().equals(window.buttons[1][2].getText())) && (window.buttons[0][2].getText().equals(window.buttons[2][2].getText()))) ||
                ((window.buttons[0][0].getText().equals(window.buttons[1][1].getText())) && (window.buttons[0][0].getText().equals(window.buttons[2][2].getText()))) ||
                ((window.buttons[0][2].getText().equals(window.buttons[1][1].getText())) && (window.buttons[0][2].getText().equals(window.buttons[2][0].getText()))))
        {
            //who wins
            if(chancePlayer1==true){
                winner="p1";
                logger.info("Player 1 won");
                player1Score++;
                logger.info("Player 1 Score = " + player1Score );
            }else{
                winner="p2";
                logger.info("Player 2 won");
                player2Score++;
                logger.info("Player 2 Score = " + player2Score );
            }
        }
        else if(clickCount==9){         //tied
            winner="t";
            tied++;
            logger.info("Game tied");
            logger.info("Tied matches Score = " + tied );
            clickCount=0;
        }
        //game over window
        if(!"n".equals(winner)){
            computer.isOn=false;
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    gameOverWindow =new GameOverDialogBox(window,true,currentGame);
                    gameOverWindow.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });                    
                    gameOverWindow.setVisible(true);//set display
                }
            });
                
            clickCount=0;
            player1First = !player1First;
            return 0;
        }
        return 0;
    }

    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet.");
        window = new TikTakTohWindow(this);
        window.setVisible(true);

    }
}
