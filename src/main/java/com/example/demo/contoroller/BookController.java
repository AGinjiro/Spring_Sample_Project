package com.example.demo.contoroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.BookForm;
import com.example.demo.form.EditBookForm;
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
	 
	 /**
	     * 新規登録画面を表示
	     * @param model
	     * @return 新規登録画面
	     */
	    @GetMapping("/book-create")
	    public String createBook(Model model) {

	        model.addAttribute("bookForm", new BookForm());

	        return "add";
	    }
	    
	    
	    /**
	     * データベースに本を登録する
	     * @param bookForm
	     * @param model
	     * @return
	     */
	    @PostMapping("/book-create")
	    public String saveBook(@ModelAttribute @Validated BookForm bookForm, BindingResult bdResult,Model model) {
	    	
	    	if(bdResult.hasErrors()){
	    		return "add";
	    	}
	        // 本を登録する
	        service.insert(bookForm);

	        // 本の一覧表示画面にリダイレクト
	        return "redirect:/book-list";
	    }
	    
	 // 編集画面を表示する
	    @GetMapping("/book-edit")
	    public String editBook(Model model, EditBookForm editBook) {
	    		
	        editBook = service.getOneBook(editBook.getId());
	        model.addAttribute(editBook);
	    		
	        return "edit";
	    }
	    
	 // 本の情報を更新する
	    @PostMapping("/book-edit")
	    public String update(@ModelAttribute @Validated EditBookForm editBook, BindingResult result, Model model) {
	    		
	        // バリデーションエラーの場合
	        if (result.hasErrors()) {
	            // 編集画面に遷移
	            return "edit";
	        }
	    	
	        // 本を更新する
	        service.update(editBook);
	        
	     // 本の一覧画面にリダイレクト
	        return "redirect:/book-list";
	    }
	    

}
