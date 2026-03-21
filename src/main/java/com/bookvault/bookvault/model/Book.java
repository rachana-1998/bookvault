package com.bookvault.bookvault.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Book extends Base {


    @Column( unique = true,nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String title;


    @Column(nullable = false)
    private String author;


    private String genre;

    private int totalCopy;


    private int availableCopies;
}
