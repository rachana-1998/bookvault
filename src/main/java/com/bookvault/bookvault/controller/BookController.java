package com.bookvault.bookvault.controller;


import com.bookvault.bookvault.config.ApiResponse;
import com.bookvault.bookvault.model.Book;
import com.bookvault.bookvault.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/books")
public class BookController {

    private final BookService bookService;
     BookController(BookService bookService) {
         this.bookService = bookService;
     }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Book>> getBook(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(ApiResponse.success(bookService.getBook(id)));
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Book>> createBook(@Valid @RequestBody Book book) {
       return   ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(bookService.createBook(book)));

    }
    @PutMapping
    public ResponseEntity<ApiResponse<Book>> updateBook(@Valid @RequestBody Book book) {
    return  ResponseEntity.ok(ApiResponse.success(bookService.updateBook(book)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteBook(@PathVariable UUID id) {
      boolean isDeleted=  bookService.deleteBook(id);
      return ResponseEntity.ok(ApiResponse.success(isDeleted));

    }
}
