package cn.liang.learnjava8.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TestReduce {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(12, 22, 5, 9, 70);
        //流的归约
        Optional<Integer> reduce = list.stream().reduce((a, b) -> a + b);
        System.out.println(reduce.get());


        //领域
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );


        // (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
        List<Transaction> list1 = transactions.stream().filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        list1.forEach(System.out::println);
        //(2) 交易员都在哪些不同的城市工作过？
        List<String> address = transactions.stream()
                .map(s -> s.getTrader().getCity()).distinct()
                .collect(Collectors.toList());
        System.out.println(address);
        System.out.println("#############");
        //(3) 查找所有来自于剑桥的交易员，并按姓名排序。
        List<Transaction> cambridgeList = transactions.stream().filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(a -> a.getTrader().getName()))
                .collect(Collectors.toList());
        cambridgeList.stream().forEach(System.out::println);

        //(4) 返回所有交易员的姓名字符串，按字母顺序排序。

        String collect = transactions.stream().map(t -> t.getTrader().getName())
                .sorted().distinct().collect(Collectors.joining());
        System.out.println("@@@@@"+collect);
        //(5) 有没有交易员是在米兰工作的？
        transactions.stream().filter(t->t.getTrader().getCity().equals("Milan")).forEach(System.out::println);
        //(6) 打印生活在剑桥的交易员的所有交易额。
        Optional<Integer> cambridge = transactions.stream().filter(t -> t.getTrader().getCity()
                .equals("Cambridge")).map(Transaction::getValue).reduce((x, y) -> x + y);
        System.out.println(cambridge.get());
//        (7) 所有交易中，最高的交易额是多少？
        Optional<Transaction> max = transactions.stream().max(Comparator.comparing(Transaction::getValue));
        System.out.println(max.get());
//        (8) 找到交易额最小的交易
        Optional<Transaction> min = transactions.stream().min(Comparator.comparing(Transaction::getValue));
        System.out.println(min.get());
    }
}
