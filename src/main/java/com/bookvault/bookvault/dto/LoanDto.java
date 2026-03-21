package com.bookvault.bookvault.dto;


import com.bookvault.bookvault.model.Book;
import com.bookvault.bookvault.model.Member;
import com.bookvault.bookvault.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanDto {


    private Book book;


    private Member member;

    private LocalDateTime borrowedAt;

    private LocalDateTime dueDate;

    public void prePersist(){
        this.borrowedAt=LocalDateTime.now();
        this.dueDate=LocalDateTime.now().plusDays(14);
    }

    private LocalDateTime returnedAt;

    private Status status = Status.ACTIVE;
}
