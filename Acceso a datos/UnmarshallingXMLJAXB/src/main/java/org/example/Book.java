package org.example;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Book {
    private String id;
    private String author;
    private String title;
    private String genre;
    private String price;
    private String publishDate;
    private String description;

    public Book() {}

    public Book(String id, String author, String title, String genre, String price, String publishDate, String description) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.publishDate = publishDate;
        this.description = description;
    }

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name = "Author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    @XmlElement(name = "Title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @XmlElement(name = "Genre")
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    @XmlElement(name = "Price")
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    @XmlElement(name = "PublishDate")
    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
    @XmlElement(name = "Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        String bookLine = "id: " + getId() + "\n";
        bookLine += "Título: " + getTitle() + "\n";
        bookLine += "Autor: " + getAuthor() + "\n";
        bookLine += "Género: " + getGenre() + "\n";
        bookLine += "Descripción: " + getDescription() + "\n";
        bookLine += "Precio: " + getPrice() + "\n";
        bookLine += "Fecha Publicación: " + getPublishDate() + "\n";
        return bookLine;
    }
}
