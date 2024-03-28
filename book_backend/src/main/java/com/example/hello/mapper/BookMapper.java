package com.example.hello.mapper;

import com.example.hello.pojo.Book;


import org.apache.ibatis.annotations.*;



import java.util.List;

@Mapper
public interface BookMapper {

    /**
     * 新增书对象
     *
     * @param book 待添加的书对象
     */
    @Insert("INSERT INTO books(bookid, title, author, publisher, price,create_time,last_update_time,user_of_book) VALUES(#{bookId}, #{title}, #{author}, #{publisher}, #{price}, #{createTime},#{updateTime},#{userOfBook})")
    void add(Book book);

    /**
     * 修改书对象
     *
     * @param book 待修改的书对象（bookId是输入的待修改的书号，其他部分待修改）
     */
    @Update("UPDATE books SET title=#{title}, author=#{author}, publisher=#{publisher}, price=#{price},last_update_time=#{updateTime} WHERE bookid=#{bookId}")
    void update(Book book);

    /**
     * 按书本序号删除
     *
     * @param bookId 输入的书本序号
     */
    @Delete("DELETE FROM books WHERE bookid=#{bookId}")
    void delete(String bookId);

    /**
     * 按书本序号查询
     *
     * @param bookId 输入的书本序号
     * @return 查询到的单本书，有可能返回null
     */
    @Select("SELECT * FROM books WHERE bookid=#{bookId}")
    Book search(String bookId);


    @Select("SELECT * FROM books WHERE user_of_book=#{username} ORDER BY create_time")
    List<Book> getAllBooks(String username);

    @Select("SELECT * FROM books WHERE bookid=#{bookId}")
    Book equals(String bookId);

    List<Book> getBooksByAttribute(String searchText, String selectAttr, String username);
}
