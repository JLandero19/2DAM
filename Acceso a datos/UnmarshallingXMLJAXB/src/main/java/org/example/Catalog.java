package org.example;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

// @XmlRootElement -> indica que este objeto puede ser raiz del XML
@XmlRootElement(name = "Catalog")
public class Catalog {
    private List<Book> catalog;

    public Catalog() {}

    @XmlElement(name = "Book")
    public List<Book> getCatalog() {
        return catalog;
    }

    public void setCatalog(List<Book> catalog) {
        this.catalog = catalog;
    }


}
