package ru.lisenkova;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Table
@Entity
public class Reader implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;
    private Date birthdate;
    @OneToMany(targetEntity = Book.class)
    private List<Book> borrowedBooks;
    public Reader(){};
    public Reader( String nameReader,String surnameReader,
                  Date dateReader)
    {
        this.name = nameReader;
        this.surname = surnameReader;
        this.birthdate = dateReader;
        this.borrowedBooks = new ArrayList<>();
    }
    public Integer getId()
    {
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getSurname(){
        return this.surname;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }
    public Date getBirthdate(){
        return this.birthdate;
    }
    public void setBirthdate(Date birthdate){
        this.birthdate = birthdate;
    }
    public List<Book> getBorrowedBooks(){
        return this.borrowedBooks;
    }
    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks=borrowedBooks;
    }
    public void addBorrowedBooks(Book borrowedBook) {
        this.borrowedBooks.add(borrowedBook);
    }
    public void returnBorrowedBooks(Book borrowedBook) {
        this.borrowedBooks.remove(borrowedBook);
    }
    public List<String> booksToString()
    {
        List<String> readerBooks = new ArrayList<>();
        for(Book book: this.borrowedBooks)
            readerBooks.add(book.getTitle());
        return readerBooks;
    }
}
