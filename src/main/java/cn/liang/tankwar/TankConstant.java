package cn.liang.tankwar;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class TankConstant {


    public static final int TANK_TYPE_HERO = 0;
    public static final int TANK_TYPE_ENEMY = 1;


    public static final int TANK_DIRECT_UP = 0;
    public static final int TANK_DIRECT_DOWN = 1;
    public static final int TANK_DIRECT_LEFT = 2;
    public static final int TANK_DIRECT_RIGHT = 3;


    public static  Image p1tankD = null ;
    public static  Image p1tankL = null ;
    public static  Image p1tankR = null ;
    public static  Image p1tankU = null ;

    public static Image enemy1D = null ;
    public static Image enemy1L = null ;
    public static Image enemy1R = null ;
    public static Image enemy1U = null ;

    static {

        try {
            p1tankD = ImageIO.read(new File("D:\\Users\\Administrator\\IdeaProjects\\learnjava8\\src\\main\\java\\img\\p1tankD.gif"));
            p1tankL = ImageIO.read(new File("D:\\Users\\Administrator\\IdeaProjects\\learnjava8\\src\\main\\java\\img\\p1tankL.gif"));
            p1tankR = ImageIO.read(new File("D:\\Users\\Administrator\\IdeaProjects\\learnjava8\\src\\main\\java\\img/p1tankR.gif"));
            p1tankU = ImageIO.read(new File("D:\\Users\\Administrator\\IdeaProjects\\learnjava8\\src\\main\\java\\img/p1tankU.gif"));

            enemy1D = ImageIO.read(new File("D:\\Users\\Administrator\\IdeaProjects\\learnjava8\\src\\main\\java\\img\\enemy1D.gif"));
            enemy1L = ImageIO.read(new File("D:\\Users\\Administrator\\IdeaProjects\\learnjava8\\src\\main\\java\\img\\enemy1L.gif"));
            enemy1R = ImageIO.read(new File("D:\\Users\\Administrator\\IdeaProjects\\learnjava8\\src\\main\\java\\img/enemy1R.gif"));
            enemy1U = ImageIO.read(new File("D:\\Users\\Administrator\\IdeaProjects\\learnjava8\\src\\main\\java\\img/enemy1U.gif"));


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static Random random = new Random();
    public static int randDirect(){

        return  random.nextInt(4);

    }
}
