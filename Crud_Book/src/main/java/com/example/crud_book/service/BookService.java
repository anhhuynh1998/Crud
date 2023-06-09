package com.example.crud_book.service;

import com.example.crud_book.dao.BookDAO;
import com.example.crud_book.model.Book;

import java.util.List;

public class BookService implements BaseCRUDService<Book> {
    private final BookDAO bookDAO= new BookDAO();


    @Override
    public void create(Book book) {
bookDAO.insertBook(book);
    }

    @Override
    public void update(Book book) {
bookDAO.updateBook(book);
    }

    @Override
    public void delete(int id) {
bookDAO.deleteBook(id);
    }

    @Override
    public Book findById(int id) {
        return bookDAO.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookDAO.findALL();
    }
}
