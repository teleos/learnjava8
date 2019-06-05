package cn.liang.tankwar;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class TankGame extends JFrame {

    private GamePanel gamePanel ;

    public TankGame(){

        gamePanel = new GamePanel();
        new Thread(gamePanel).start();
        addKeyListener(gamePanel);
        add(gamePanel);
        setSize(1000,700);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
//        Arrays.asList(UIManager.getInstalledLookAndFeels()).forEach(System.out::println); ;
//        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        new TankGame();



    }
}
