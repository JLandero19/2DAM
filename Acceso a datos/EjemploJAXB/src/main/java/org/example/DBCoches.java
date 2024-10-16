package org.example;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "coches")
public class DBCoches {
    private List<Coche> coches;

    public DBCoches() {}

    public DBCoches(List<Coche> coches) {
        this.coches = coches;
    }
    @XmlElement(name = "coche")
    public List<Coche> getCoches() {
        return coches;
    }

    public void setCoches(List<Coche> coches) {
        this.coches = coches;
    }
}
