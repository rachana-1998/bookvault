package com.bookvault.bookvault.service;

import com.bookvault.bookvault.exp.ResourceNotFoundException;
import com.bookvault.bookvault.model.Book;
import com.bookvault.bookvault.repo.BookRepo;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookService {
    private final BookRepo bookRepo;

    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public Book getBook(UUID id) {
        Book book = bookRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Book not found"));

       return  book;
    }
    public Book createBook(Book book) {
        return bookRepo.save(book);
    }
    public Book updateBook(Book book) {
        Book updatedBook = bookRepo.findById(book.getId()).orElseThrow(()->new ResourceNotFoundException("Book not found"));
        updatedBook.setTitle(book.getTitle());
        updatedBook.setAuthor(book.getAuthor());
        updatedBook.setAvailableCopies(book.getAvailableCopies());
        updatedBook.setGenre(book.getGenre());
        updatedBook.setIsbn(book.getIsbn());
        updatedBook.setTotalCopy(book.getTotalCopy());
        return bookRepo.save(updatedBook);
    }
    public boolean deleteBook(UUID id) {
        Book book = bookRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Book not found"));

        bookRepo.deleteById(book.getId());
        return true;
    }
}
