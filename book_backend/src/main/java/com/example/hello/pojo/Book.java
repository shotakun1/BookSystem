package com.example.hello.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Book implements Serializable{
    private String bookId = null;
    private String title = null;
    private String author = null;
    private String publisher = null;
    private Float price = null;
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间
    private String userOfBook;

    public Book(){}

    public Book(String bookId, String title, String author, String publisher, Float price, LocalDateTime createTime, LocalDateTime updateTime, String userOfBook) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.userOfBook = userOfBook;
    }
    public String getBookId() { return this.bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return this.author; }
    public Float getPrice() { return price; }
    public String getPublisher () { return publisher; }
    public LocalDateTime getCreateTime() {
        return createTime;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setBookId(String bookId){ this.bookId = bookId; }
    public void setTitle(String title){this.title=title; }
    public void setAuthor(String author){ this. author = author; }
    public void setPrice(Float price){this.price=price; }
    public void setPublisher (String publisher){ this.publisher = publisher;}
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserOfBook() {
        return userOfBook;
    }

    public void setUserOfBook(String userOfBook) {
        this.userOfBook = userOfBook;
    }

    public boolean isEmpty(){
        return bookId.isEmpty()||title.isEmpty()||author.isEmpty()||publisher.isEmpty()||price==null;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId='" + bookId + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", price=" + price +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", userOfBook='" + userOfBook + '\'' +
                '}';
    }
}