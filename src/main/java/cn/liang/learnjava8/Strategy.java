package cn.liang.learnjava8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Java8以前传递行为的做法
 */
public class Strategy {

    public static void main(String[] args) {

        List<Apple> list= Arrays.asList(
                new Apple("green",200.0), new Apple("red",140.0),new Apple("yellow",300.0)) ;
        Strategy strategy = new Strategy();
        List<Apple> list1 = strategy.filterApples(list, new AppleWeightPredicate());
        list1.forEach(System.out::println);

    }

    /**筛选苹果*/
    public List<Apple> filterApples(List<Apple> apples,ApplePredicate predicate){

        List<Apple> result = new ArrayList<>();
        for (Apple a:apples){
            if ( predicate.test(a) )
                result.add(a);
        }
        return result;
    }

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter f){
        for(Apple apple: inventory) {


        }
    }


}


/*##############
 *  谓词接口与实现代码
 * ###################
 */

interface ApplePredicate{
    boolean test(Apple a);
}

class AppleColorPredicate implements ApplePredicate{

    /**
     *
     * @param a
     * @return  Color is red
     */
    @Override
    public boolean test(Apple a) {
        return "red".equals(a.getColor());
    }
}


class AppleWeightPredicate implements ApplePredicate{

    /**
     *
     * @param a
     * @return weight gt 150
     */
    @Override
    public boolean test(Apple a) {
        return a.getWeight()>150;
    }
}


/**
 * 打印字符格式化接口
 *
 */
interface AppleFormatter{
    //todo 字符格式化
    String accept(Apple a);
}