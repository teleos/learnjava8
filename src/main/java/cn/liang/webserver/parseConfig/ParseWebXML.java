package cn.liang.webserver.parseConfig;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.util.List;
import java.util.stream.Collectors;


public class ParseWebXML {

    private List<Entity> entities ;
    private List<Mapping> mappings ;

    public ParseWebXML(){

        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read("src/main/webapp/WEB-INF/web.xml");
            Element rootElement = document.getRootElement();

            entities = (List<Entity>) rootElement.elements("servlet").stream().map(t->{
                Entity entity = new Entity();
                Element e = (Element) t;
                String servletName = e.element("servlet-name").getTextTrim();
                entity.setName(servletName);
                String servletClazz = e.element("servlet-class").getTextTrim();
                entity.setClazz(servletClazz);
                return entity;
            }).collect(Collectors.toList());

            mappings = (List<Mapping>) rootElement.elements("servlet-mapping").stream().map(t->{
                Element e = (Element) t;
                Mapping mapping = new Mapping();
                e.elements("url-pattern").spliterator().forEachRemaining(action->{
                    Element p = (Element) action;
                    mapping.addPatterns(p.getTextTrim());
                });
                mapping.setName( e.element("servlet-name").getTextTrim());
                return mapping;
            }).collect(Collectors.toList());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ParseWebXML parseWebXML = new ParseWebXML();
        parseWebXML.entities.forEach(System.out::println);
        parseWebXML.mappings.forEach(System.out::println);
    }
}
