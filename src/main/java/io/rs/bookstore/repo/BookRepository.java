package io.rs.bookstore.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.rs.bookstore.entities.Book;

//Paging ==> Pages and Sorts everything
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    Book findByTitle(String title);
    List<Book> findByTitleContaining(String title);
    List<Book> findByTitleAndAuthor(String title, String author);

}