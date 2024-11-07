package ru.lisenkova;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ReaderService {
    @Autowired
    ReaderRepository repo;

    public void save(Reader reader) {
        repo.save(reader);
    }

    public List<Reader> listAll() {
        return repo.findAll();
    }

    public Reader get(Integer id) {
        return repo.findById(id).get();
    }
    public void update(Reader reader){
        repo.findById(reader.getId()).get().setName(reader.getName());
        repo.findById(reader.getId()).get().setSurname(reader.getSurname());
        repo.findById(reader.getId()).get().setBirthdate(reader.getBirthdate());
        repo.findById(reader.getId()).get().setBorrowedBooks(reader.getBorrowedBooks());
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
    public List<Reader> search(String keyword) {
        return repo.search(keyword);
    }
}
