package com.bookvault.bookvault.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Loan  extends Base{

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id",referencedColumnName = "id")
    private Book book;

    @ManyToOne(optional = false)
    @JoinColumn(name = "member_id",referencedColumnName = "id")
    private Member member;

    private LocalDateTime borrowedAt;

    private LocalDateTime dueDate;
    @PrePersist
    public void prePersist(){
        this.borrowedAt=LocalDateTime.now();
        this.dueDate=LocalDateTime.now().plusDays(14);
    }

    private LocalDateTime returnedAt;
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
}
