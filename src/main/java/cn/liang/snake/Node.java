package cn.liang.snake;

import java.awt.*;

public class Node {

    int row,col;

    int x ;
    int y ;

    Node prev,next;

    public Node(int row, int col) {
        this.row = row;
        this.col = col;
    }


    public void paintNode(Graphics g) {
        x = Yard.x + col*Yard.Node_SIZE;
        y = Yard.y + row*Yard.Node_SIZE;
        Color c = g.getColor();
        g.setColor(Color.BLACK);
        g.fillRect(x,y,Yard.Node_SIZE,Yard.Node_SIZE);


        g.setColor(c);
    }
}
