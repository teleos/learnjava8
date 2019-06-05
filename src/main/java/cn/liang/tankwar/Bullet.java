package cn.liang.tankwar;


import java.awt.*;

public class Bullet implements Runnable{

    private int x,y,width=2,height=2,direct;
    boolean isDead,isEnemy;
    private Image BulletImage;

    public Bullet(int x, int y,  int direct, boolean isDead) {
        this.x = x;
        this.y = y;

        this.direct = direct;
        this.isDead = isDead;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }



    public Image getBulletImage() {
        return BulletImage;
    }

    public void setBulletImage(Image bulletImage) {
        BulletImage = bulletImage;
    }

    @Override
    public void run() {
       while (!isDead){

            switch (direct){

                case TankConstant.TANK_DIRECT_UP:
                    y-=2;
                    break;
                case TankConstant.TANK_DIRECT_DOWN:
                    y+=2;
                    break;
                case TankConstant.TANK_DIRECT_LEFT:
                    x-=2;
                    break;
                case TankConstant.TANK_DIRECT_RIGHT:
                    x+=2;
                    break;

            }

            if (x>1000|| x<0 || y>700||y<0){
                setDead(true);
            }
       }
    }
}
