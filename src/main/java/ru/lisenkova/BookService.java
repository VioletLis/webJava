package ru.lisenkova;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class BookService {
    @Autowired
    BookRepository repo;

    public void save(Book book) {
        repo.save(book);
    }

    public List<Book> listAll() {
        return repo.findAll();
    }

    public Book get(Long id) {
        return repo.findById(id).get();
    }
    public void update(Book book){
        repo.findById(book.getIsbn()).get().setTitle(book.getTitle());
        repo.findById(book.getIsbn()).get().setAuthor(book.getAuthor());
        repo.findById(book.getIsbn()).get().setReader(book.getReader());
    }
    public void delete(Long isbn) {
        repo.deleteById(isbn);
    }
    public List<Book> search(String keyword) {
        return repo.search(keyword);
    }
}
