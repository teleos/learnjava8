package cn.liang.learnjava8.lambda;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

public class chapter3 {
    static Map<String,Function<Double,Fruit>> map = new HashMap<>();
    static {
        map.put("apple",Apple::new);
        map.put("orange",Orange::new);

    }

    public static void main(String[] args) {
        List<Double> weights = Arrays.asList(7.2,3.0,4.3,10.1);
        List<Apple> apples = map(weights,Apple::new);

        apples.sort(Comparator.comparing(Apple::getWeight).reversed());
        apples.forEach(System.out::println);

        BiFunction<String,Double,Apple> c3 = Apple::new;
        Apple greenApple = c3.apply("green", 110.6);
        System.out.println(greenApple);

        System.out.println("###############################");
        Fruit orange = giveMeFruit("orange", 300.0);
        System.out.println(orange);
    }

    public static Fruit giveMeFruit(String fruit,Double weight){
        return map.get(fruit.toLowerCase()).apply(weight);
    }

    private static List<Apple> map(List<Double> weights, Function<Double,Apple> f) {
        List<Apple> list = new ArrayList<>();
        for (Double e:weights) {
            list.add(f.apply(e));
        }
        return list;
    }


}
