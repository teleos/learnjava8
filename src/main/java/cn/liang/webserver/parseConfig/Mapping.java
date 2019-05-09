package cn.liang.webserver.parseConfig;

import java.util.HashSet;
import java.util.Set;

public class Mapping {


    private String name;
    private Set<String> patterns;

    public Mapping() {
        patterns = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public Set<String> getPatterns() {
        return patterns;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPatterns(Set<String> patterns) {
        this.patterns = patterns;
    }

    public void addPatterns(String pattern){
        patterns.add(pattern);
    }

    @Override
    public String toString() {
        return "Mapping{" +
                "name='" + name + '\'' +
                ", patterns=" + patterns +
                '}';
    }
}
