package com.bookvault.bookvault.repo;


import com.bookvault.bookvault.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepo extends JpaRepository<Book, UUID> {


    @Query(value="select * from book where availableCopies>0 AND author=?1 AND genre=?2",nativeQuery=true)
    List<Book> findByAuthorAndGenre(String author, String genre);



}
