package com.example.hello.service;

import com.example.hello.mapper.BookMapper;
import com.example.hello.pojo.Book;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {
    @Resource
    private BookMapper bookMapper;

    @Resource
    private HttpServletRequest request;

    public void add(Book book) {
        String str = UUID.randomUUID().toString().replaceAll("-", "").substring(0,8);
        while (bookMapper.equals(str)!=null){
            str = UUID.randomUUID().toString().replaceAll("-", "").substring(0,8);
        }
        book.setBookId(str);

        //更新添加时间和修改时间
        book.setCreateTime(LocalDateTime.now());
        book.setUpdateTime(LocalDateTime.now());

        String username = (String) request.getAttribute("username");
        book.setUserOfBook(username);

        bookMapper.add(book);
    }

    public void update(Book book) {
        //更新修改时间
        book.setUpdateTime(LocalDateTime.now());

        bookMapper.update(book);

    }

    public void delete(String bookId) {
        bookMapper.delete(bookId);
    }

    public List<Book> getAllBooks() {
        String username = (String) request.getAttribute("username");

//        System.out.println("request.username = " + username);

        return bookMapper.getAllBooks(username);
    }

    public List<Book> getBooksByAttribute(String searchText,String selectAttr) {
        String username = (String) request.getAttribute("username");

//        System.out.println("request.username = " + username);

        return bookMapper.getBooksByAttribute(searchText, selectAttr, username);
    }
}
