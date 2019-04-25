package cn.liang.learnjava8;

import java.util.Arrays;
import java.util.List;

public class chapter1 {

    public static void main(String[] args) {
        List<Apple> list=Arrays.asList(
                new Apple("green",200.0), new Apple("red",140.0),new Apple("yellow",300.0)) ;
        //普通流
        long red = list.stream().filter((s) -> s.getColor().equals("red")).count();
        System.out.println(red);
        //并行流
        long weight = list.parallelStream().filter(s -> s.getWeight() > 150.0).count();
        System.out.println(weight);
    }
}


class Apple{
    private String color;
    private Double weight;
    public Apple(){}
    public Apple(String c,Double w){color = c;weight = w;}
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}