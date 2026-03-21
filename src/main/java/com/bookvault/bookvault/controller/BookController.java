package com.bookvault.bookvault.controller;


import com.bookvault.bookvault.dto.ApiResponse;
import com.bookvault.bookvault.dto.BookDto;
import com.bookvault.bookvault.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/books")
public class BookController {

    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<BookDto>>> getAllPagableBooks(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "10")int size ){
        return ResponseEntity.ok(ApiResponse.success(bookService.getAllPagableBook(page,size)));

    }
   @GetMapping("/")
   public ResponseEntity<ApiResponse<List<BookDto>>> getAllBooks(@RequestParam String author,@RequestParam String genre){
        return ResponseEntity.ok(ApiResponse.success(bookService.getAllBooks(author,genre)));
   }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BookDto>> getBook(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(bookService.getBook(id)));
    }



    @PostMapping
    public ResponseEntity<ApiResponse<BookDto>> createBook(@Valid @RequestBody BookDto book)  {
       return   ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(bookService.createBook(book)));

    }



    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BookDto>> updateBook(@PathVariable UUID id ,@Valid @RequestBody BookDto book) {
    return  ResponseEntity.ok(ApiResponse.success(bookService.updateBook(id,book)));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBook(@PathVariable UUID id) {
        bookService.deleteBook(id);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponse.success(null));

    }
}
