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
    protected int player2Score =5;
    //protected int compScore=0;
    protected int tied =22;
    
    Game currentGame =this;           //is this correct?
    protected TikTakTohWindow window;     //game has a window
    private GameOverWindow gameOverWindow;
    
    public Game(){      //adding the buttons array-s  ,done in run method
        
        /*
        final Game currentGame =this;           //is this correct?
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TikTakTohWindow window = new TikTakTohWindow(clickCount,chancePlayer1,player1First,currentGame);
                window.setTitle("Tik-Tak-Toh");
                window.setVisible(true);
                buttons = window.buttons;
            }
        });
        */
    }
    
    protected int gameWin(){
        if(((window.buttons[0][0].getText().equals(window.buttons[0][1].getText())) && (window.buttons[0][0].getText().equals(window.buttons[0][2].getText()))) || 
            ((window.buttons[1][0].getText().equals(window.buttons[1][1].getText())) && (window.buttons[1][0].getText().equals(window.buttons[1][2].getText()))) ||
                ((window.buttons[2][0].getText().equals(window.buttons[2][1].getText())) && (window.buttons[2][0].getText().equals(window.buttons[2][2].getText()))) ||
                ((window.buttons[0][0].getText().equals(window.buttons[1][0].getText())) && (window.buttons[0][0].getText().equals(window.buttons[2][0].getText()))) ||
                ((window.buttons[0][1].getText().equals(window.buttons[1][1].getText())) && (window.buttons[0][1].getText().equals(window.buttons[2][1].getText()))) ||
                ((window.buttons[0][2].getText().equals(window.buttons[1][2].getText())) && (window.buttons[0][2].getText().equals(window.buttons[2][2].getText()))) ||
                ((window.buttons[0][0].getText().equals(window.buttons[1][1].getText())) && (window.buttons[0][0].getText().equals(window.buttons[2][2].getText()))) ||
                ((window.buttons[0][2].getText().equals(window.buttons[1][1].getText())) && (window.buttons[0][2].getText().equals(window.buttons[2][0].getText())))){
            //who wins
            if(chancePlayer1==true){
                
            }
            
            java.awt.EventQueue.invokeLater(new Runnable() {
                
                public void run() {
                    gameOverWindow =new GameOverWindow(currentGame);
                    gameOverWindow.setVisible(true);
                    //set display
                }
            });
            
            clickCount=0;
            player1First = !player1First;
            //return
        }
        //tied
        clickCount=0;
        player1First = !player1First;
        return 0;
    }

    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet.");
        window = new TikTakTohWindow(this);
        window.setVisible(true);
    }
}
