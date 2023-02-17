package com.zia.btaservice.Controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zia.btaservice.Model.BookTracker;
import com.zia.btaservice.Repository.IBookRepository;

@RestController
@RequestMapping("/trackbooks")
public class BookTrackerController {
    @Autowired
    private IBookRepository bookRepository;

    @GetMapping("/allbooks")
    public List<BookTracker> getAllBooks() {
        return bookRepository.findAll();
    }

    @PostMapping("/addBooks")
    public String addBooks(@RequestBody BookTracker bookTracker){
        bookRepository.save(bookTracker);
        return "Book Added Successfully!"+bookTracker.getId();
    }

    @GetMapping("/list/{id}")
    public BookTracker getBookById(@PathVariable Long id){
        return bookRepository.findById(id).orElse(null);
    }

    @PutMapping("/addBooks")
    public String updateBooks(@RequestBody BookTracker bookTracker){
        bookRepository.save(bookTracker);
        return "Book Updated Successfully!";
    }
}
