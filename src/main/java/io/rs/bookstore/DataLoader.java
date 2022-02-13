package io.rs.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import io.rs.bookstore.entities.Book;
import io.rs.bookstore.repo.BookRepository;

@Component
public class DataLoader implements CommandLineRunner{
    private final BookRepository repository;
    
    
    @Autowired
    public DataLoader(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        this.repository.save(new Book("Apple", "Apple", "Apple"));
        this.repository.save(new Book("Banna", "Banna", "Banna"));  
    }
    
}