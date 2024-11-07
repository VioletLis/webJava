package ru.lisenkova;

import jakarta.persistence.*;

import java.io.Serializable;
@Table
@Entity
public class Book implements Serializable {
    @Id
    private Long isbn;
    private String title;
    private String author;
    @ManyToOne(targetEntity = Reader.class)
//    @JoinColumn(name = "id")
    private Reader reader;

    public Book() {
    };

    public Book(Long isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.reader = null;
    }
    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }
    public Long getIsbn(){
        return this.isbn;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setAuthor(String author){
        this.author = author;
    }
    public String getAuthor(){
        return this.author;
    }
    public void setReader(Reader reader){
        this.reader = reader;
    }
    public Reader getReader() {
        return this.reader;
    }
}