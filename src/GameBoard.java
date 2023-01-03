import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//extending the property of JPanal;
public class GameBoard extends JPanel implements ActionListener{



        // seting the height and weidth of the board;
        int height = 400;
        int weidth = 400;

        // initial starting position of snake ;
        int dots;

        // we also need weidth of dots;
        int dots_size=10;

            // making the array for storing the position of smake dots in x and y direction;
            // maxmium size of snake is height*width of the panel;
            int[] x = new int[height * weidth];
            int[] y = new int[height * weidth];

            // x and y position of food;
            int apple_x=100;
            int apple_y=100;

            Image apple;
            Image head;
            Image body;

            boolean leftDirection=true;
            boolean rightDirection=false;
            boolean upDirection=false;
            boolean downDirection=false;

            // Timer is a class where we perform action to our snake for moving them forward at a perticular time in a mile second ;

            Timer timer;
            //delay will map time with actual time;
            int DELAY=200;//400=0.4ms;
            int RAND_POS=39;// because our board having 400 weidth or height;

            boolean inGame=true;
            public GameBoard() {
                // adding keylistiner for movement of snake, chile class obj
                addKeyListener(new TAdapter());
                setFocusable(true);
                setPreferredSize(new Dimension( weidth,height));
                setBackground(Color.BLACK);
                // colling loadImage method;
                lodeImages();

                // calling initGame method
                initGame();

              }

            // making method for initial position of snake;
        public void initGame(){
           dots=3;
            // setting the snake at perticular position of the jpanal;
            for (int i = 0; i < dots; i++) {
                x[i] = 150 + i * dots_size;// length of snake is increasing in x direction like x=[50,60,70]
                y[i] = 150;           // length of snake is constant at y direction y=[50,50,50];
            }
            // initilizing the timmer
            timer =new Timer(DELAY,this); // adding this so timmer will working on this Gamebord class;
            timer.start();
            }
        private void lodeImages(){
            // setting the imageicon
            // imageIcone is a class which helps to set images
              ImageIcon image_apple=new ImageIcon("src/resources/apple.png");
              apple=image_apple.getImage();

              ImageIcon image_head=new ImageIcon("src/resources/head.png");
              head=image_head.getImage();

            ImageIcon image_body=new ImageIcon("src/resources/snakebody.png");
            body=image_body.getImage();

         }

         // we have to @overwride the method of jPanal class;
    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
    if(inGame){

        //drawing the image of apple by using graphic object on the board;
        // line 68 drawing apple image on apple_x ans appl_y position to this doard for observer;
        graphics.drawImage(apple,apple_x,apple_y,this);

        // now we are drawing the image head and body of snake of the board;

        for(int i=0;i<dots;i++){
            if(i==0){
                // drawing image head at the first position ;
                graphics.drawImage(head,x[0],y[0],this);
            }else{
                // drawing image of body;
                graphics.drawImage(body,x[i],y[i],this);
            }
        }
        // in paint method the snake will continiously moving
        // so we have to sync or change snake pain according to the time

        Toolkit.getDefaultToolkit().sync();//it will sync with actual time;

    }
    else{
        gameOver(graphics);
    }

    }
    private void  move(){

     // moving our snake in left direction;
     for(int i=dots-1;i>0;i--){
         x[i]=x[i-1];//moving x direction of snake to the left <--;
         y[i]=y[i-1];
     }
     if(leftDirection){
         x[0]-=dots_size;
     }
     if(rightDirection){
         x[0]+=dots_size;
     }
     if(upDirection){
         y[0]-=dots_size;
     }
     if(downDirection){
         y[0]+=dots_size;
     }
    }

    private  void locateApple(){

     int r=(int) (Math.random()*(RAND_POS));// it will give the random position for x
    apple_x=r*dots_size;

    r=(int) (Math.random()*(RAND_POS));
    apple_y=r*dots_size;

   }
   private void checkApple(){
      if(x[0]==apple_x&&y[0]==apple_y){
          dots++;
          locateApple();
      }
   }
   private void checkCollision(){
       // this collision for border of game board
                if(x[0]<0){
                    inGame=false;
                }
                if(x[0]>=weidth){
                    inGame=false;
                }
                if(y[0]<0){
                    inGame=false;
                }
                if(y[0]>=height){
                    inGame=false;
                }
         // this is for body collision;
        for(int i=dots-1;i>=3;i--){
            if(x[0]==x[i]&&y[0]==y[i]){
                inGame=false;
                break;
            }
        }
   }
   private void gameOver(Graphics graphics){
                String msg=" By AMANGOUR";
                Font small=new Font("Helvetica",Font.BOLD,14);
                FontMetrics metrics=getFontMetrics(small);
                graphics.setColor(Color.WHITE);
                graphics.setFont(small);
                graphics.drawString(msg,(weidth-metrics.stringWidth(msg))/2,height/2);
   }

    @Override

    public void actionPerformed (ActionEvent actionEvent){
          if(inGame){// the three method only work when ingame is true;
              checkApple();
              checkCollision();
              move();
          }
     // after moment we have to repaint the snake so;
         repaint();

    }
    // making a child class;
    public class TAdapter extends KeyAdapter{

    @Override// the property of KeyAdapter class;
    public void keyPressed(KeyEvent keyEvent){
        int key=keyEvent.getKeyCode();

        if((key==keyEvent.VK_LEFT)&&(!rightDirection)){
            leftDirection=true;
            upDirection=false;
            downDirection=false;
        }
        if((key==keyEvent.VK_RIGHT)&&(!leftDirection)){
            rightDirection=true;
            upDirection=false;
            downDirection=false;
        }
        if((key==keyEvent.VK_UP)&&(!downDirection)){
            upDirection=true;
            leftDirection=false;
            rightDirection=false;
        }
        if((key==keyEvent.VK_DOWN)&&(!upDirection)){
            downDirection=true;
            leftDirection=false;
            rightDirection=false;
        }
    }

    }

}