package com.example.hello.controller;

import com.example.hello.Utils.Result;
import com.example.hello.pojo.Book;
import com.example.hello.service.BookService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;



@CrossOrigin
@RestController

public class BookController {
    @Resource
    private BookService bookService;

    /**
     * 返回数据给前端表格
     *
     * @return List<Book>
     */
    @GetMapping("/books")
    public Result getAllBooks() {
        return Result.success(bookService.getAllBooks());
    }

    @DeleteMapping("/books/{bookId}")
    public void deleteBook(@PathVariable String bookId) {
        bookService.delete(bookId);
    }

    @PutMapping("/books")
    public void updateBook(@RequestBody Book book) {
        bookService.update(book);
    }

    @PostMapping("/books")
    public void addBook(@RequestBody Book book) {
        bookService.add(book);
    }

    @GetMapping("/books/{searchText}/{selectAttr}")
    public Result getBooksByAttribute(@PathVariable String searchText, @PathVariable String selectAttr) {

        System.out.println(searchText+' '+selectAttr);
        return Result.success(bookService.getBooksByAttribute(searchText,selectAttr));

    }
}