package cn.liang.learnjava8.lambda;

public class Fruit {
}


class Orange extends Fruit{
    private String name;
    private Double weight;

    public Orange() {
    }

    public Orange(Double weight) {
        this.weight = weight;
    }

    public Orange(String name, Double weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Orange{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}