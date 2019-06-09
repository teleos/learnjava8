package cn.liang.snake;

import java.awt.*;
import java.util.Random;

public class Egg {

    int row,col;
    Random r = new Random();

    public Egg(int row, int col) {
        this.row = row;
        this.col = col;
    }



    public void paintEgg(Graphics g){

        int x = Yard.x + col*Yard.Node_SIZE;
        int y = Yard.y + row*Yard.Node_SIZE;

        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x,y,Yard.Node_SIZE,Yard.Node_SIZE);

        g.setColor(c);

    }

    public void reAppear() {
        this.row = r.nextInt(Yard.Node_COUNT);
        this.col = r.nextInt(Yard.Node_COUNT);
    }
}
