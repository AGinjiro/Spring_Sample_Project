package com.example.demo.contoroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;

@Controller
public class BookController {
	
	@Autowired
    BookService service;
	
	 @GetMapping("/book-list")
	    public String bookList(Model model) {
			
	        
	        List<Book> bookList = service.findAll();
	       
	        model.addAttribute("bookList", bookList);
	       
	        return "bookList";
	    }

}
