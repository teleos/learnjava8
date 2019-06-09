package cn.liang.snake;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Yard extends JFrame {
    public static final int Node_SIZE =15;
    public static final int Node_COUNT = 30;

    public static final int AreaSize = Node_SIZE * Node_COUNT;

    static int x = AreaSize / 2;
    static int y = AreaSize / 2;

    Snake snake = new Snake();

    Egg egg = new Egg(10,10);

    public Yard(){
        setTitle("贪吃蛇");
        setVisible(true);
        addKeyListener(snake);
        setSize(new Dimension(AreaSize*2,AreaSize*2));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        new Thread(()->{
            while (true) {
                try {

                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
            }
        }).start();
    }

    @Override
    public void paint(Graphics g) {
       Color c = g.getColor();
       g.setColor(Color.WHITE);
       g.fillRect(0,0,this.getWidth(),this.getHeight());
       g.setColor(c);
        //绘制格子
        for (int i = 0; i <= Node_COUNT; i++) {
            //绘制横线
            g.drawLine(x,y+i*Node_SIZE,x+AreaSize,y+i*Node_SIZE);
            //绘制竖线
            g.drawLine(x+i*Node_SIZE,y,x+i*Node_SIZE,y+AreaSize);
        }
        snake.drawSnake(g);
        egg.paintEgg(g);

        snake.eat(egg);
    }

    //double buffer

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        super.update(g);
        if (offScreenImage == null){
            offScreenImage = this.createImage(this.getWidth(),this.getHeight());
        }
        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage,0,0,null);
    }

    public static void main(String[] args) {
        new Yard();
    }
}
