import javax.swing.*;

// exten the property of JFrame;
public class SnakeGame extends JFrame {
    private  GameBoard board;
    // constructor of snakegame class;

    public SnakeGame(){
        // adding gameboard to the Jframe;
        board=new GameBoard();
        add(board);
        setResizable(false);
        pack();
        setVisible(true);
    }
    public static void main(String[] args){
        // making the obj of jframe
        // where SnakeGame containg all the property of JFrame calss;
        JFrame snakegame=new SnakeGame();
    }
}
