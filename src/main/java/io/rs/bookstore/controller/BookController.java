package io.rs.bookstore.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.rs.bookstore.entities.Book;
import io.rs.bookstore.repo.BookRepository;
//MVC ==> Model, Value, 
@Controller
@RequestMapping("/")
public class BookController {

    @Autowired
    private BookRepository bookRepository;
    
    private static final Log log = LogFactory.getLog(BookController.class);
    // C R UD ==> read ==> @Get
    @GetMapping
    public String homePage(Model model) {
        model.addAttribute("books",bookRepository.findAll());
        return "index";
    }

    @PostMapping("/")
    public String homePage(@ModelAttribute Book book, Model model){
        log.info("title=" + book.getTitle());
        log.info("author=" + book.getAuthor());
        if(book.getTitle().isBlank() && book.getAuthor().isBlank()){
            model.addAttribute("books", bookRepository.findAll());
        }
        
        else if ((book.getTitle().isBlank() && !book.getAuthor().isBlank())){
            model.addAttribute("books", bookRepository.findByAuthorContaining(book.getAuthor()));
        }

        else if (!book.getTitle().isBlank() && book.getAuthor().isBlank()){
            model.addAttribute("books", bookRepository.findByTitleContaining(book.getTitle()));
        }

        else if (!book.getTitle().isBlank() && !book.getAuthor().isBlank()){
            model.addAttribute("books", bookRepository.findByTitleContainingAndAuthorContaining(book.getTitle(),book.getAuthor()));
        }
        return "index";
    }
    //PathVariable is in url
    @GetMapping("/{title}")
    public String getBook(Model model, @PathVariable String title) {
        model.addAttribute("book",bookRepository.findByTitle(title));
        return "book";
    }
    //ModelAttribute is giving us the whole book.
    @PostMapping("/booksearch")
    public String searchBook(@ModelAttribute("book")Book book) {
        System.out.println(book.getTitle());
        //model.addAttribute("book",bookRepository.findByTitle(title));
        return "booksearch";
    }
    @PostMapping("/add_book")
    public String addBook(@ModelAttribute Book book, Model model){
    bookRepository.save(book);
    return homePage(model);
    }
    @GetMapping("/remove")
    public String removeBook(Model model, @RequestParam long id) {
        bookRepository.deleteById(id);
        return homePage(model);
    }

}