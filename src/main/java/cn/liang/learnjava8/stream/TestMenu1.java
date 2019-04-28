package cn.liang.learnjava8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class TestMenu1 {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        List<String> threeColorDishNames = menu.stream()
                .filter(dish ->{
                            System.out.println("filtering" + dish.getName());
                            return dish.getCalories() > 300;
                        }
                )
                .map(dish -> {
                    System.out.println(dish.getName());
                    return dish.getName();
                })
                .limit(3)
                .collect(toList());
        System.out.println(threeColorDishNames);

        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        List<int[]> pairs =
                numbers1.stream()
                        .flatMap(i ->
                                numbers2.stream()
                                        .filter(j -> (i + j) % 3 == 0)
                                        .map(j -> new int[]{i, j})
                        )
                        .collect(toList());

        pairs.forEach( ints -> System.out.println(ints[0] +"，"+ ints[1]));

        System.out.println("#########至少有一个元素匹配谓词##########");
        if (menu.stream().anyMatch(Dish::isVegetarian)){
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }

        Optional<Dish> dish = menu.stream().filter(Dish::isVegetarian).findAny();
        System.out.println(dish);

        System.out.println("**********************数值流**************************");
        int calories = menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println("计算所有的calories:"+calories);

    }
}
