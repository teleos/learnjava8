package cn.liang.webserver.o1;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.ArrayList;
import java.util.List;

public class Dom4jParseXML {

    public  List<?> readXML(String path) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(path);
        Element root = document.getRootElement();

        List<Person> personList = new ArrayList<>();
        root.elements().spliterator()
        .forEachRemaining(action->{
            Person p = new Person();
            Element e = (Element) action;
            p.setId( Integer.valueOf( e.attributeValue("id")));
            e.elements().spliterator().forEachRemaining(action2->{
                Element f = (Element) action2;
                if (f.getName().equals("age")){
                    p.setAge(Integer.valueOf(f.getTextTrim()));
                }else if (f.getName().equals("name")){
                    p.setName(f.getTextTrim());
                }
            });
            personList.add(p);
        });

        return personList;
    }


    public static void main(String[] args) {
        try {
            Dom4jParseXML parse = new Dom4jParseXML();
            parse.readXML("src/main/resources/a.xml")
                    .stream().forEach(System.out::println);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
