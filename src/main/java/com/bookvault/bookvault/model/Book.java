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
    @Pattern(regexp = "978-\\d{10}", message = "ISBN must be in format 978-XXXXXXXXXX")
    private String isbn;
    @NotEmpty
    private String title;
    @NotEmpty
    private String author;


    private String genre;
    @Min(1)
    private int totalCopy;

    @Min(0)
    private int availableCopies;
}
