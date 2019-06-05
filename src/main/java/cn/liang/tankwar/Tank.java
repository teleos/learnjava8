package cn.liang.tankwar;

import java.awt.*;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Tank {
    private int x,y;
    private int direct;
    private int speed = 2;
    protected boolean live = true;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public void moveUp(){
        this.y -= speed;
    }


    public void moveDown(){
        this.y += speed;
    }

    public void moveLeft(){
        this.x -= speed;
    }

    public void moveRight(){
        this.x += speed;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }
}


class Hero extends Tank{

    public Hero(int x, int y) {
        super(x, y);
    }
}

class EnemyTank extends Tank implements Runnable{


    public EnemyTank(int x,int y){
        super(x,y);
    }


    @Override
    public void run() {

        System.out.println(isLive());
        while (isLive() ){


            int i = Calendar.getInstance().get(Calendar.SECOND);

            try {
                TimeUnit.MILLISECONDS.sleep(100);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            switch (getDirect()){
                case TankConstant.TANK_DIRECT_UP:
                    if (getY()<700&&getY()>0)
                        moveUp();

                    break;
                case TankConstant.TANK_DIRECT_DOWN:
                    if (getY()<700&&getY()>0)
                        moveDown();

                    break;
                case TankConstant.TANK_DIRECT_LEFT:
                    if (getX()<1000&& getX()>0)
                        moveLeft();


                    break;
                case TankConstant.TANK_DIRECT_RIGHT:
                    if (getX()<1000&& getX()>0)
                        moveRight();


                    break;
            }


            if (i % 13 == 0 ){
                setDirect(TankConstant.randDirect());
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }
}