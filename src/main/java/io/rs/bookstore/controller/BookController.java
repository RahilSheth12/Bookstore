package io.rs.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import io.rs.bookstore.repo.BookRepository;

@Controller
@RequestMapping("/")
public class BookController {

    @Autowired
    private BookRepository bookRepository;
    
    @GetMapping
    public String homePage(Model model) {
        model.addAttribute("books",bookRepository.findAll());
        return "index";
    }

    @GetMapping("/{title}")
    public String getBook(Model model, @PathVariable String title) {
        model.addAttribute("book",bookRepository.findByTitle(title));
        return "book";
    }


}