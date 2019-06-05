package cn.liang.tankwar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import java.util.concurrent.TimeUnit;


public class GamePanel extends JPanel implements KeyListener,Runnable{
    private Hero hero;

    private Vector<EnemyTank> ets = new Vector<>();
    int enSize = 7;

    public GamePanel(){
        hero = new Hero(10,10);

        for (int i = 0; i < enSize; i++) {
            EnemyTank et = new EnemyTank((i + 10) * 40, 2);
            et.setDirect(TankConstant.TANK_DIRECT_DOWN);
            ets.add(et);

            new Thread(et).start();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.fillRect(0,0,1000,700);

        drawTank(hero,g,hero.getDirect(),0);

        for (EnemyTank et : ets){
            drawTank(et,g,et.getDirect(),1);
        }

        g.setColor(Color.BLACK);

    }

    private void drawTank(Tank t, Graphics g, int direct, int type) {

            switch (direct){
                case TankConstant.TANK_DIRECT_UP:
                    if (type == 0)
                        g.drawImage(TankConstant.p1tankU,t.getX(),t.getY(),30,30,null);
                    else if (type == 1)
                        g.drawImage(TankConstant.enemy1U,t.getX(),t.getY(),30,30,null);

                    break;
                case TankConstant.TANK_DIRECT_DOWN:
                    if (type == 0)
                        g.drawImage(TankConstant.p1tankD,t.getX(),t.getY(),30,30,null);
                    else if (type == 1)
                        g.drawImage(TankConstant.enemy1D,t.getX(),t.getY(),30,30,null);
                    break;
                case TankConstant.TANK_DIRECT_LEFT:
                    if (type == 0)
                        g.drawImage(TankConstant.p1tankL,t.getX(),t.getY(),30,30,null);
                    else if (type == 1)
                        g.drawImage(TankConstant.enemy1L,t.getX(),t.getY(),30,30,null);

                    break;
                case TankConstant.TANK_DIRECT_RIGHT:
                    if (type == 0)
                        g.drawImage(TankConstant.p1tankR,t.getX(),t.getY(),30,30,null);
                    else if (type == 1)
                        g.drawImage(TankConstant.enemy1R,t.getX(),t.getY(),30,30,null);

                    break;
            }


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_W){
            hero.setDirect(TankConstant.TANK_DIRECT_UP);
            hero.moveUp();
        }else if(e.getKeyCode()==KeyEvent.VK_S){
            hero.setDirect(TankConstant.TANK_DIRECT_DOWN);
            hero.moveDown();
        }else if(e.getKeyCode()==KeyEvent.VK_A){
            hero.setDirect(TankConstant.TANK_DIRECT_LEFT);
            hero.moveLeft();
        }else if(e.getKeyCode()==KeyEvent.VK_D){
            hero.setDirect(TankConstant.TANK_DIRECT_RIGHT);
            hero.moveRight();
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true)
        try {
            TimeUnit.MILLISECONDS.sleep(100);
            repaint();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
