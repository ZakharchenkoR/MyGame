import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;


public class EngineGame extends JPanel implements ActionListener {
    private final int DOTE_SIZE = 16;
    private Image apple;
    private Image fase;
    private int appleX;
    private int appleY;
    private int faseX;
    private int faseY;
    private Timer timer;
    private boolean inGame = true;
    private boolean left = false;
    private boolean down = true;
    private boolean right = false;
    private int errorCount = 0;
    private int count = 0;


    public EngineGame(){
        setBackground(Color.black);
        loadImages();
        initGame();
        addKeyListener(new FieldKeyLisstener());
        setFocusable(true);
    }

    public void initGame(){
        timer = new Timer(250,this);
        timer.start();
        createApple();
        createFase();
    }

    public void createApple(){
        appleX = new Random().nextInt(20)*DOTE_SIZE;
        appleY = 0;
    }

    public void createFase(){
        faseX = new Random().nextInt(20)*DOTE_SIZE;
        faseY = 304;
    }

    public void loadImages(){
        ImageIcon iia = new ImageIcon("apple.png");
        apple=iia.getImage();
        ImageIcon iif = new ImageIcon("fase.gif");
        fase=iif.getImage();
    }

    public void move(){
        if(down && appleY<300){
            appleY+=16;
        }else{
            down=false;
        }
        if(left &&  faseX!= 0){
            faseX-=16;
            left=false;
        }
        if(right && faseX<300){
            faseX+=16;
            right=false;
        }
    }

    public void chackApple(){
        if(faseX==appleX && faseY==appleY){
            count++;
            createApple();
        }
    }

    public void exit(){
        if(appleY>=300){
            errorCount++;
            createApple();
        }
        if(errorCount>2){
            inGame=false;
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(inGame) {
            g.drawImage(apple,appleX,appleY,this);
            g.drawImage(fase,faseX,faseY,this);
        }else{
            String str = "Game Over! Your points: "+ count ;
            g.setColor(Color.white);
            g.drawString(str,75,160);
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(inGame){
            chackApple();
            exit();
            move();
        }
        repaint();
    }


    class FieldKeyLisstener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            if(e.getKeyCode()==e.VK_LEFT){
                left=true;
            }
            if(e.getKeyCode()==e.VK_RIGHT){
                right=true;
            }
        }
    }


}
