package cn.liang.snake;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Snake extends KeyAdapter {

    Node head,tail;
    Direction dir = Direction.L;

    public Snake() {
        head = new Node(20,20);
        tail = head;
    }

    public void addToHead(){
        Node n = null;
        switch (dir){
            case L:
                n = new Node(head.row,head.col-1);
                break;
            case D:
                n = new Node(head.row+1,head.col);
                break;
            case R:
                n = new Node(head.row,head.col+1);
                break;
            case U:
                n = new Node(head.row-1,head.col);
                break;
        }
        n.next = head;
        head.prev = n;
        head = n;
    }

    public void drawSnake(Graphics g) {
        Node n = head;
        while (n!=null){
            n.paintNode(g);
            n = n.next;
        }
        move();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_LEFT:
                dir = Direction.L;
                break;
            case KeyEvent.VK_UP:
                dir = Direction.U;
                break;
            case KeyEvent.VK_RIGHT:
                dir = Direction.R;
                break;
            case KeyEvent.VK_DOWN:
                dir = Direction.D;
                break;
        }
    }

    private void move(){
        addToHead();
        deleteTail();
        boundaryCheck();
    }

    private void boundaryCheck() {
        if (head.row < 0)
            head.row = Yard.Node_COUNT-1;
        if (head.col < 0){
            head.col = Yard.Node_COUNT-1;
        }
        if (head.row > Yard.Node_COUNT - 1) head.row = 0;
        if (head.col > Yard.Node_COUNT - 1) head.col = 0;
    }

    private void deleteTail() {
        if (tail == null) return;
        tail = tail.prev;
        tail.next.prev = null;
        tail.next = null;

    }

    public void eat(Egg e) {
        if (head.row  == e.row && head.col == e.col){
            addToHead();
            e.reAppear();
        }
    }
}
