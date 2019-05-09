package cn.liang.webserver.parseConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebContent {
    private List<Entity> entities ;
    private List<Mapping> mappings ;

    private Map<String ,String> entityMap = new HashMap<>();
    private Map<String,String> mappingMap= new HashMap<>();


    public WebContent(List<Entity> entities,List<Mapping> mappings){
        this.entities = entities;
        this.mappings = mappings;
    }

    public String getClazz(String pattern){

        return null;
    }
}
