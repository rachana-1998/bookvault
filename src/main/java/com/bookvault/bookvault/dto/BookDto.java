package com.bookvault.bookvault.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    @Pattern(regexp = "978-\\d{10}", message = "ISBN must be in format 978-XXXXXXXXXX")
    private String isbn;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    private String genre;

    @Min(value = 1, message = "Total copies must be at least 1")
    private int totalCopy;

    @Min(value = 0, message = "Available copies cannot be negative")
    private int availableCopies;
}
