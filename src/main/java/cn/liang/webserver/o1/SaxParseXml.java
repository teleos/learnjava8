package cn.liang.webserver.o1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaxParseXml {

    public static void main(String[] args) {
        try {
            List<Person> list = new ArrayList<>();
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse("src/main/resources/a.xml",new MyXmlHandle(list));

            list.forEach(System.out::println);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




class MyXmlHandle extends DefaultHandler{

    private List list;
    private Person p;
    private String tag;
    public MyXmlHandle(List list){
        this.list = list;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("解析开始。。。");
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("解析结束。。。");
    }

    /**解析标签里的内容*/
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if (tag !=null){
            String value = new String(ch, start, length);
            if ("name".equals(tag))
                p.setName(value);
            else if ("age".equals(tag))
                p.setAge(Integer.parseInt(value));
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if ("person".equals(qName) ){
            p = new Person();
        }
        tag = qName;

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if ("person".equals(qName)){
            list.add(p);
        }
        tag = null;
    }
}

class Person{
    private String name;
    private int age;
    private int id;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}