package cn.liang.learnjava8.stream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PythagoreanCalc {

    public static void main(String[] args) {
        //计算勾股数
        Stream<int[]> pythagoreans =
                IntStream.rangeClosed(1, 100).boxed()  //a的取值范围[0,100]
                        .flatMap(a ->
                                IntStream.rangeClosed(a, 100) //b的取值方位 [a,100] 防止数重复
                                        .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
                                        .mapToObj(b ->
                                                new int[]{a, b, (int)Math.sqrt(a * a + b * b)})
                        );

        pythagoreans.forEach(x->
                System.out.println(x[0] +","+x[1]+","+x[2]));


        //创建流的方式：：

        //1.由值创建流
        Stream<String> stream = Stream.of("Java 8", "lambdas", "in", "action");
        stream.map(String::toUpperCase).forEach(System.out::println);
        //获取一个空流
        Stream.empty();
        //2. 由数组创建流
        int numbers[] = {2,3,5,6,11,13,15};
        int sum = Arrays.stream(numbers).sum();
        System.out.println(sum);
        //3. 由文件生成流
        long uniqueWords = 0;

        String path = System.getProperty("user.dir");

        System.out.println(path);
        try (Stream<String> lines = Files.lines(
                Paths.get(path+"/.gitignore"),
                Charset.defaultCharset())) {
            long count = lines.flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();
            System.out.println(count);
        }catch (IOException e){
            e.printStackTrace();
        }

        //4. 生成无限流
        Stream.iterate(0, n -> n+2).limit(200). forEach(System.out::println);

        //斐波那契数列

        Stream.iterate(new int[]{0,1}, n-> new int[]{n[1],n[0] + n[1] } )
        .limit(10).map(t -> t[1])
        .forEach(System.out::println);
    }
}
