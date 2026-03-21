package com.bookvault.bookvault.service;

import com.bookvault.bookvault.dto.BookDto;
import com.bookvault.bookvault.exp.BadRequestException;
import com.bookvault.bookvault.exp.ResourceNotFoundException;
import com.bookvault.bookvault.model.Book;
import com.bookvault.bookvault.repo.BookRepo;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {
    private final BookRepo bookRepo;
    private final ModelMapper modelMapper;

    public BookService(BookRepo bookRepo, ModelMapper modelMapper) {
        this.bookRepo = bookRepo;
        this.modelMapper = modelMapper;
    }
    public List<BookDto> getAllPagableBook(int page,int size){
        Pageable pageable= PageRequest.of(page,size);
       Page<Book> bookList= bookRepo.findAll(pageable);
       return bookList.stream().map(book->modelMapper.map(book, BookDto.class)).toList();

    }

    public List<BookDto> getAllBooks(String author, String genre) {
        List<Book>bookList=bookRepo.findByAuthorAndGenre(author,genre);
       return bookList.stream().map(book->modelMapper.map(book,BookDto.class)).toList();
    }

    public BookDto getBook(UUID id) {
        Book book =
                bookRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Book not found"));

       return modelMapper.map(book,BookDto.class);
    }


    public BookDto createBook(BookDto book) {
         validateCopies(book);
        Book newBook = modelMapper.map(book, Book.class);
        return modelMapper.map(bookRepo.save(newBook),BookDto.class);
    }


    public BookDto updateBook(UUID id ,BookDto book)  {
         validateCopies(book);
        Book updatedBook = bookRepo.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Book not found"));

        modelMapper.map(book,updatedBook);
        return modelMapper.map(bookRepo.save(updatedBook),BookDto.class);
    }


    public void deleteBook(UUID id) {
       if(!bookRepo.existsById(id)){
        throw new ResourceNotFoundException("Book not found");
    }
        bookRepo.deleteById(id);

    }
    private void validateCopies(BookDto bookDto)  {
        if(bookDto.getAvailableCopies() > bookDto.getTotalCopy()){

       throw new BadRequestException("available copies of books can not be greater than total copies");
        }
    }
}
